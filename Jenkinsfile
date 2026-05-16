pipeline {
    agent any

    tools {
        jdk 'JDK 25'
        maven 'Maven3'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git 'https://github.com/aratilodha1997-coder/RestAssured.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Test Cases') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                bat 'mvn surefire-report:report'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }

        success {
            echo 'Build executed successfully'
        }

        failure {
            echo 'Build failed'
        }
    }
}