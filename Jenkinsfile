pipeline {
    agent { 
        kubernetes{
            label 'jenkins-slave'
        }
        
    }
    environment{
        DOCKER_USERNAME = 'brainupgrade'
        DOCKER_PASSWORD = credentials('docker-brainupgrade')
    }
    stages {
        stage('docker login') {
            steps{
                sh(script: """
                    docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
                """, returnStdout: true) 
            }
        }
        stage('code checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/jenkins']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/brainupgrade-in/weather-service.git']]])
                // sh 'mvn clean install'
            }
        }
        stage('code build') {
            steps{
                sh script: '''
                #!/bin/bash
                cd $WORKSPACE
                mvn clean install
                '''
            }
        }
        stage('docker build') {
            steps{
                sh script: '''
                #!/bin/bash
                cd $WORKSPACE
                docker build -t brainupgrade/weather-services:jenkins-${BUILD_NUMBER} .
                '''
            }
        }

        stage('docker push') {
            steps{
                sh(script: """
                    docker push brainupgrade/weather-services:jenkins-${BUILD_NUMBER}
                """)
            }
        }

        stage('deploy') {
            steps{
                sh script: '''
                #!/bin/bash
                cd $WORKSPACE/weather-services/
                #get kubectl for this demo
                curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
                chmod +x ./kubectl
                ./kubectl apply -f $WORKSPACE/weather-service/kubernetes/deploy.yaml
                '''
        }
    }
}
}
