pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-south-1'
        AWS_ACCOUNT_ID = '399747939119'
        ECR_REPO_NAME = 'test-images'
        IMAGE_TAG = 'latest'
        IMAGE_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}"
        CLUSTER_NAME = 'orangehrm-cluster'
    }

    stages {
        stage('Check Tools') {
            steps {
                sh '''
                    set -e
                    echo "Checking installed tools on Jenkins server..."
                    java -version || true
                    git --version || true
                    mvn -version || true
                    docker --version || true
                    aws --version || true
                    kubectl version --client || true
                '''
            }
        }

        stage('Build Java Application') {
            steps {
                sh '''
                    set -e
                    echo "Compiling test automation suite..."
                    mvn clean test-compile -DskipTests
                '''
            }
        }

        stage('Verify AWS IAM Role') {
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
                    echo "Authenticating Docker with ECR..."
                    aws ecr get-login-password --region "$AWS_REGION" | docker login --username AWS --password-stdin "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    set -e
                    echo "Building Docker image with Headless Chrome..."
                    docker build -t "$IMAGE_URI" .
                '''
            }
        }

        stage('Push Docker Image to ECR') {
            steps {
                sh '''
                    set -e
                    echo "Pushing Docker image to Amazon ECR..."
                    docker push "$IMAGE_URI"
                '''
            }
        }

        stage('Deploy & Run Tests on Amazon EKS') {
            steps {
                sh '''
                    set -e
                    echo "Connecting to EKS cluster: $CLUSTER_NAME..."
                    aws eks update-kubeconfig --region "$AWS_REGION" --name "$CLUSTER_NAME"

                    echo "Cleaning up any previous test job..."
                    kubectl delete job orangehrm-automation-job --ignore-not-found=true

                    echo "Submitting new Test Automation Job to EKS..."
                    kubectl apply -f eks-test-job.yaml

                    echo "Waiting for test runner pod to be scheduled..."
                    sleep 10
                    kubectl get pods -l app=orangehrm-test || true

                    echo "Streaming live test execution logs from EKS..."
                    kubectl logs -f job/orangehrm-automation-job --all-containers=true || true

                    echo "Checking test job completion..."
                    kubectl wait --for=condition=complete job/orangehrm-automation-job --timeout=600s
                '''
            }
        }
    }

    post {
        always {
            sh '''
                echo "Final Pod Status:"
                kubectl get pods -l app=orangehrm-test || true
                echo "Job Status:"
                kubectl get job orangehrm-automation-job || true
            '''
        }
        success {
            echo "SUCCESS: OrangeHRM Automation tests successfully executed on Amazon EKS!"
        }
        failure {
            echo "FAILURE: Check the failed stage in the Jenkins console output."
        }
    }
}