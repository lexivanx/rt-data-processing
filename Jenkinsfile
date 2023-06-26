pipeline {
    agent any
    environment {
        // Azure Key Vault secrets
        AZURE_SERVICE_PRINCIPAL_ID = azureKeyVault secret: 'cust-azure-id', vaultUrl: "${params.AZURE_VAULT_URL}"
        AZURE_SERVICE_PRINCIPAL_KEY = azureKeyVault secret: 'cust-azure-key', vaultUrl: "${params.AZURE_VAULT_URL}"
        ACR_USERNAME = azureKeyVault secret: 'cust-acr-username', vaultUrl: "${params.AZURE_VAULT_URL}"
        ACR_PASSWORD = azureKeyVault secret: 'cust-acr-password', vaultUrl: "${params.AZURE_VAULT_URL}"

        // Additional required variables
        APP_NAME = 'flinkapp'
        ACR_NAME = 'custacrname'
        ACR_LOGIN_SERVER = "${ACR_NAME}.azurecr.io"
    }
    stages {
        stage('Maven build') {
            steps {
                dir('data-processor') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Docker build and push') {
            steps {
                script {
                    docker.withRegistry("https://${ACR_LOGIN_SERVER}", [username: "${ACR_USERNAME}", password: "${ACR_PASSWORD}"]) {
                        def appImage = docker.build("${ACR_LOGIN_SERVER}/${APP_NAME}:latest")
                        appImage.push()
                    }
                }
            }
        }
        stage('Deploy to AKS') {
            steps {
                azureCLI commands: [[exportVariablesString: '', script: '''
                az aks get-credentials --resource-group CustResourceGroup --name CustAKSCluster
                bash scripts/deploy-to-aks.sh
                ''']], credentialsId: "${params.AZURE_CREDENTIALS_ID}", principalCredentialId: "${params.AZURE_PRINCIPAL_ID}"
            }
        }
    }
}
