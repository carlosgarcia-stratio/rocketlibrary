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
                        .withUrl("${instance['url']}/release/workflow/findById/${releaseId}")
                        .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def String addWorkflowReleaseInfo(String releaseId, String key, String message) {

    String body = "{\"releaseId\":\"${releaseId}\",\"info\":{\"${key}\":\"${message}\"}}"

    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/release/workflow/addInfo")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

//workflows

def String getWorkflow(String workflowId) {
    String request = new HttpRequest()
            .get()
            .insecure()
            .withUrl("${instance['url']}/workflow/findById/${workflowId}")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

return this