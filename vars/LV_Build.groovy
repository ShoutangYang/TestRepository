#!/usr/bin/env groovy
import groovy.json.JsonOutput

def call(projPath, buildTarget, buildSpec)
{
        echo 'Run basic tests before build'
        
        echo env.WORKSPACE+'\\build_temp'

        def build_json = JsonOutput.toJson(["Executor_Number": env.EXECUTOR_NUMBER.toString(), "Workspace_Path" : env.WORKSPACE+'\\build_temp' , "Build_Spec": "${buildSpec}", 'Target': "${buildTarget}" , 'Project_Path': "${WORKSPACE}\\${projPath}" , "Workspace_Path": env.WORKSPACE+'\\build_temp', "Auto_Version_Increment": "true".toBoolean()])
        echo build_json
      
 
        //The following lines post the response from the CI Service. A successful build will return code 200. 
        //It will return the error along with error code 500. Error code 408 indicates that the CI service has not been started. 
        def lvb_response = httpRequest validResponseCodes: "200,500", url: "http://localhost:8002/LabVIEWCIService/LabVIEW_Build?JSON="+java.net.URLEncoder.encode(lvBuild_json, "UTF-8").replaceAll("\\+", "%20")
        println("Status: "+lvb_response.status)
        println("Content: "+lvb_response.content)

        if (lvb_response.status!=200)
        {
        error("Call to CI Server method LabVIEW_Build failed with error: "+lvb_response.content)
        }

        echo 'Magic delay of 5 seconds to let LabVIEW breathe'
        sleep(5)
        echo 'Done waiting.'
}      
