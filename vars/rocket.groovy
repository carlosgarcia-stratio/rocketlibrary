#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient

@Field static def dev = new RocketClient()
@Field static def pre = new RocketClient()
@Field static def pro = new RocketClient()

def initInstances() {

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
        dev.initialize(env[RocketConstants.ROCKET_API_URL_DEV], "")
    }

    if (env[RocketConstants.ROCKET_API_URL_PRE]) {
        pre.initialize(env[RocketConstants.ROCKET_API_URL_PRE], "")
    }

    if (env[RocketConstants.ROCKET_API_URL_PRO]) {
        pro.initialize(env[RocketConstants.ROCKET_API_URL_PRO], "")
    }
}

