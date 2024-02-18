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

        stage('Build Docker Images') {
            steps {
                bat 'docker-compose -f docker-compose.yml build enterbook authenticationservice analytics-service show-result'
            }
        }

        stage('Deploy') {
            steps {
             // Attempt to forcefully remove existing containers, and do not fail if they do not exist
                     bat 'docker rm -f enterbook || exit /b 0'
                     bat 'docker rm -f authenticationservice || exit /b 0'
                     bat 'docker rm -f analytics-service || exit /b 0'
                     bat 'docker rm -f show-result || exit /b 0'
                     // Proceed to build and start only the specified services without dependencies
                     bat 'docker-compose -f docker-compose.yml up -d --no-deps enterbook authenticationservice analytics-service show-result'
           }
        }
    }

    post {
        always {
            // Stop and remove all services defined in the compose file
            bat 'docker-compose -f docker-compose.yml down'
            // Remove unused volumes associated with the compose file
            bat 'docker volume prune -f --filter label=com.docker.compose.project=dockercompose'

        }
    }
}
