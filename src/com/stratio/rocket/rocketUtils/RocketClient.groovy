package com.stratio.rocket.rocketUtils

import com.stratio.rocket.http.HttpRequest
import groovy.transform.Field

@Field def url
@Field def auth = ""

def initialize(String aUrl, String authProps) {

    url = aUrl
    auth = authProps
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

def validateWorkflow(String id, String name, String description, String settings, String pipelineGraph, String executionEngine,
                     String workflowType, Long version, String group, String tags, String workflowMasterId, String projectId) {

    String body = """{"id":"${id}","name":"${name}","description":"${description}","settings":${settings},"pipelineGraph":${pipelineGraph},
"executionEngine":"${executionEngine}","workflowType":"${workflowType}","version":${version},"group":${group},"tags":${tags},
"workflowMasterId":"${workflowMasterId}","projectId":"${projectId}"}"""

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

def findGroupByName(String name) {
    def formattedName = name.replace("/","%2F")
    String request = new HttpRequest()
            .withAuth(auth)
            .get()
            .insecure()
            .silent()
            .withUrl("${url}/groups/findByName/${formattedName}")
            .getRequest()

    return request
}

def createGroup(String name) {
    String body = "{\"name\":\"${name}\"}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/groups")
            .getRequest()

    return request
}

def findAssetByNameAndGroup(String name, String groupId) {
    String request = new HttpRequest()
            .withAuth(auth)
            .get()
            .insecure()
            .silent()
            .withUrl("${url}/assets/findAllByGroupAndName/${groupId}/${name}")
            .getRequest()

    return request
}

def createWorkflowAsset(String name, String description, String groupId, String projectId, String executionEngine) {
    String body = "{\"workflowAsset\":{\"name\":\"${name}\",\"description\":\"${description}\",\"groupId\":\"${groupId}\",\"projectId\":\"${projectId}\",\"executionEngine\":\"${executionEngine}\"}}"

    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/assets")
            .getRequest()

    return request
}

def createWorkflowVersion(String version, String uiSettings, String pipelineGraph, String tags, String settings, String workflowMasterId, String workflowType) {
    String body = "{\"version\":${version},\"uiSettings\":${uiSettings},\"pipelineGraph\":\"${pipelineGraph}\",\"tags\":${tags},\"settings\":${settings},\"workflowMasterId\":\"${workflowMasterId}\", \"workflowType\":\"${workflowType}\"}"
    String request = new HttpRequest()
            .withAuth(auth)
            .post()
            .withHeader("Content-Type:application/json")
            .withBody(body)
            .insecure()
            .silent()
            .withUrl("${url}/workflows")
            .getRequest()

    return request
}

return this