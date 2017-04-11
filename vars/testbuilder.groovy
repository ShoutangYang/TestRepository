import groovy.json.JsonOutput

def call(path){
        echo 'Run basic tests before build'
        
        
        def build_json = JsonOutput.toJson([Executor_Number: env.EXECUTOR_NUMBER.toString(), 'Workspace_Path' : env.WORKSPACE+'\\build_temp' , 'Build_Spec':"test-build", 'Target': "My Computer" , 'Project_Path': "${WORKSPACE}\\${path}" , 'Workspace_Path': env.WORKSPACE+'\\build_temp', 'Auto_Version_Increment': "true".toBoolean])
        echo build_json
      //  Executor_Number, Workspace_Path, Build_Spec, Target, Project_Path, Auto_Version_Increment - boolean, 
        
        def build_response = httpRequest "http://localhost:8002/LabVIEWCIService/LabVIEW_Build?JSON="+java.net.URLEncoder.encode(build_json, "UTF-8").replaceAll("\\+", "%20")
        println("Status: "+build_response.status)
        println("Content: "+build_response.content)
        
        //echo 'Junit'
        //junit allowEmptyResults: true, testResults: 'build_temp\\utf_results.xml'

        echo 'Magic delay of 5 seconds to let LabVIEW breathe'
        sleep(5)
        echo 'Done waiting.'
}      
