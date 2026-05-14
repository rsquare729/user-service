pipeline {
    agent any
    
    environment {
        PATH = "/usr/local/bin:${env.PATH}"
    }

    tools {
        maven 'Maven-3'
    }

    stages {

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t user-service:v1 .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh '''
                docker rm -f user-service-container || true
                '''
            }
        }

        stage('Run New Container') {
            steps {
                sh '''
                docker run -d -p 8081:8081 \
                --name user-service-container \
                user-service:v1
                '''
            }
        }

        stage('Verify Container') {
            steps {
                sh 'docker ps'
            }
        }
    }
}