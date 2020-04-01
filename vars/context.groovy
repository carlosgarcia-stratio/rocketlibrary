
import groovy.transform.Field

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.Context

@Field static def props = [:]
@Field static Context ctx = new Context()

def init(Map p) {
    props << p
    logLevel = getFromPropsOrEnv(FlowConstants.LOG_LEVEL_ENV_VAR, FlowConstants.DEFAULT_LOG_LEVEL)
    log.logLevel = logLevel
}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return props.containsKey(key) ? props[key]  : (env[key] ? env[key] : defaultValue)
}
