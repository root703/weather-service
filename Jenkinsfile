pipeline {

  environment {
    registry = "brainupgrade/weather-service"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/brainupgrade-in/weather-service.git'
        sh 'mvn clean install'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }

    stage('Push Image') {
      steps{
        script {
          docker.withRegistry( "" ) {
            dockerImage.push()
          }
        }
      }
    }

    stage('Deploy App') {
      steps {
        script {
          kubernetesDeploy(configs: "deploy.yaml", kubeconfigId: "jenkinskubeconfig")
        }
      }
    }

  }

}
