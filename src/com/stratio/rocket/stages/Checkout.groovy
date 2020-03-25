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
    def wfJson = rocket.dev.getWorkflow(context.props["workflowId"])

    //Get Project for Workflow version
    def projectJson = rocket.dev.getProject(wfJson["projectId"])

    rocket.dev.workflow.init(wfJson)
    rocket.dev.project.init(projectJson)
    message = "Workflow version ${rocket.dev.workflow.getId()} and project ${rocket.dev.project.getId()} successfully checked out."
}

return this
