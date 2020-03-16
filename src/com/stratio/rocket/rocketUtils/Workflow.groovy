package com.stratio.rocket.rocketUtils

import groovy.json.JsonOutput

class Workflow implements Serializable {

    String wfString;
    String wfJson;
    String releaseId;

    def init(String wf, String releaseId) {
        this.wfString = wf
        this.releaseId = releaseId
        this.wfJson = readJSON text: wf
    }


    String getId() {
        return wfJson["id"]
    }

    String getReleaseId() {
        return this.releaseId
    }

    String getProjectId() {
        return wfJson["projectId"]
    }

    String getName() {
        return wfJson["name"]
    }

    String getDescription() {
        return wfJson["description"]
    }

    String getGroupName() {
        return wfJson["group"]["name"]
    }

    String getAsEscapedJson(){
        return JsonOutput.toJson(this.wfString)
    }

}
