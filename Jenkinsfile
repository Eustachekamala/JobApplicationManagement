pipeline {
    agent any

    tools {
        maven 'Maven-3.9.11'
        jdk 'jdk-21'
    }

    stages {
        stage('Build Maven') {
            environment {
                SPRING_DATASOURCE_URL = "jdbc:postgresql://ep-jolly-lake-ad3k8uoo-pooler.c-2.us-east-1.aws.neon.tech/jobApplicationDB"
            }
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/Eustachekamala/JobApplicationManagement']]
                )

                withCredentials([
                    usernamePassword(
                        credentialsId: 'job_application_system',
                        usernameVariable: 'SPRING_DATASOURCE_USERNAME',
                        passwordVariable: 'SPRING_DATASOURCE_PASSWORD'
                    )
                ]) {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'docker build -t eustachekamala/job-application-management-system .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub_credentials',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push eustachekamala/job-application-management-system
                    '''
                }
            }
        }
    }
}
