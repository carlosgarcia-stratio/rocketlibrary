package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.VERIFY
@Field def status = "SUCCESS"
@Field def message = ""

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
    rocket.dev.validateWorkflow()
    message = "Workflow version${rocket.dev.workflow.getId()} validated successfully"
}

return this