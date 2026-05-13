pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {

        stage('Build') {
            steps {
                echo 'Building application...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Package Info') {
            steps {
                echo 'Checking generated JAR...'
                sh 'ls -la target'
            }
        }
    }
}