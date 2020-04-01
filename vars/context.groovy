
import groovy.transform.Field

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.FlowContext

@Field static def props = [:]
@Field static FlowContext ctx = new FlowContext()

def init(Map p) {
    props << p
    logLevel = getFromPropsOrEnv(FlowConstants.LOG_LEVEL_ENV_VAR, FlowConstants.DEFAULT_LOG_LEVEL)
    ctx.logLevel = logLevel
}

def initNode(Map p) {
    ctx.node = p.containsKey(FlowConstants.NODE_ENV_VAR) ? p[FlowConstants.NODE_ENV_VAR] : FlowConstants.DEFAULT_NODE
}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return props.containsKey(key) ? props[key]  : (env[key] ? env[key] : defaultValue)
}
