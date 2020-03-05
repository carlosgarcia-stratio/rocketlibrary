#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketApi
import com.stratio.rocket.rocketUtils.RocketConstants

@Field static RocketApi rocketDev = new RocketApi(RocketConstants.ROCKET_API_URL_DEV)
@Field static RocketApi rocketPre = new RocketApi(RocketConstants.ROCKET_API_URL_PRE)
@Field static RocketApi rocketPro = new RocketApi(RocketConstants.ROCKET_API_URL_PRO)