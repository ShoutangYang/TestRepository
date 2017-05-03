#!/usr/bin/env groovy
//Leave the above line alone.  It identifies this as a groovy script.
//Call from Jenkinsfile is testcaller(viPath, projPath, buildTarget, buildSpec)

def call(viPath, projPath, buildTarget, buildSpec)
{
node
  {
    stage('PreClean')
      {
      preClean()
      }
    stage ('SCM_Checkout'){
        echo 'Attempting to get source from repo...'
        checkout scm
      }
    stage ('Temp Directories')
      {
      bat 'mkdir build_temp'
      }
    stage ('Run VI')
      {
       echo 'Attempting to build the VI specified'
        runVI(viPath)
      }
    stage ('LabVIEW Build')
      {
        echo 'Attempting to build' + {$buildSpec} + ' with the target' + {$buildTarget}
      buildSpec(projPath, buildTarget, buildSpec)
      }
     stage('PostClean')
      {
      postClean()
      }
  }
}