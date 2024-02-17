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


        stage('Pull Database Images') {
                    steps {
                        bat 'docker-compose -f docker-compose.yml pull mongodb mysqldb'
                    }
        }

        stage('Build Docker Images') {
            steps {
                bat 'docker-compose -f docker-compose.yml build enterbook authenticationservice analytics-service show-result'
            }
        }

        stage('Deploy') {
            steps {
                // Check if the bookstoremicroservice container is running and stop it, ignoring errors
                bat 'docker stop bookstoremicroservice || exit 0'
                bat 'docker rm bookstoremicroservice || exit 0'
                // Proceed with deployment
                bat 'docker-compose -f docker-compose.yml down'
                bat 'docker-compose -f docker-compose.yml up -d'
            }
        }


    post {
        always {
            bat 'docker-compose -f docker-compose.yml down'
            bat 'docker system prune -f --volumes'
        }
    }
}
