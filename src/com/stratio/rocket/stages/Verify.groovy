package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.VERIFY

def executeStage() {
    log.info("Verify Stage started")
    execute()
    log.info("Verify Stage finished")
}

def execute() {
    log.info("Verify Stages execute")

    //Check last execution
    //TODO: ask for and endpoint to get the latest execution for workfloe
    //Check QR for that execution

    //Validate workflow (get context from last execution)
    rocket.dev.api.validateWorkflow(rocket.dev.workflow.getId(), rocket.dev.workflow.getName(), rocket.dev.workflow.getDescription(),
            rocket.dev.workflow.getSettings(), rocket.dev.workflow.getPipelineGraph(), rocket.dev.workflow.getExecutionEngine(),
            rocket.dev.workflow.getWorkflowType(), rocket.dev.workflow.getVersion(), rocket.dev.workflow.getGroup(), rocket.dev.workflow.getTags(),
            rocket.dev.workflow.getWorkflowMasterId(), rocket.dev.workflow.getProjectId())

}

return this