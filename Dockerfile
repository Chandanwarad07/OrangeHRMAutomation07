pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/Chandanwarad07/Jenkinsandjava.git'
        AWS_REGION = 'ap-south-1'
        AWS_ACCOUNT_ID = '399747939119'
        ECR_REPO_NAME = 'test-images'
        IMAGE_TAG = 'latest'
        IMAGE_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}"
    }

    stages {
        stage('Check Tools') {
            steps {
                sh '''
                    set -e
                    java -version
                    git --version
                    mvn -version
                    docker --version
                    aws --version
                '''
            }
        }

        stage('Clone Repository') {
            steps {
                git branch: 'main', url: "${GIT_REPO}"
            }
        }

        stage('Build Java Application') {
            steps {
                sh '''
                    set -e
                    echo "Building Java application..."
                    mvn clean -B -Denforcer.skip=true package
                    ls -lh target/
                '''
            }
        }

        stage('Check AWS IAM Role') {
            steps {
                sh '''
                    set -e
                    aws sts get-caller-identity --region "$AWS_REGION"
                '''
            }
        }

        stage('Login to AWS ECR') {
            steps {
                sh '''
                    set -e
                    aws ecr get-login-password \
                        --region "$AWS_REGION" |
                    docker login \
                        --username AWS \
                        --password-stdin \
                        "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    set -e
                    docker build -t "$IMAGE_URI" .
                '''
            }
        }

        stage('Push Docker Image to ECR') {
            steps {
                sh '''
                    set -e
                    docker push "$IMAGE_URI"
                '''
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Image pushed to private ECR project1.'
        }
        failure {
            echo 'FAILURE: Check the failed stage in Console Output.'
        }
    }
}