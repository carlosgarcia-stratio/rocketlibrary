package com.stratio.rocket.rocketUtils

import com.stratio.rocket.http.HttpClient
import com.stratio.rocket.http.HttpRequest
import groovy.transform.Field

@Field def instance = [:]
@Field def isActive = false

def initialize(String url, String cookieCredentials) {

    instance["url"] = url
    instance["cookieCredentials"] = cookieCredentials
    isActive = true
}

//Releases

def String getWorkflowRelease(String releaseId) {
    String request = new HttpRequest()
                        .get()
                        .insecure()
                        .withUrl("${instance['url']}/release/workflow/${releaseId}")
                        .getRequest()

    new HttpClient().execute(request)
}

//workflows

return this