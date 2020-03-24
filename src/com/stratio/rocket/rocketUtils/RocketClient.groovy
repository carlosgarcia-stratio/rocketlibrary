package com.stratio.rocket.rocketUtils

import com.stratio.rocket.http.HttpRequest
import groovy.transform.Field

@Field def url
@Field def isActive = false
@Field def auth = ""

def initialize(String aUrl, String authProps) {

    url = aUrl
    auth = authProps
    isActive = true
}

// Releases
def getWorkflowRelease(String releaseId) {

    String request = new HttpRequest()
                        .withAuth(auth)
                        .get()
                        .insecure()
                        .silent()
                        .withUrl("${url}/release/workflow/findById/${releaseId}")
                        .getRequest()

    return request
}

def addWorkflowReleaseStage(String releaseId, String name, String state, String message) {

    String body = "{\"releaseId\":\"${releaseId}\",\"name\":\"${name}\",\"state\":\"${state}\",\"message\":\"${message}\"}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/release/workflow/addReleaseStage")
            .getRequest()

    return request
}

def addWorkflowReleaseInfo(String releaseId, String key, String message) {

    String body = "{\"releaseId\":\"${releaseId}\",\"info\":{\"${key}\":\"${message}\"}}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/release/workflow/addInfo")
            .getRequest()

    return request
}

def updateWorkflowReleaseExecutionState(String releaseId, String state) {

    String body = "{\"releaseId\":\"${releaseId}\",\"executionState\":\"${state}\"}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/release/workflow/executionState")
            .getRequest()

    return request
}

def updateWorkflowReleaseWorkflowState(String releaseId, String state) {

    String body = "{\"releaseId\":\"${releaseId}\",\"workflowState\":\"${state}\"}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/release/workflow/workflowState")
            .getRequest()

    return request
}

// Workflows

def getWorkflow(String workflowId) {
    String request = new HttpRequest()
            .withAuth(auth)
            .get()
            .insecure()
            .silent()
            .withUrl("${url}/workflows/findById/${workflowId}")
            .getRequest()

    return request
}

def importWorkflow(String workflow, String groupId, String projectId, String name, String description) {
    String body = "{\"content\":${workflow},\"assetType\":\"Workflow\",\"groupId\":\"${groupId}\",\"projectId\":\"${projectId}\",\"name\":\"${name}\",\"description\":\"${description}\"}"
    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/assets/import")
            .getRequest()

    return request
}

def validateWorkflow(String id, String name, String description, String settings, String pipelineGraph, String executionEngine,
                     String workflowType, Long version, String group, String tags, String workflowMasterId, String projectId) {

    String body = """{"id":"${id}","name":"${name}","description":"${description}","settings":${settings},"pipelineGraph":${pipelineGraph},
"executionEngine":"${executionEngine}","workflowType":"${workflowType}","version":${version},"group":${group},"tags":${tags},
"workflowMasterId":"${workflowMasterId}","projectId":"${projectId}"}"""

    println(body)
    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/workflows/validateWithoutExecutionContext")
            .getRequest()


    return request
}

// Project & Folders
def getProject(String projectId) {
    String request = new HttpRequest()
            .withAuth(auth)
            .get()
            .insecure()
            .silent()
            .withUrl("${url}/projects/findById/${projectId}")
            .getRequest()

    return request
}

def findProjectByName(String name) {
    String request = new HttpRequest()
            .withAuth(auth)
            .get()
            .withFail()
            .insecure()
            .silent()
            .withUrl("${url}/projects/findByName/${name}")
            .getRequest()

    return request
}

def createProject(String name, String description) {
    String body = "{\"name\":\"${name}\",\"description\":\"${description}\"}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/projects")
            .getRequest()

    return request
}

return this