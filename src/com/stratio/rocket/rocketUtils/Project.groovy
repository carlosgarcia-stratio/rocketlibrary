package com.stratio.rocket.rocketUtils

class Project implements Serializable {

    String projectString;
    Map projectJson;

    void init(String project, Map projectJson) {
        this.projectString = project
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
