#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketApi
import com.stratio.rocket.rocketUtils.RocketConstants

@Field static def instances = [:]

def initRocketInstances() {

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
      instances[RocketConstants.DEV] = new RocketApi(env[RocketConstants.ROCKET_API_URL_DEV])
    }

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
        instances[RocketConstants.PRE] = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRE])
    }

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
        instances[RocketConstants.PRO] = new RocketApi(env[RocketConstants.ROCKET_API_URL_PRO])
    }
}
