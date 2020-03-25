package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.CHECKOUT
@Field def status = "SUCCESS"
@Field def message = ""

def executeStage() {
    log.info("Checkout Stage started")
    execute()
    log.info("Checkout Stage finished")
}

def execute() {
    log.info("Checkout Stages execute")

    //Get Workflow version
    def workflow = rocket.dev.getWorkflow(context.props["workflowId"])
    def wfJson = readJSON text: workflow

    //Get Project for Workflow version
    def project = rocket.dev.getProject(wfJson["projectId"])
    def projectJson = readJSON text: project

    def folders = wfJson["group"]["name"].split("/").findAll{ !(it == '' || it == 'home' || it == projectJson["name"]) }

    rocket.dev.workflow.init(workflow, wfJson, folders)
    rocket.dev.project.init(project, projectJson)
    message = "Workflow version ${rocket.dev.workflow.getId()} and project ${rocket.dev.project.getId()} successfully checked out."
}

return this
