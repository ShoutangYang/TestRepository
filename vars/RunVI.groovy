#!/usr/bin/env groovy
import groovy.json.JsonOutput

def call(path) {

       // echo 'Running VI '+ viName

        //def vi_run_json = JsonOutput.toJson(['VI Name': "${viName}"])//"LabVIEW Version":"${lvVersion}"])
        def vi_run_json = JsonOutput.toJson([Executor_Number: env.EXECUTOR_NUMBER.toString(),'VI_Path': env.WORKSPACE+"\\"+path, 'Wait_Until_Done': "true".toBoolean()])
        //"${WORKSPACE}\\${path}"
       echo vi_run_json       

        def vi_run_response = httpRequest "http://localhost:8002/LabVIEWCIService/Run_VI?JSON="+java.net.URLEncoder.encode(vi_run_json, "UTF-8").replaceAll("\\+", "%20")

        println("Status: "+ vi_run_response.status)

        println("Content: "+vi_run_response.content)       

        echo 'Magic wait of 5 seconds...'

        sleep(5)

}
