package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.Stages

@Field def name = Stages.DEPLOY
@Field def status = FlowConstants.SUCCESS
@Field def message = ""

def executeStage() {
    log.info("Executing stage ${name}...")

    //Create project if not exist
    log.info "Checking if project ${rocket.dev.project.getName()} exist in production environment environment"
    def projectJson = rocket.pro.createProjectIfNotExist(rocket.dev.project.getName(), rocket.dev.project.getDescription())
    rocket.pro.project.init(projectJson)
    log.info "Project ${rocket.pro.project.getName()} created or found with id ${rocket.pro.project.getId()}"

    //Create folders if not exist
    log.info "Checking folders ${rocket.dev.workflow.getGroupName()} in project ${rocket.dev.project.getName()}"
    def groupJson = rocket.pro.createFoldersIfNotExist(rocket.pro.project.getName(), rocket.dev.workflow.getGroupName())
    if(!groupJson) {
        groupJson = rocket.pro.getGroup(rocket.pro.project.getGroupId())
    }
    rocket.pro.group.init(groupJson)
    log.info "Folders ${rocket.dev.workflow.getGroupName()} created or found in production environment"

    //Create workflow if not exist
    log.info "Checking workflow ${rocket.dev.workflow.getName()} in production environment"
    def workflowId = rocket.pro.createWorkflowIfNotExist(
            rocket.dev.workflow.getName(),
            rocket.dev.workflow.getDescription(),
            rocket.pro.group.getId(),
            rocket.pro.project.getId(),
            rocket.dev.workflow.getExecutionEngine()
    )
    log.info "Workflow created or found with id ${workflowId}"

    //Create or Update Workflow Version
    log.info "Creating or updating workflow version ${rocket.dev.workflow.getName()}-v${rocket.dev.workflow.getVersion()} in production environment"
    def workflowVersionId = rocket.pro.createOrUpdateWorkflowVersion(
            rocket.dev.workflow.getVersion(),
            "[]",
            rocket.dev.workflow.getPipelineGraph(),
            rocket.dev.workflow.getTags(),
            rocket.dev.workflow.getSettings(),
            workflowId,
            rocket.dev.workflow.getWorkflowType()
    )
    log.info "Workflow version ${workflowVersionId} created or updated successfully"

    //Get Workflow version
    def wfJson = rocket.pro.getWorkflow(workflowVersionId)
    rocket.pro.workflow.init(wfJson)

    message = "Workflow deployed with id ${workflowVersionId} successfully"
    log.info("Stage ${name} finished successfully")
}

return this
