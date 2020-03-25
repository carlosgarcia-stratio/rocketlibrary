package com.stratio.rocket.rocketUtils

class Project implements Serializable {

    Map projectJson;

    void init(Map projectJson) {
        this.projectJson = projectJson
    }

    String getId() {
        return projectJson["id"]
    }

    String getName() {
        return projectJson["name"]
    }

    String getDescription() {
        return projectJson["description"]
    }

    String getGroupId(){
        return projectJson["groupId"]
    }
}
