#!/bin/bash

# Define variables
namespace="real-time-data-processing-system"

# Create namespace
kubectl create namespace $namespace

# Deploy applications
kubectl apply -f ../k8s/producer-deployment.yaml -n $namespace
kubectl apply -f ../k8s/processor-deployment.yaml -n $namespace
kubectl apply -f ../k8s/frontend-deployment.yaml -n $namespace
