def call()
   node('workstation') { 
      stages {
         stage('codecheckout'){}
         stage('compile'){}
         stage('test'){}
         stage('build'){}
         stage('release'){}
      }

   }
   
