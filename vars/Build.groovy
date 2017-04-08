#!/usr/bin/env groovy
import groovy.json.JsonOutput

def call(path) {

       // echo 'Running VI '+ viName
      // {"Executor_Number":"11231","Workspace_Path":"3","Build_Spec":"3","Target":"3","Project_Path":"3","Auto_Version_Increment":false} 

        def vi_run_json = JsonOutput.toJson([Executor_Number: env.EXECUTOR_NUMBER.toString(),"Workspace_Path": "${WORKSPACE}","Build_Spec":"executable-test","Target":"My Computer","Project_Path":"3","${WORKSPACE}\\${path}","Auto_Version_Increment":false}
        
    //    'VI_Path': "${WORKSPACE}\\${path}", 'Wait_Until_Done': "false".toBoolean()])
        echo build_json       

        def build_response = httpRequest "http://localhost:8002/LabVIEWCIService/Run_VI?JSON="+java.net.URLEncoder.encode(vi_run_json, "UTF-8").replaceAll("\\+", "%20")

        println("Status: "+ vi_build.status)

        println("Content: "+vi_build.content)       

        echo 'Magic wait of 5 seconds...'

        sleep(5)

}
