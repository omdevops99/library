def call() {
    node('workstation'){
        sh "find . -mindepth 1 | xargs rm -rf"
        if(env.TAG_NAME ==~ ".*"){
           env.branch_name = "/refs/tags/${env.TAG_NAME}"
        } else 
          if(env.branch_name ==~ "PR-.*"){
            env.branch_name = "${env.CHANGE_BRANCH}"
          }
        else{
            env.branch_name = "${BRANCH_NAME}"
        }
        // Stage to check out the source code from the repository
        stage('Code Checkout') {
            checkout scmGit(branches: [[name: "${env.branch_name}"]], 
            extensions: [], 
            userRemoteConfigs: [[url: "https://github.com/omdevops99/${repo_name}.git"]])
        }
        sh  'cat Jenkinsfile'

        if(app_type == "nodejs"){
        stage('Download Dependencies') {
            sh 'npm install'
            // Example compilation command (replace with actual command for your project)  
        } 
        }
        // Stage to compile the code
        if(env.JOB_BASE_NAME ==~ "PR.*"){
           sh 'echo PR'
        stage('Release') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        } 
        stage('code quality') {
         //wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: sonar_token ]]])
          {
         sh 'sonar-scanner -Dsonar.host.url=http://172.31.37.111:9000 \
         -Dsonar.token=squ_d76c2ac190d03e20d46a4b26a8d0aba11e00cf87 \
         -Dsonar.projectKey=${repo_name}.git \
         -Dsonar.exclusions=node_modules/** \
         -Dsonar.qualitygate.wait=true'  
}    
        
        }     
        } else if(env.branch_name == "main"){
        sh 'echo main'
        // Stage to check out the source code from the repository
        stage('test') {
            echo 'Checking out the source code...'
        }

        // Stage to compile the code
        stage('build') {
            echo 'Compiling the source code...'
            // Example compilation command (replace with actual command for your project)
            
        }

    }  else if(env.TAG_NAME ==~ ".*") {
        sh 'echo TAG'
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
        sh 'echo branch'    
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
