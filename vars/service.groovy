#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketApi
import com.stratio.rocket.rocketUtils.RocketConstants

@Field RocketApi rocketDev = new RocketApi(RocketConstants.ROCKET_API_URL_DEV)
@Field RocketApi rocketPre = new RocketApi(RocketConstants.ROCKET_API_URL_PRE)
@Field RocketApi rocketPro = new RocketApi(RocketConstants.ROCKET_API_URL_PRO)