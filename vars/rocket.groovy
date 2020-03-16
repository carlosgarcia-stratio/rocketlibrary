#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient

@Field static def dev = new RocketClient()
@Field static def pre = new RocketClient()
@Field static def pro = new RocketClient()

def initInstances() {

    def dev_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_DEV)
    if (dev_url) {
        dev.initialize(dev_url, "")
    }

    def pre_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRE)
    if (pre_url) {
        pre.initialize(pre_url, "")
    }

    def pro_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRO)
    if (pro_url) {
        pro.initialize(pro_url, "")
    }
}

def getFromPropsOrEnv(String key) {
    return context.props.containsKey(key) ? context.props[key]  : (env[key] ? env[key] : null)
}
