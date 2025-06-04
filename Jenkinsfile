def version = "v0.${BUILD_NUMBER}"

pipeline {
    agent any

    environment 
    {
        VERSION = "${version}"  // Add version to the environment
    }

    stages 
    {
        stage("Check Source") 
        {
            steps 
            {
                echo "First Stage"
                git url: 'https://github.com/tuanhung2005/No.01', branch: 'main', credentialsId: 'github-pat'
            }
        }

        stage("Scan + Test Code ...")
        {
            steps 
            {
                echo "Test Backend"
                echo "Scan code with SonarQuebe"
                echo "..... vv and may may ......"
            }
        }

        stage("Build & Push Image") 
        {
            steps 
            {
                echo "Build stage"
                script 
                {
                   
                        echo "Starting building image !!!"
                        if (isUnix()) {
                            sh "sudo docker build -t $IMAGE_NAME:$VERSION . && sudo docker push $IMAGE_NAME:$VERSION"
                        } else {
                            bat "./gradlew clean build"
                            bat "docker build -t $IMAGE_NAME:$VERSION . && docker push $IMAGE_NAME:$VERSION"
                        }
                   
                    
                }
            }
        }


        stage("Deploy on server asus") 
        {
            steps {
                echo "Deploy new image !!!"

        
            }
        }
    }
}
