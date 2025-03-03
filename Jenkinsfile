pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/opt/homebrew/bin"
    }

    stages {
        stage('Check Docker') {
            steps {
                sh 'echo "Docker Path: $(which docker)"'
                sh 'docker --version'
            }
        }
        
        stage('Checkout Code') {
            steps {
                git branch: 'develop', credentialsId: 'github-credentials', url: 'https://github.com/akashn34/healthcare-app.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
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


