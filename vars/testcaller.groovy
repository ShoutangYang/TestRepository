#!/usr/bin/env groovy
//Leave the above line alone.  It identifies this as a groovy script.


def call(viPath,projPath)
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
     //  RunVI(viPath)
      }
    stage ('LabVIEW Build')
      {
      testbuilder(projPath)
      }
     stage('PostClean')
      {
      postClean()
      }
  }
}
