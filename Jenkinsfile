pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
      
        stage('Build') {
          steps {
            git(url: 'https://github.com/brainupgrade-in/weather-service.git', branch: 'main')
            build 'mvn clean install'
          }
        }

      }
    }

    stage('Test') {
      steps {
        echo 'Testing..'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploying....'
      }
    }

  }
}
