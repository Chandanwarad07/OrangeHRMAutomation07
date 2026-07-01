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

                        bat """
                        "${scannerHome}\\bin\\sonar-scanner.bat" ^
                        -Dsonar.projectKey=OrangeHRMAutomation ^
                        -Dsonar.projectName=OrangeHRMAutomation ^
                        -Dsonar.sources=src ^
                        -Dsonar.java.binaries=target
                        """

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