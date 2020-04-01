package com.stratio.rocket.constants

class RocketConstants implements Serializable {

    static String DEV = "DEV"
    static String PRE = "PRE"
    static String PRO = "PRO"

    static String ROCKET_API_URL_DEV = "ROCKET_API_URL_DEV"
    static String ROCKET_API_URL_PRE = "ROCKET_API_URL_PRE"
    static String ROCKET_API_URL_PRO = "ROCKET_API_URL_PRO"

    static Map ROCKET_API_URL = [
            DEV: ROCKET_API_URL_DEV,
            PRE: ROCKET_API_URL_PRE,
            PRO: ROCKET_API_URL_PRO
    ]

    static String ROCKET_TENANT_DEV = "ROCKET_TENANT_DEV"
    static String ROCKET_TENANT_PRE = "ROCKET_TENANT_PRE"
    static String ROCKET_TENANT_PRO = "ROCKET_TENANT_PRO"

    static Map ROCKET_TENANT = [
            DEV: ROCKET_TENANT_DEV,
            PRE: ROCKET_TENANT_PRE,
            PRO: ROCKET_TENANT_PRO
    ]

    static String STAGE_STARTED = "WfReleaseStageStarted"
    static String STAGE_FINISHED = "WfReleaseStageFinished"
    static String STAGE_FAILED = "WfReleaseStageFailed"

    static String RELEASE_CREATED = "WfReleaseCreated"
    static String RELEASE_LAUNCHED = "WfReleaseLaunched"
    static String RELEASE_STARTED = "WfReleaseStarted"
    static String RELEASE_FINISHED = "WfReleaseFinished"
    static String RELEASE_FAILED = "WfReleaseFailed"
}
