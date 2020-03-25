package com.stratio.rocket.rocketUtils

import groovy.json.JsonOutput

class Workflow implements Serializable {

    Map wfJson;

    void init(Map wfJson) {
        this.wfJson = wfJson
    }

    String getId() {
        return wfJson["id"]
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

    String getGroup(Boolean escaped = false) {
        return escaped ? JsonOutput.toJson(wfJson["group"].toString()) : wfJson["group"].toString()
    }

    String getTags() {
        return wfJson["tags"].toString()
    }

    String getWorkflowMasterId() {
        return wfJson["workflowMasterId"]
    }

}
