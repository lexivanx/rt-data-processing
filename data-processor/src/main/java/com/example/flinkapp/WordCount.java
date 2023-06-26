package com.example.flinkapp;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCount {

    public static void main(String[] args) throws Exception {

        // Set up execution env
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Define data source
        DataStream<String> text = env.fromElements(
                "Lorem ipsum dolor sit amet",
                "consectetur adipiscing elit",
                "Curabitur laoreet fringilla nulla");

        // Transform the source data
        DataStream<Tuple2<String, Integer>> counts = text
                .flatMap(new Tokenizer())  // Tokenize input string into a stream of Tuple2<word, 1>
                .keyBy(value -> value.f0)  // Group tuples by word field
                .sum(1);  // Sum counts for each word

        // Output the result to stdout
        counts.print();

        // Start computation
        env.execute("Streaming WordCount");
    }

    // FlatMapFunction to split sentences to words
    public static final class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // Split the input into words using non-word chars as delimiter
            String[] words = value.toLowerCase().split("\\W+");

            // For each word, emit a Tuple2<word, 1> to output
            for (String word : words) {
                if (word.length() > 0) {
                    out.collect(new Tuple2<>(word, 1));
                }
            }
        }
    }
}
