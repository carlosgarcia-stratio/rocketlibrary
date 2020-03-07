package com.stratio.rocket.rocketUtils

import com.stratio.rocket.http.HttpClient
import com.stratio.rocket.http.HttpRequest
import groovy.transform.Field

@Field def instance = [:]

def initialize(String url, String cookieCredentials) {

    instance["url"] = url
    instance["cookieCredentials"] = cookieCredentials
}

//Releases

def String getWorkflowRelease(String releaseId) {
    String request = new HttpRequest().getCommand()
                        .get()
                        .insecure()
                        .url("${instance['url']}/release/workflow/${releaseId}")
                        .getCommand()

    new HttpClient().execute(request)
}

//workflows

return this