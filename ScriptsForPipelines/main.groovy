#!/usr/bin/env groovy
//Leave the above line alone.  It identifies this as a groovy script.
node 

{
 stage ('Pre-Clean')
  {
  //echo "Hello World!"
  // stage ('UTF'){
        RunVI(viPath)    
      }
  
  }
}
