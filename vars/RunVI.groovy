#!/usr/bin/env groovy
import groovy.json.JsonOutput

def call(viName) {

        echo 'Running VI '+ viName

        def vi_run_json = JsonOutput.toJson(['VI Name': "${vipName}"])//"LabVIEW Version":"${lvVersion}"])

        echo vi_run_json       

        def vip_run_response = httpRequest "http://localhost:8002/LabVIEWCIService/VIP_INSTALL?JSON="+java.net.URLEncoder.encode(vi_run_json, "UTF-8").replaceAll("\\+", "%20")

        println("Status: "+ vip_run_response.status)

        println("Content: "+vip_run_response.content)       

        echo 'Magic wait of 5 seconds...'

        sleep(5)

}
