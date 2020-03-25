package com.stratio.rocket.rocketUtils

class Group implements Serializable {

    String groupString;
    Map groupJson;

    void init(String group, Map groupJson) {
        this.groupString = group
        this.groupJson = groupJson
    }

    String getId() {
        return groupJson["id"]
    }

    String getName() {
        return groupJson["name"]
    }
}
