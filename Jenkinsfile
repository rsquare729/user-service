pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Stop Old App') {
            steps {
                sh '''
                PID=$(lsof -ti:8081 || true)

                if [ ! -z "$PID" ]; then
                  kill -9 $PID
                fi
                '''
            }
        }

        stage('Run New App') {
            steps {
                sh '''
                nohup java -jar target/*.jar > app.log 2>&1 &
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                sleep 15
                curl http://localhost:8081
                '''
            }
        }
    }
}