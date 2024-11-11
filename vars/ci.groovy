def call() {
    node('workstation'){ 
       if(BRANCH_NAME == 'main')
    {
         sh'env'   
        // Stage to check out the source code from the repository
        stage('Code Checkout') {
            echo 'Checking out the source code...'
        }

        // Stage to compile the code
        stage('Compile') {
            echo 'Compiling the source code...'
            // Example compilation command (replace with actual command for your project)
            
        }

    } else if { (BRANCH_NAME ==~ "PR.*")

      sh'env'   
        // Stage to check out the source code from the repository
        stage('Code Checkout') {
            echo 'Checking out the source code...'
        }

        // Stage to compile the code
        stage('Compile') {
            echo 'Compiling the source code...'
            // Example compilation command (replace with actual command for your project)
            
        }

        stage('Release') {
            echo 'Releasing the build...'
            // Example release or deployment script
             // Or `scp`, `kubectl apply`, etc.
        }
        

    } else { 
         sh'env'     
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
