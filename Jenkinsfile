pipeline {
    agent any

parameters {
choice(
name: 'ACTION',
choices:['Checkout', 'Build', 'Test', 'Deploy', 'Promote'],
description:'Select the action you want to perform'
)
}

    stages {
        stage('Checkout') {
        when {expression { params.ACTION == 'Checkout'}}
            steps {
            echo "Performing Checkout..."
            git url: 'https://github.com/udhayaeugine/CRUDwithDynamoDB.git', branch: 'develop'
            }
        }

        stage('Build') {
        when {expression { params.ACTION == 'Build'}}
            steps {
                echo "Performing Build..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
                when {expression { params.ACTION == 'Test'}}
            steps {
                echo "Performing Test..."
                bat 'mvn test'
            }
        }

        stage('Deploy') {
                when {expression { params.ACTION == 'Deploy'}}
            steps {
                echo "Performing Deploy..."
                bat 'copy target\\CRUDwithDynamoDB-1.0-SNAPSHOT.jar C:\\deploy\\'
            }
        }
    }
     post {
            always {
                echo "Pipeline completed for ACTION = ${params.ACTION}"
            }
        }

}
