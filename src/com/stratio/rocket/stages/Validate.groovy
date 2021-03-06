package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.Stages

@Field def name = Stages.VALIDATE
@Field def status = FlowConstants.SUCCESS
@Field def message = ""

def executeStage() {
    log.info("Executing stage ${name}...")

    //Check last execution
    //TODO: ask for and endpoint to get the latest execution for workflow
    //Check QR for that execution

    //Validate workflow (get context from last execution)
    log.info "Validating workflow version ${rocket.dev.workflow.getId()} with default conetxt"
    rocket.dev.validateWorkflow()
    log.info "Workflow version ${rocket.dev.workflow.getId()} validated with default conetxt"

    message = "Workflow version ${rocket.dev.workflow.getId()} validated successfully"
    log.info("Stage ${name} finished successfully")
}

return this