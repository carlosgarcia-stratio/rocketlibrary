package com.stratio.rocket.rocketUtils

import groovy.transform.Field
import com.stratio.rocket.http.HttpClient
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient
import com.stratio.rocket.rocketUtils.Workflow
import com.stratio.rocket.rocketUtils.Project
import com.stratio.rocket.rocketUtils.Release

@Field def api = new RocketClient()
@Field def workflow = new Workflow()
@Field def project = new Project()
@Field def release = new Release()
@Field def http = new HttpClient()
@Field def isActive = false

def getWorkflow(String workflowId) {
   def request = api.getWorkflow(workflowId)
   def response = http.executeWithOutput(request)
   return http.handleJsonResponse(response)
}

def getProject(String projectId) {
   def request = api.getProject(projectId)
   def response = http.executeWithOutput(request)
   return http.handleJsonResponse(response)
}

def getRelease(String releaseId){
      def request = api.getWorkflowRelease(releaseId)
      def response = http.executeWithOutput(request)
      return http.handleJsonResponse(response)
}

def updateReleaseExecutionState(String state){
   release.setExecutionState(state)
   api.updateWorkflowReleaseExecutionState(release.getId(), state)
}




def init(String env, String url) {
   def auth_method = context.getFromPropsOrEnv(RocketConstants.ROCKET_AUTH_METHOD[env], RocketConstants.ROCKET_AUTH_USER_PASS)
   def tenant = context.getFromPropsOrEnv(RocketConstants.ROCKET_TENANT[env])
   def authProps = [authMethod: auth_method,
                    url: url,
                    credentialsId: RocketConstants.ROCKET_AUTH_CREDENTIALS[env],
                    tenant: tenant,
                    tokenPath: RocketConstants.AUTH_TOKEN_TEMP_PATH[env]
   ]
   def auth = getAuth(authProps)
   api.initialize(url, auth)
   isActive = true
}

def initRelease(String releaseId) {
   if(isActive) {
      def releaseString = getRelease(releaseId)
      def releaseJson = readJSON text: releaseString
      release.init(releaseString, releaseJson)
   } else {
      log.error "Error when initialize release: instance not active"
      error "Error when initialize release: instance not active"
   }
}

def getAuth(Map props) {
   if (props.authMethod == RocketConstants.ROCKET_AUTH_USER_PASS) {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${props.credentialsId}", usernameVariable: 'USER', passwordVariable: 'PASS']]) {
         def exists = fileExists RocketConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH
         if(!exists) {
            def authScript = libraryResource RocketConstants.AUTH_TOKEN_RESOURCE_PATH
            writeFile file: RocketConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH, text: authScript
         }
         sh(script: "bash ${RocketConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH} ${props.url} ${USER} ${PASS} ${props.tenant} ${props.tokenPath}")
         def token = sh(script: "cat ${props.tokenPath}", returnStdout: true).trim()
         return "-H 'Cookie: user=${token}'"
      }
   } else if(props.authMethod == RocketConstants.ROCKET_AUTH_MUTUAL_TLS) {
      log.error "Auth Method not implemented yet"
      error "Auth Method not implemented yet"
   } else {
      log.debug "No auth for ${props.url}"
      return ""
   }
}

return this
