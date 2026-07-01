pipeline {

    agent any

    tools {
        jdk 'java'
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarScanner'

                    withSonarQubeEnv('SonarQube') {
                        withCredentials([string(credentialsId: 'sonar-token-orangehrm', variable: 'SONAR_TOKEN')]) {
                            bat """
                            "${scannerHome}\\bin\\sonar-scanner.bat" ^
                            -Dsonar.projectKey=OrangeHRMAutomation ^
                            -Dsonar.projectName=OrangeHRMAutomation ^
                            -Dsonar.sources=src ^
                            -Dsonar.java.binaries=target ^
                            -Dsonar.token=%SONAR_TOKEN%
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline Success'
        }

        failure {
            echo 'Pipeline Failed'
        }
    }
}