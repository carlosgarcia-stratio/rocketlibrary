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
    def jsonWorkflow = readJSON text: workflow
    context.props["workflow"] = workflow
    folders = jsonWorkflow["group"]["name"].split("/").remove("home")
    println(folders)


    sleep 15
}

return this
