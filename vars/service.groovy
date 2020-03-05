#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketApi
import com.stratio.rocket.rocketUtils.RocketConstants

@Field static def instances = [:]

//@Field static RocketApi rocketDev = new RocketApi(env[RocketConstants.ROCKET_API_URL_DEV])
//@Field static RocketApi rocketPre = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRE])
//@Field static RocketApi rocketPro = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRO])

def initRocketInstances() {

    if (env.contains(RocketConstants.ROCKET_API_URL_DEV)) {
      instances[RocketConstants.DEV] = new RocketApi(env[RocketConstants.ROCKET_API_URL_DEV])
    }

    if (env.contains(RocketConstants.ROCKET_API_URL_DEV)) {
        instances[RocketConstants.PRE] = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRE])
    }

    if (env.contains(RocketConstants.ROCKET_API_URL_DEV)) {
        instances[RocketConstants.PRO] = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRO])
    }
}
