package com.stratio.rocket.rocketUtils

class Project implements Serializable {

    String projectString;
    String projectJson;

    def init(String project) {
        this.projectString = project
        this.projectJson = readJSON text: project
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
