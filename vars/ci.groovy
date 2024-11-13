def call() {
    node('workstation'){
        sh 'env'
        sh "find . -mindepth 1 | xargs rm -rf"
        if(env.TAG_NAME == '.*'){
           env.branchName = "/refs/tags/${env.TAG_NAME}"
        } else 
         sh 'env'
          if(env.branchName = 'PR.*'){
            env.branchName = "${env.CHANGE_BRANCH}"
          }
        } else{
            env.branchName = "${BRANCH_NAME}"
        }
        // Stage to check out the source code from the repository
        stage('Code Checkout') {
            checkout scmGit(branches: [[name: "${env.branchName}"]], 
            extensions: [], 
            userRemoteConfigs: [[url: 'https://github.com/omdevops99/expense_jenkins.git']])
        }
        sh  'cat Jenkinsfile'
        // Stage to compile the code
        stage('Compile') {
            echo 'Compiling the source code...'
            // Example compilation command (replace with actual command for your project)
            
        }
       if(env.BRANCH_NAME == "main")
    {
        
        // Stage to check out the source code from the repository
        stage('test') {
            echo 'Checking out the source code...'
        }

        // Stage to compile the code
        stage('build') {
            echo 'Compiling the source code...'
            // Example compilation command (replace with actual command for your project)
            
        }

    } else if  (env.BRANCH_NAME ==~ "PR.*") {
        stage('Release') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        }  
        

    } else if  (env.TAG_NAME ==~ ".*") {

   
        // Stage to check out the source code from the repository
        stage('Build') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        }

        stage('Release') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        } 
        } else { 
            
        // Stage to run tests
        stage('Test') {
            echo 'Running tests...'
            // Example test command (replace with your testing framework)
            // Or `mvn test`, `npm test`, etc.
        }

        // Stage to build the artifact/package
        stage('Build') {
            echo 'Building the package...'
            // Example build command
             // Or `mvn package`, `gradle build`, etc.
        }
        // Stage to release/deploy the build
        stage('Release') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        }
        }
    }
}
