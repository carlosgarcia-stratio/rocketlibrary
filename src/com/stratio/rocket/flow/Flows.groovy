package com.stratio.rocket.flow

class Flows implements Serializable {

    static def FLOW_DEV_PRO = [
        Stages.CHECKOUT,
        Stages.VALIDATE,
        Stages.DEPLOY,
        Stages.RELEASE
    ]

    static def FLOW_EMPTY = [
    ]

    static String FLOW_KEY_DEV_PRO = "DEV_PRO"
    static String FLOW_KEY_EMPTY = "EMPTY"

    static def FLOWS = [
            (FLOW_KEY_EMPTY)    	: FLOW_EMPTY,
            (FLOW_KEY_DEV_PRO)    	: FLOW_DEV_PRO
            ]
}
