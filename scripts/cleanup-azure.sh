#!/bin/bash

# Set Azure subscription
az account set --subscription <subscription-id>

# Delete AKS Cluster
echo "Deleting AKS Cluster..."
az aks delete --name <aks-name> --resource-group <resource-group> --yes --no-wait

# Delete Azure Container Registry
echo "Deleting Azure Container Registry..."
az acr delete --name <acr-name> --resource-group <resource-group> --yes

echo "Azure resources deletion triggered. It may take some time for all resources to be deleted."
