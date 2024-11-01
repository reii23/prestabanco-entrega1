pipeline {
    agent any
    tools {
        maven "maven"
    }
    stages {
        stage("Build JAR File") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/reii23/prestabanco-entrega1']])
                dir("back/managment") {
                    bat "mvn clean install"
                }
            }
        }
        stage("Test") {
            steps {
                dir("back/managment") {
                    bat "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image") {
            steps {
                dir("back/managment") {
                    script {
                        withDockerRegistry(credentialsId: 'docker-credentials') {
                            bat "docker build -t reii23/spring-image ."
                            bat "docker push reii23/spring-image"
                        }
                    }
                }
            }
        }
    }
}
