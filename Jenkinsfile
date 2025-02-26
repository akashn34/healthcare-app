pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/akashn34/healthcare-backend.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t healthcare-backend:latest .'
            }
        }
        stage('Stop and Remove Existing Container') {
            steps {
                script {
                    def containerExists = sh(script: "docker ps -a -q --filter 'name=healthcare-backend'", returnStdout: true).trim()
                    if (containerExists) {
                        sh 'docker stop healthcare-backend || true'
                        sh 'docker rm healthcare-backend || true'
                    }
                }
            }
        }
        stage('Run Backend') {
            steps {
                sh 'docker run -d -p 9090:9090 --name healthcare-backend healthcare-backend:latest'
            }
        }
    }
}

