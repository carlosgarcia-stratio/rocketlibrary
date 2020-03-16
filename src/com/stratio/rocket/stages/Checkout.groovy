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
    def workflow = rocket.dev.getWorkflow(context.props["workflowId"])
    context.workflow.init(workflow, context.props["releaseId"])
    def project = rocket.dev.getProject(context.workflow.getProjectId())
    context.project.init(project)
    //def folders = jsonWorkflow["group"]["name"].split("/").findAll{ !(it == '' || it == 'home' || it == jsonProject["name"]) }
    sleep 1
}

return this
