pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', credentialsId: 'your-credentials-id', url: 'https://github.com/your-account/your-repo.git'
            }
        }
        stage('Build & Deploy') {
            steps {
                script {
                    bat "docker-compose -f docker-compose.yml down"
                    bat "docker-compose -f docker-compose.yml up --build -d"
                }
            }
        }
    }
}
