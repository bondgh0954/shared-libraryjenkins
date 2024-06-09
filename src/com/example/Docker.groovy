#!/user/bin/env groovy

package com.example

class Docker implements serializable {
    def script
    Docker(script){
        this.script = script
    }

    buildJar(){
        script.echo 'building application artifact'
        script.sh 'mvn package'
    }
    dockerLogin(){
        script.echo 'logging to dockerhub repository'
        script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASSWORD')]){
            script.sh "echo '${script.PASSWORD}'| docker login -u '${script.USER}' --password-stdin"
        }
    }
    buildImage(string imageName){
        script.echo 'building application image'
        script.sh "docker build -t $imageName ."

    }
    pushImage(string imageName){
        script.echo 'pushing the image into docker hub'
        script.sh "docker push $imageName"
    }
}