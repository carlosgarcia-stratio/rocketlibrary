
import groovy.transform.Field

import com.stratio.rocket.constants.FlowConstants

@Field static def props = [:]
@Field static String buildStatus = FlowConstants.SUCCESS
@Field static String error = ""

def init(Map p) {
    props << p

}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return props.containsKey(key) ? props[key]  : (env[key] ? env[key] : defaultValue)
}

def withContext(Closure cl) {
    logLevel = context.getFromPropsOrEnv(FlowConstants.LOG_LEVEL_ENV_VAR, FlowConstants.DEFAULT_LOG_LEVEL)
    log.logLevel = logLevel
    log.withLogger {
        cl()
    }
}
