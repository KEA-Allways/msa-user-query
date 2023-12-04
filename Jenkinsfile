pipeline {
    agent any



    environment {
        
        
        //서버 정보
        ip = "44.201.129.31"
        username = "ubuntu"
        
        //스프링 서버 정보
        springname = "user-query"
        port = "8084"
        
        //도커 정보
        imagename = "user-query-img"
        dockerCredential = 'docker-access-token'
        dockerImage = ''
        tagname = "dev"
        
        //깃 정보
        giturl = 'https://github.com/KEA-Allways/msa-user-query.git/'
        gitCredential = "github-access-token"
        branchname = "prod"

        //소나 큐브
        sonarqubeCredential = "sonarqube-access-token"
        sonarqubeUrl = "http://18.204.16.65:9000"
    }

    stages {
        // git에서 repository clone
        stage('Prepare') {
          steps {
            echo 'Clonning Repository'
              git url: giturl,
              branch: branchname,
              credentialsId: gitCredential
            }
            post {
             success { 
               echo 'Successfully Cloned Repository'
             }
           	 failure {
               error 'This pipeline stops here...'
             }
          }
        }

        stage('sonarqube-server') {
            steps {
               withSonarQubeEnv(credentialsId: sonarqubeCredential) {
                    sh './gradlew sonarqube -Dsonar.projectKey=msa-user-query -Dsonar.host.url=sonarqubeUrl -Dsonar.login=sonarqubeCredential'
                }
            }
        }

        // gradle build
        stage('Bulid Gradle') {
          steps {
            echo 'Bulid Gradle'
            dir ('.'){
                sh """
                ./gradlew clean build --exclude-task test
                """
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }
        
        // docker build
        stage('Bulid Docker') {
          steps {
            echo 'Bulid Docker'
            script {
                imagename = "jmk7117/${imagename}"
                dockerImage = docker.build imagename
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        // docker push
        stage('Push Docker') {
          steps {
            echo 'Push Docker'
            script {
                docker.withRegistry( '', dockerCredential) {
                    dockerImage.push("dev")
                }
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }
        
        stage('Run Container on Dev Server') {
          steps {
            echo 'Run Container on Dev Server'
            
            sshagent(['ec2-ssh']) {
            
                sh 'ssh -o StrictHostKeyChecking=no ${username}@${ip} "whoami"'

                sh "ssh -o StrictHostKeyChecking=no ${username}@${ip} 'docker ps -f name=${springname} -q | xargs --no-run-if-empty docker container stop'"
                sh "ssh -o StrictHostKeyChecking=no ${username}@${ip} 'docker container ls -a -fname=${springname} -q | xargs --no-run-if-empty docker container rm'"
                sh "ssh -o StrictHostKeyChecking=no ${username}@${ip} 'docker images -f reference=${imagename}:${tagname} -q | xargs --no-run-if-empty docker image rmi'"
                
                
                sh "ssh -o StrictHostKeyChecking=no ${username}@${ip} 'docker pull ${imagename}:${tagname}'"
                sh "ssh -o StrictHostKeyChecking=no ${username}@${ip} 'docker run -d -p 80:${port} -p ${port}:${port} --name ${springname} ${imagename}:${tagname}'"
            }
          }

          post {
                  success {
                      slackSend (
                          channel: '#jenkins',
                          color: '#00FF00',
                          message: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"


                      )
                  }
                  failure {
                      slackSend (
                          channel: '#jenkins',
                          color: '#FF0000',
                          message: "FAIL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"
                      )
                  }
              }
          
        }
    }
}