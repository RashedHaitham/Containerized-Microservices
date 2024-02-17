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
                node {
                    checkout scm
                }
            }
        }

        stage('Build with Maven') {
            steps {
                node {
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
        }

        stage('Build Docker Images') {
            steps {
                node {
                    bat 'docker-compose -f docker-compose.yml build'
                }
            }
        }

        stage('Deploy') {
            steps {
                node {
                    bat 'docker-compose -f docker-compose.yml down'
                    bat 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }
    }

    post {
        always {
            node {
                bat 'docker-compose -f docker-compose.yml down'
                bat 'docker system prune -f'
            }
        }
    }
}
