package com.stratio.rocket.rocketUtils

class Release implements Serializable {

    String releaseString;
    Map releaseJson;

    void init(String release, Map releaseJson) {
        this.releaseString = release
        this.releaseJson = releaseJson
    }

    def getId() {
        return releaseJson["id"]
    }

    def getWorkflowVersionId() {
        return releaseJson["workflowVersionId"]
    }

    def getStages() {
        return releaseJson["stages"]
    }

    def getCreationUser(){
        return releaseJson["creationUser"]
    }

    def getAdditionalInfo(){
        return releaseJson["addtionalInfo"]
    }

    def getWorkflowState(){
        return releaseJson["workflowState"]
    }

    def getExecutionState(){
        return releaseJson["executionState"]
    }

    def setExecutionState(String state) {
        releaseJson["executionState"] = state
    }
}
