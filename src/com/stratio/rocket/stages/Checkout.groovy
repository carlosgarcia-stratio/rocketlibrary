package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.Stages

@Field def name = Stages.CHECKOUT
@Field def status = FlowConstants.SUCCESS
@Field def message = ""

def executeStage() {
    log.info("Executing stage ${name}...")

    //Get Workflow version
    log.info "Checking out workflow version ${context.props["workflowId"]}"
    def wfJson = rocket.dev.getWorkflow(context.props["workflowId"])
    log.info "Workflow version checked out successfully"


    //Get Project for Workflow version
    log.info "Retrieving project info with id ${wfJson["projectId"]}"
    def projectJson = rocket.dev.getProject(wfJson["projectId"])
    log.info "Project info retrieved successfully"

    log.info "Initializing project and workflow version..."
    rocket.dev.workflow.init(wfJson)
    rocket.dev.project.init(projectJson)
    log.info "Project and workflow version initialized successfully"
    message = "Workflow version ${rocket.dev.workflow.getId()} and project ${rocket.dev.project.getId()} successfully checked out."
    log.info("Stage ${name} finished successfully")
}

return this
