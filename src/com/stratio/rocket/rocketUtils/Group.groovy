package com.stratio.rocket.rocketUtils

class Group implements Serializable {

    Map groupJson;

    void init(Map groupJson) {
        this.groupJson = groupJson
    }

    String getId() {
        return groupJson["id"]
    }

    String getName() {
        return groupJson["name"]
    }

}
