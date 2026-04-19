pipeline {
    agent {
        docker {
            image 'mcr.microsoft.com/playwright/java:v1.49.0-noble'
            args '--ipc=host'
        }
    }

    tools {
        allure 'Allure 2.30'
    }

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage('Run UI Tests') {
            steps {
                sh 'mvn clean test -Dtest=CatalogPageTest,CompanyServicesTest,HousePageTest,SubscriptionPageTest -Dmaven.test.failure.ignore=true'
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