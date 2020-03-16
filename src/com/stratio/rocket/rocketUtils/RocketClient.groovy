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

// Releases

def getWorkflowRelease(String releaseId) {

    String request = new HttpRequest()
                        .get()
                        .insecure()
                        .withUrl("${instance['url']}/release/workflow/findById/${releaseId}")
                        .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def addWorkflowReleaseStage(String releaseId, String name, String state, String message) {

    String body = "{\"releaseId\":\"${releaseId}\",\"name\":\"${name}\",\"state\":\"${state}\",\"message\":\"${message}\"}"

    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/release/workflow/addReleaseStage")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def addWorkflowReleaseInfo(String releaseId, String key, String message) {

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

def updateWorkflowReleaseExecutionState(String releaseId, String state) {

    String body = "{\"releaseId\":\"${releaseId}\",\"executionState\":\"${state}\"}"

    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/release/workflow/executionState")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def updateWorkflowReleaseWorkflowState(String releaseId, String state) {

    String body = "{\"releaseId\":\"${releaseId}\",\"workflowState\":\"${state}\"}"

    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/release/workflow/workflowState")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

// Workflows

def getWorkflow(String workflowId) {
    String request = new HttpRequest()
            .get()
            .insecure()
            .withUrl("${instance['url']}/workflows/findById/${workflowId}")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def importWorkflow(String workflow, String groupId, String projectId, String name, String description) {
    String body = "{\"content\":${workflow},\"assetType\":\"Workflow\",\"groupId\":\"${groupId}\",\"projectId\":\"${projectId}\",\"name\":\"${name}\",\"description\":\"${description}\"}"
    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/assets/import")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def validateWorkflow(String id, String name, String description, String settings, String pipelineGraph, String executionEngine,
                     String workflowType, Long version, String group, String tags, String workflowMasterId, String projectId) {
    String body = """{"id":"${id}","name":"${name}","description":"${description}","settings":"${settings}","pipelineGraph":"${pipelineGraph}",
"executionEngine":"${executionEngine}","workflowType":"${workflowType}","version":${version},"group":"${group}","tags":"${tags}",
"workflowMasterId":"${workflowMasterId}","projectId":"${projectId}"}"""

    println(body)
    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/workflows/validateWithoutExecutionContext")
            .getRequest()

    String response = new HttpClient().execute(request)
    println(response)
    return response
}

// Project & Folders

def getProject(String projectId) {
    String request = new HttpRequest()
            .get()
            .insecure()
            .withUrl("${instance['url']}/projects/findById/${projectId}")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

def createProject(String name, String description) {
    String body = "{\"name\":\"${name}\",\"description\":\"${description}\"}"

    String request = new HttpRequest()
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .withUrl("${instance['url']}/projects")
            .getRequest()

    String response = new HttpClient().execute(request)
    return response
}

return this