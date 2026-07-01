pipeline {

    agent any

    tools {
        jdk 'java'
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Project...'
                bat 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {

                    def scannerHome = tool 'SonarScanner'

                    withSonarQubeEnv('SonarQube') {

                        bat """
                        "${scannerHome}\\bin\\sonar-scanner.bat" ^
                        -Dsonar.projectKey=OrangeHRMAutomation ^
                        -Dsonar.projectName=OrangeHRMAutomation ^
                        -Dsonar.projectVersion=1.0 ^
                        -Dsonar.sources=src ^
                        -Dsonar.java.binaries=target ^
                        -Dsonar.token=%SONAR_AUTH_TOKEN%
                        """

                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'reports/*.html', fingerprint: true
                archiveArtifacts artifacts: 'screenshots/*.png', fingerprint: true
            }
        }

    }

    post {

        always {
            junit 'test-output/junitreports/*.xml'
        }

        success {
            echo 'Pipeline Executed Successfully'
        }

        failure {
            echo 'Pipeline Failed'
        }
    }
}