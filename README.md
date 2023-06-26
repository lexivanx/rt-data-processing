# Real-time Data Processing on AKS

Real-time data processing system using Apache Kafka, Apache Flink, and AKS.

## Project Structure

- **data-processor**: Java-based data processor that is built on Apache Flink. It includes the WordCount.java file and a Dockerfile for building the application's Docker image.
- **data-producer**: Python-based data producer which generates the data that will be processed by the data-processor. It includes the producer.py file, a Dockerfile for building the application's Docker image, and a requirements.txt file for specifying the necessary Python dependencies.
- **frontend**: Frontend application that displays the results of the data processing. It includes the App.js file, a Dockerfile for building the application's Docker image, and a package.json file for specifying the necessary JavaScript dependencies.
- **k8s**: Kubernetes deployment files for the frontend, data processor, and data producer.
- **scripts**: Scripts for creating the AKS cluster and deploying the applications to AKS.

## Usage

To use this project, clone the repository and follow the steps below:

1. Build the Docker images for the data processor, data producer, and frontend applications.
2. Push the Docker images to a Docker registry.
3. Create the AKS cluster using the `create-aks-cluster.sh` script.
4. Deploy the applications to the AKS cluster using the `deploy-to-aks.sh` script.

Note: Ensure that you have the necessary permissions and resources available on Azure before proceeding.

## Technologies Used

This project uses a variety of technologies and tools:

- **Docker**: Used to containerize the services.
- **Azure Kubernetes Service (AKS)**: Used as the Kubernetes platform to orchestrate the containers.
- **Azure Container Registry (ACR)**: Used to store the Docker images.
- **Azure CLI**: Used to interact with Azure resources.
- **Azure Key Vault**: Used to manage and access secrets and keys securely.
- **Maven**: Used to manage the Java dependencies in the data processor service.
- **Jenkins**: Used for CI/CD

## License

This project is licensed under the terms of the MIT license.
