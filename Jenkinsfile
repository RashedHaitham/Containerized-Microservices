pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'Maven 3.9.6'
    }

    environment {
        JAVA_HOME = "${tool 'JDK 21'}"
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

        stage('Stop and Remove Services') {
            steps {
                // Explicitly stop the containers
                bat 'docker-compose -f docker-compose.yml stop enterbook authenticationservice analytics-service show-result'
                // Forcefully remove the containers
                bat 'docker-compose -f docker-compose.yml rm -f enterbook authenticationservice analytics-service show-result'
            }
        }

        stage('Recreate and Start Specific Services') {
            steps {
                // Recreate and start the services without their dependencies
                bat 'docker-compose -f docker-compose.yml up -d --no-deps enterbook authenticationservice analytics-service show-result'
            }
        }

    }

    post {
        always {
            // Optional: Clean up resources. Use with caution if you have other Docker resources on the same host.
            bat 'docker-compose -f docker-compose.yml down'
            bat 'docker system prune -f --volumes'
        }
    }
}
