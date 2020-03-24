package com.stratio.rocket.rocketUtils

import groovy.transform.Field
import com.stratio.rocket.http.HttpClient
import com.stratio.rocket.rocketUtils.RocketClient
import com.stratio.rocket.rocketUtils.Workflow
import com.stratio.rocket.rocketUtils.Project

@Field def api = new RocketClient()
@Field def workflow = new Workflow()
@Field def project = new Project()
@Field def http = new HttpClient()

String getWorkflow(String workflowId) {
   def request = api.getWorkflow(workflowId)
   def response = http.executeWithOutput(request)
   return response
}

def createProjectIfNotExist(String projectName) {

   api.findProjectByName(rocket.dev.project.getName())
}

return this
