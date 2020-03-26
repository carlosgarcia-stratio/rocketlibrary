package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.DEPLOY
@Field def status = "SUCCESS"
@Field def message = ""

def executeStage() {
    log.info("Deploy Stage started")
    execute()
    log.info("Deploy Stage finished")
}

def execute() {
    log.info("Deploy Stage execute")

    //Create project if not exist
    def projectJson = rocket.pro.createProjectIfNotExist(rocket.dev.project.getName(), rocket.dev.project.getDescription())
    rocket.pro.project.init(projectJson)

    //Create folders if not exist
    def groupJson = rocket.pro.createFoldersIfNotExist(rocket.pro.project.getName(), rocket.dev.workflow.getGroupName())
    if(!groupJson) {
        groupJson = rocket.pro.getGroup(rocket.pro.project.getGroupId())
    }
    rocket.pro.group.init(groupJson)

    //Create workflow if not exist
    def workflowId = rocket.pro.createWorkflowIfNotExist(
            rocket.dev.workflow.getName(),
            rocket.dev.workflow.getDescription(),
            rocket.pro.group.getId(),
            rocket.pro.project.getId(),
            rocket.dev.workflow.getExecutionEngine()
    )

    //Create or Update Workflow Version
    def workflowVersionId = rocket.pro.createOrUpdateWorkflowVersion(
            rocket.dev.workflow.getVersion(),
            "[]",
            rocket.dev.workflow.getPipelineGraph(),
            rocket.dev.workflow.getTags(),
            rocket.dev.workflow.getSettings(),
            workflowId,
            rocket.dev.workflow.getWorkflowType()
    )

    message = "Workflow deployed with id ${workflowVersionId} successfully"
}

return this
