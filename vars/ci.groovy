def call() {
   node ('workstation') {
      stages {
        if (env.Branch_name == "main"): {
        stage('codecheckout'){}
        stage('compile'){}
        stage('testcases'){}
      } else:
        stage('codecheckout'){}
        stage('compile'){}
        stage('integrationtestcases'){}
        stage('build'){}
        stage('releaseapp'){}
      }

   }

}