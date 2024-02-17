pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml build'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml down'
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment has been successful.'
        }

        failure {
            echo 'Deployment failed.'
        }

        always {
            echo 'Pipeline execution complete.'
        }
    }
}
