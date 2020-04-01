
import groovy.transform.Field

import com.stratio.rocket.constants.FlowConstants

@Field static def props = [:]
@Field String buildStatus = FlowConstants.SUCCESS
@Field static String error = ""
@Field static String logLevel = FlowConstants.DEFAULT_LOG_LEVEL

def init(Map p) {
    props << p
    logLevel = getFromPropsOrEnv(FlowConstants.LOG_LEVEL_ENV_VAR, FlowConstants.DEFAULT_LOG_LEVEL)
}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return props.containsKey(key) ? props[key]  : (env[key] ? env[key] : defaultValue)
}
