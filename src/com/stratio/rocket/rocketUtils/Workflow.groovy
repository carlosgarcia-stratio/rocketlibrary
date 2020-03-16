package com.stratio.rocket.rocketUtils

import groovy.json.JsonOutput

class Workflow implements Serializable {

    String wfString;
    Map wfJson;
    String releaseId;

    void init(String wf, Map wfJson, String releaseId) {
        this.wfString = wf
        this.wfJson = wfJson
        this.releaseId = releaseId
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

    String getSettings(Boolean escaped = false) {
        return escaped ? JsonOutput.toJson(wfJson["settings"].toString()) : wfJson["settings"].toString()
    }

    String getPipelineGraph(Boolean escaped = false) {
        return escaped ? JsonOutput.toJson(wfJson["pipelineGraph"].toString()) : wfJson["pipelineGraph"].toString()
    }

    String getExecutionEngine() {
        return wfJson["executionEngine"]
    }

    String getWorkflowType() {
        return wfJson["workflowType"]
    }

    Long getVersion() {
        return wfJson["version"].toLong()
    }

    String getGroup() {
        return wfJson["group"].toString()
    }

    String getTags() {
        return wfJson["tags"].toString()
    }

    String getWorkflowMasterId() {
        return wfJson["workflowMasterId"]
    }

}
