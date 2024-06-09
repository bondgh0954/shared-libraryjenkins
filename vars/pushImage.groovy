#!/user/bin/env groovy

import com.example.Docker

def call(string imageName){
    return new Docker(this).pushImage(imageName)
}