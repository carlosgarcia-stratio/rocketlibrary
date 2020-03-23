package com.stratio.rocket.rocketUtils

class RocketConstants implements Serializable {

    static String DEV = "DEV"
    static String PRE = "PRE"
    static String PRO = "PRO"

    static String ROCKET_API_URL_DEV = "ROCKET_API_URL_DEV"
    static String ROCKET_API_URL_PRE = "ROCKET_API_URL_PRE"
    static String ROCKET_API_URL_PRO = "ROCKET_API_URL_PRO"

    static String ROCKET_TENANT_DEV = "ROCKET_TENANT_DEV"
    static String ROCKET_TENANT_PRE = "ROCKET_TENANT_PRE"
    static String ROCKET_TENANT_PRO = "ROCKET_TENANT_PRO"

    static String ROCKET_AUTH_METHOD_DEV = "ROCKET_AUTH_METHOD_DEV"
    static String ROCKET_AUTH_METHOD_PRE = "ROCKET_AUTH_METHOD_PRE"
    static String ROCKET_AUTH_METHOD_PRO = "ROCKET_AUTH_METHOD_PRO"

    static String ROCKET_AUTH_CREDENTIALS_DEV = "ROCKET_AUTH_CREDENTIALS_DEV"
    static String ROCKET_AUTH_CREDENTIALS_PRE = "ROCKET_AUTH_CREDENTIALS_PRE"
    static String ROCKET_AUTH_CREDENTIALS_PRO = "ROCKET_AUTH_CREDENTIALS_PRO"

    static String ROCKET_AUTH_USER_PASS = "ROCKET_AUTH_USER_PASS"
    static String ROCKET_AUTH_NONE = "ROCKET_AUTH_NONE"
    static String ROCKET_AUTH_MUTUAL_TLS = "ROCKET_AUTH_MUTUAL_TLS"

    static String AUTH_TOKEN_TEMP_PATH = "/tmp/getAuthToken.sh"
    static String AUTH_TOKEN_RESOURCE_PATH = "script/getAuthToken.sh"

    static String STAGE_STARTED = "WfReleaseStageStarted"
    static String STAGE_FINISHED = "WfReleaseStageFinished"
    static String STAGE_FAILED = "WfReleaseStageFailed"

    static String RELEASE_CREATED = "WfReleaseCreated"
    static String RELEASE_LAUNCHED = "WfReleaseLaunched"
    static String RELEASE_STARTED = "WfReleaseStarted"
    static String RELEASE_FINISHED = "WfReleaseFinished"
    static String RELEASE_FAILED = "WfReleaseFailed"
}
