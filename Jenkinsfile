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
                                       sh 'mvn clean install'
                                   }
                                   dir('enterBook/enterBook') {
                                       sh 'mvn clean install'
                                   }
                                   dir('AnalyticsService/AnalyticsService') {
                                       sh 'mvn clean install'
                                   }
                                   dir('authentication-service') {
                                       sh 'mvn clean install'
                                   }}
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
                        sh 'docker-compose -f docker-compose.yml up -d'
                    }
                }
        }
    }

    post {
           always {
               sh 'docker-compose -f docker-compose.yml down'
               sh 'docker system prune -f --volumes'
           }
       }
}
