def call() {
   node('workstation') {
      stages {
         stage('codecheckout') {
            steps {
               echo "Checking out code..."
            }
         }
         stage('compile') {
            steps {
               echo "Compiling the code..."
            }
         }

         // Use a script block for conditional logic
         script {
            if (env.Branch_name == "main") {
               stage('testcases') {
                  steps {
                     echo "Running test cases for the main branch..."
                  }
               }
            } else {
               stage('integrationtestcases') {
                  steps {
                     echo "Running integration test cases for non-main branch..."
                  }
               }
               stage('build') {
                  steps {
                     echo "Building the application for non-main branch..."
                  }
               }
               stage('releaseapp') {
                  steps {
                     echo "Releasing the application for non-main branch..."
                  }
               }
            }
         }
      }
   }
}
