pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Riya-R/DevOps_exp10.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat 'mvn sonar:sonar -Dsonar.projectKey=demo-project -Dsonar.host.url=http://localhost:9000 -Dsonar.login=YOUR_TOKEN'
            }
        }
    }
}
