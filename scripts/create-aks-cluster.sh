#!/bin/bash
# Define variables
resourceGroup="CustResourceGroup"
location="westeurope"
clusterName="CustAKSCluster"

# Create a resource group
az group create --name $resourceGroup --location $location

# Create AKS cluster
az aks create --resource-group $resourceGroup --name $clusterName --node-count 1 --enable-addons monitoring --generate-ssh-keys

# Get AKS credentials
az aks get-credentials --resource-group $resourceGroup --name $clusterName
