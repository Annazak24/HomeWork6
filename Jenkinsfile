pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-21'
        }
    }

    tools {
        allure 'Allure 2.30'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                sh 'echo "========================="'
                sh 'echo "ALLURE DEBUG START"'
                sh 'echo "========================="'
                sh 'ls -la target || true'
                sh 'ls -la target/allure-results || true'
                sh 'find target/allure-results -type f || true'
                sh 'echo "========================="'
                sh 'echo "ALLURE DEBUG END"'
                sh 'echo "========================="'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true

            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}