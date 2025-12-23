pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/udhayaeugine/CRUDwithDynamoDB.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                bat 'copy target\\CRUDwithDynamoDB-1.0-SNAPSHOT.jar C:\\deploy\\'
            }
        }
    }
}
