pipeline {
    agent any
     tools {
        maven "MAVEN"
    }

    stages {
        stage("version of software"){
            steps{
                sh "java --version"
                sh "mvn --version"
                sh "docker --version"
                sh "docker-compose --version"
            }
        }
        stage("Build the project"){
            steps{
                sh "mvn clean"
                sh "mvn package"
            }
        }
        stage("run the docker containers"){
            steps{
                sh "docker-compose down"
                sh "docker-compose up --build -d"       
            }
        }
        stage("Check images and runnign containers"){
            steps{
                sh "docker images"
                sh "docker ps"       
            }
        }
    }
}
