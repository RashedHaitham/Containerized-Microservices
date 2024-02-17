pipeline {
    agent any
    
    environment {
        // Define environment variables if needed, e.g., DOCKER_HOST, registry credentials
        // DOCKER_CREDENTIALS_ID = 'id-for-docker-hub-credentials-stored-in-jenkins'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Docker Images') {
            steps {
                script {
                    bat "docker-compose -f docker-compose.yml build --no-cache"
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    bat "docker-compose -f docker-compose.yml down"
                    bat "docker-compose -f docker-compose.yml up -d"
                }
            }
        }
    }
    
    post {
        always {
            echo 'Cleaning up...'
            bat "docker-compose -f docker-compose.yml down"
            bat "docker system prune -f"
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
