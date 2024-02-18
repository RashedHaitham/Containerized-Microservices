pipeline {
    agent any

 tools {
        jdk 'JDK 21'
        maven 'Maven 3.9.6'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
                    steps {
                        dir('ShowResult/BookResult') {
                            bat 'mvn clean install'
                        }
                        dir('enterBook/enterBook') {
                            bat 'mvn clean install'
                        }
                        dir('AnalyticsService/AnalyticsService') {
                            bat 'mvn clean install'
                        }
                        dir('authentication-service') {
                            bat 'mvn clean install'
                        }
                    }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    bat 'docker-compose -f docker-compose.yml down' // Take down any existing containers
                    bat 'docker-compose -f docker-compose.yml build' // Build the Docker images
                    bat 'docker-compose -f docker-compose.yml up -d' // Run the containers in detached mode
                }
            }
        }
    }
}
