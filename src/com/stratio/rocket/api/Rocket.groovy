package com.stratio.rocket.api

import groovy.json.JsonOutput
import groovy.transform.Field

import com.stratio.rocket.http.HttpClient
import com.stratio.rocket.constants.RocketConstants
import com.stratio.rocket.constants.AuthConstants
import com.stratio.rocket.model.Workflow
import com.stratio.rocket.model.Project
import com.stratio.rocket.model.Group
import com.stratio.rocket.model.Release

@Field def api = new RocketClient()
@Field def workflow = new Workflow()
@Field def project = new Project()
@Field def group = new Group()
@Field def release = new Release()
@Field def http = new HttpClient()
@Field def isActive = false

def getWorkflow(String workflowId) {
   log.debug "getWorkflow ${workflowId} in ${api.url}"

   def request = api.getWorkflow(workflowId)
   def response = http.executeWithOutput(request)
   return http.handleJsonResponse(response, "Error retrieving workflow version ${workflowId}")
}

def getProject(String projectId) {
   log.debug "getProject ${projectId} in ${api.url}"

   def request = api.getProject(projectId)
   def response = http.executeWithOutput(request)
   return http.handleJsonResponse(response, "Error retrievieng project ${projectId}")
}

def getRelease(String releaseId){
   log.debug "getRelease ${releaseId} in ${api.url}"

   def request = api.getWorkflowRelease(releaseId)
   def response = http.executeWithOutput(request)
   return http.handleJsonResponse(response, "Error retrievieng workflow release ${releaseId}")
}

def updateReleaseExecutionState(String state){
   log.debug "updateReleaseExecutionState ${state} in ${api.url}"

   def request = api.updateWorkflowReleaseExecutionState(release.getId(), state)
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error updating release execution state ${releaseId}")
}

def addReleaseInfo(String key, String info){
   log.debug "addReleaseInfo ${key}:${info} in ${api.url}"

   def request = api.addWorkflowReleaseInfo(release.getId(), key, info)
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error adding release info for release ${releaseId}")
}

def addReleaseStageState(String name, String state, String message){
   log.debug "addReleaseStageState ${name}, ${state}, ${message} in ${api.url}"

   def request = api.addWorkflowReleaseStage(release.getId(), name, state, message)
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error adding release stage state for release ${releaseId}")
}

def validateWorkflow() {
   log.debug "validateWorkflow ${workflow.getId()} in ${api.url}"

   def request = api.validateWorkflow(workflow.getId(), workflow.getName(), workflow.getDescription(),
           workflow.getSettings(), workflow.getPipelineGraph(), workflow.getExecutionEngine(),
           workflow.getWorkflowType(), workflow.getVersion(), workflow.getGroup(), workflow.getTags(),
           workflow.getWorkflowMasterId(), workflow.getProjectId())
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error validating workflow version ${workflow.getId()}")
   def responseJson = readJSON text: response
   if(!responseJson.valid) {
      if(responseJson.messages) {
         error "Error validating workflow ${workflow.getId()}: ${JsonOutput.toJson(responseJson.messages.toString())}"
      } else {
         error "Error validating workflow ${workflow.getId()}"
      }
   }
}

def createProject(String name, String description) {
   log.debug "createProject ${name}, ${description} in ${api.url}"

   def request = api.createProject(name, description)
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error creating project ${name} in ${api.url}")
}

def createProjectIfNotExist(String projectName, String description) {
   log.debug "createProjectIfNotExist ${projectName}, ${description} in ${api.url}"

   def request = api.findProjectByName(projectName)
   def response = http.executeWithOutput(request)
   def project = null
   try {
      project = http.handleJsonResponse(response, "Error retrieving project ${projectName}")
   } catch(Exception e) {
      log.info "Project ${projectName} not found in ${api.url}"
   }

   if(!project) {
      project = createProject(projectName, description)
   } else {
      log.info "Project ${projectName} found in ${api.url}"
   }

   return project
}

def createFoldersIfNotExist(String projectName, String folders) {
   log.debug "createFoldersIfNotExist ${projectName}, ${folders} in ${api.url}"

   def folderList = folders.split("/").findAll{ !(it == '' || it == 'home' || it == projectName) }
   def folder = ""
   def group = null
   folderList.each { f ->
      folder +=  "/$f"
      group = createFolderIfNotExist(projectName, folder)
   }

   return group
}

def createFolderIfNotExist(String projectName, String folder) {
   log.debug "createFolderIfNotExist ${projectName}, ${folder} in ${api.url}"

   def groupName = "/home/$projectName$folder"

   def request = api.findGroupByName(groupName)
   def response = http.executeWithOutput(request)
   def group = null
   try {
      group = http.handleJsonResponse(response, "Error retrieving group ${groupName}")
   } catch(Exception e) {
      log.info "Group ${groupName} not found in ${api.url}"
   }

   if(!group) {
      request = api.createGroup(groupName)
      response = http.executeWithOutput(request)
      group = http.handleJsonResponse(response, "Error creating group ${groupName}")
   }

   return group
}

def getGroup(String groupId) {
   log.debug "getGroup ${groupId} in ${api.url}"

   def request = api.findGroupById(groupId)
   def response = http.executeWithOutput(request)
   groupJson = http.handleJsonResponse(response, "Error finding group for id ${groupId}")
   return groupJson
}

def createWorkflowIfNotExist(String name, String description, String groupId, String projectId, String executionEngine) {
   log.debug "createWorkflowIfNotExist ${name}, ${description}, ${groupId}, ${projectId}, ${executionEngine} in ${api.url}"

   def request = api.findAssetByNameAndGroup(name, groupId)
   def response = http.executeWithOutput(request)
   def workflowJson = null
   try {
      workflowJson = http.handleJsonResponse(response, "Error finding asset name ${name} in group ${groupId}")
   } catch(Exception e) {
      log.info "Asset name ${name} not found in groupId ${groupId} in ${api.url}"
   }

   if(!workflowJson) {
      request = api.createWorkflowAsset(name, description, groupId, projectId, executionEngine)
      response = http.executeWithOutput(request)
      workflowJson = http.handleJsonResponse(response, "Error creating asset name ${name} in group ${groupId}")
   }

   return workflowJson.workflowAsset.id
}

def getWorkflowVersionId(String workflowMasterId, Long targetVersion) {
   log.debug "getWorkflowVersionId ${workflowMasterId}-v${targetVersion} in ${api.url}"

   def request = api.findWorkflowVersions(workflowMasterId)
   def response = http.executeWithOutput(request)
   workflowIdsJson = http.handleJsonResponse(response, "Error finding workflow versionID for workflow ${workflowMasterId}")

   def id = workflowIdsJson.find { it.value == targetVersion }?.key
   return id
}

def createOrUpdateWorkflowVersion(Long version, String uiSettings, String pipelineGraph,String tags, String settings, String workflowMasterId, String workflowType) {
   log.debug "createOrUpdateWorkflowVersion v${version} in ${api.url}"

   String workflowVersionId = getWorkflowVersionId(workflowMasterId, version)
   def request
   def response
   def id
   if(workflowVersionId) {
      request = api.updateWorkflowVersion(workflowVersionId, version, uiSettings, pipelineGraph, tags, settings, workflowMasterId, workflowType)
      response = http.executeWithOutput(request)
      http.handleJsonErrorResponse(response, "Error updating version for workflow ${workflowMasterId}")
      id = workflowVersionId
   } else {
      request = api.createWorkflowVersion(version, uiSettings, pipelineGraph, tags, settings, workflowMasterId, workflowType)
      response = http.executeWithOutput(request)
      http.handleJsonErrorResponse(response, "Error creating version for workflow ${workflowMasterId}")
      id = getWorkflowVersionId(workflowMasterId, version)
   }

   if(id) return id else error "Error creating or updating version for workflow ${workflowMasterId}"
}

def release(String state){
   log.debug "release ${state} in ${api.url}"

   def request = api.updateWorkflowReleaseWorkflowState(release.getId(), state)
   def response = http.executeWithOutput(request)
   http.handleJsonResponse(response, "Error setting release state to ${state}")
}

def lock() {
   log.debug "lock ${workflow.getId()} in ${api.url}"

   def request = api.setReadOnly(workflow.getId(), true)
   def response = http.executeWithOutput(request)
   //TODO: handle string responses (assert(response=="OK"))
   http.handleJsonErrorResponse(response, "Error setting workflow version ${workflow.getId()} as readOnly")
}

def releaseAndLock(String state) {
   log.debug "releaseAndLock ${state} in ${api.url}"

   release(state)
   lock()
}

def init(String env, String url) {
   def auth_method = context.getFromPropsOrEnv(AuthConstants.ROCKET_AUTH_METHOD[env], AuthConstants.ROCKET_AUTH_USER_PASS)
   def tenant = context.getFromPropsOrEnv(RocketConstants.ROCKET_TENANT[env])
   def authProps = [authMethod: auth_method,
                    url: url,
                    credentialsId: AuthConstants.ROCKET_AUTH_CREDENTIALS[env],
                    tenant: tenant,
                    tokenPath: AuthConstants.AUTH_TOKEN_TEMP_PATH[env]
   ]
   def auth = getAuth(authProps)
   api.initialize(url, auth)
   isActive = true
}

def initRelease(String releaseId) {
   if(isActive) {
      def releaseJson = getRelease(releaseId)
      release.init(releaseJson)
   } else {
      error "Error when initialize release: instance not active"
   }
}

def getAuth(Map props) {
   if (props.authMethod == AuthConstants.ROCKET_AUTH_USER_PASS) {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${props.credentialsId}", usernameVariable: 'USER', passwordVariable: 'PASS']]) {
         def exists = fileExists AuthConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH
         if(!exists) {
            def authScript = libraryResource AuthConstants.AUTH_TOKEN_RESOURCE_PATH
            writeFile file: AuthConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH, text: authScript
         }
         sh(script: "bash ${AuthConstants.AUTH_TOKEN_SCRIPT_TEMP_PATH} ${props.url} ${USER} ${PASS} ${props.tenant} ${props.tokenPath}")
         def token = sh(script: "cat ${props.tokenPath}", returnStdout: true).trim()
         return "-H 'Cookie: user=${token}'"
      }
   } else if(props.authMethod == AuthConstants.ROCKET_AUTH_MUTUAL_TLS) {
      error "Auth Method not implemented yet"
   } else {
      log.debug "No auth for ${props.url}"
      return ""
   }
}

return this
