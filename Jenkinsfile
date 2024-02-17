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
                        sh 'docker-compose -f docker-compose.yml pull mongodb mysqldb'
                    }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker-compose -f docker-compose.yml build enterbook authenticationservice analytics-service show-result'
            }
        }

        stage('Deploy') {
            steps {
            // Check if the bookstoremicroservice container is running and stop it
            sh 'docker stop bookstoremicroservice || true'
            sh 'docker rm bookstoremicroservice || true'
            // Proceed with deployment
            sh 'docker-compose -f docker-compose.yml down'
            sh 'docker-compose -f docker-compose.yml up -d'            }
        }
    }

    post {
        always {
            sh 'docker-compose -f docker-compose.yml down'
            sh 'docker system prune -f --volumes'
        }
    }
}
