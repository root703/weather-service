node('jenkins-slave') {
    
     stage('build') {
        sh(script: """
            echo "hello"
           git clone https://github.com/brainupgrade-in/weather-service.git
           cd ./brainupgrade-in/weather-service
           mvn clean install
           docker build . -t brainupgrade-in/weather-service
        """)
    }
}
