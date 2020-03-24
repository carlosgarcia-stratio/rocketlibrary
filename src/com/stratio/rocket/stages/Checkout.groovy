package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.CHECKOUT

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
    def project = rocket.dev.getProject(rocket.dev.workflow.getProjectId())
    def projectJson = readJSON text: project
    rocket.dev.project.init(project, projectJson)

    def folders = wfJson["group"]["name"].split("/").findAll{ !(it == '' || it == 'home' || it == projectJson["name"]) }
    rocket.dev.workflow.init(workflow, wfJson, context.props["releaseId"])
}

return this
