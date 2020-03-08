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
    def workflow = rocket.dev.getWorkflow()
    def jsonWorkflow = readJSON text: workflow
    context.props["workflow"] = jsonWorkflow
    context.props["projectId"] = jsonWorkflow["projectId"]
    context.props["workflowName"] = jsonWorkflow["name"]
    context.props["workflowDescription"] = jsonWorkflow["description"]
    // TODO: Refactor to do it in bash
    def project = rocket.dev.getProject()
    println(project)
    def jsonProject = readJSON text: project
    def folders = jsonWorkflow["group"]["name"].split("/").findAll{ !(it == '' || it == 'home' || it == jsonProject["name"]) }
    context.props["projectName"] = jsonProject["name"]
    context.props["folders"] = folders
    sleep 5
}

return this
