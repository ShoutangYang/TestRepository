#!/usr/bin/env groovy
//Leave the above line alone.  It identifies this as a groovy script.


def call(viPath)
{
node
  {
    stage('PreClean')
      {
      preClean()
      }
    stage ('Temp Directories')
      {
     //  bat 'mkdir build_temp'
      }
    stage ('Run VI')
      {
       RunVI(viPath)
      }
     stage('PostClean')
      {
      postClean()
      }
  }
}
