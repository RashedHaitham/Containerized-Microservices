pipeline {
    agent any
    
    tools {
        maven 'Maven 3.6.3'
    }

    environment {
        // Define any environment variables needed for Maven or Docker
    }

    stages {
        stage('Checkout') {
            steps {
                // Checks out the source code from the repository
                checkout scm
            }
        }
        
        stage('Build with Maven') {
            steps {
                // Run Maven build
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Images') {
            steps {
                bat 'docker-compose -f docker-compose.yml build'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker-compose -f docker-compose.yml down'
                bat 'docker-compose -f docker-compose.yml up -d'
            }
        }
    }

    post {
        always {
            bat 'docker-compose -f docker-compose.yml down'
            bat 'docker system prune -f'
        }
    }
}
