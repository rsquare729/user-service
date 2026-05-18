```groovy
pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    environment {
        PATH = "/usr/local/bin:${env.PATH}"

        IMAGE_NAME = "rrdocker729/user-service"
        IMAGE_TAG = "v${BUILD_NUMBER}"
        CONTAINER_NAME = "user-service"
    }

    stages {

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker buildx build \
                --platform linux/amd64 \
                -t $IMAGE_NAME:$IMAGE_TAG .
                '''
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh '''
                docker push $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker rm -f $CONTAINER_NAME || true

                docker run -d \
                -p 8081:8081 \
                --name $CONTAINER_NAME \
                $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                sleep 15

                docker ps

                curl http://localhost:8081/users || true
                '''
            }
        }
    }

    post {

        success {
            echo 'Application deployed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }

        always {
            sh 'docker image prune -f || true'
        }
    }
}
```
