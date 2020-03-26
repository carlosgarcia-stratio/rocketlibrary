package com.stratio.rocket.flow

class Flows implements Serializable {

    static def FLOW_DEV_PRO = [
        Stages.CHECKOUT,
        Stages.VERIFY,
        Stages.DEPLOY,
        Stages.RELEASE
    ]

    static def FLOW_DEV_PRE_PRO = [
            Stages.CHECKOUT,
            Stages.VERIFY,
            Stages.DEPLOY
    ]

    static def FLOW_EMPTY = [
    ]

    static String FLOW_KEY_DEV_PRO = "DEV_PRO"
    static String FLOW_KEY_DEV_PRE_PRO = "DEV_PRE_PRO"
    static String FLOW_KEY_EMPTY = "EMPTY"

    static def FLOWS = [
            (FLOW_KEY_EMPTY)    	: FLOW_EMPTY,
            (FLOW_KEY_DEV_PRO)    	: FLOW_DEV_PRO,
            (FLOW_KEY_DEV_PRE_PRO) 	: FLOW_DEV_PRE_PRO
            ]
}