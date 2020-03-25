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

    //Create if project exist
    def project = rocket.pro.createProjectIfNotExist(rocket.dev.project.getName(), rocket.dev.project.getDescription())
    def projectJson = readJSON text: project
    println(projectJson)
    rocket.pro.project.init(project, projectJson)

    def group = rocket.pro.createFoldersIfNotExist(rocket.pro.project.getName(), rocket.dev.workflow.getGroupName())
    def groupJson = readJSON text: group

//    String workflow = rocket.pro.api.importWorkflow(rocket.dev.workflow.getAsEscapedJson(),
//            rocket.dev.project.getGroupId(),
//            rocket.dev.project.getId(),
//            rocket.dev.workflow.getName(),
//            rocket.dev.workflow.getDescription())
//    println(workflow)
}

return this
