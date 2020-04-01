package com.stratio.rocket.flow

import com.stratio.rocket.constants.FlowConstants

class FlowContext implements Serializable {

    String buildStatus = FlowConstants.SUCCESS
    String error = ""
    String node = FlowConstants.DEFAULT_NODE
    String logLevel = FlowConstants.DEFAULT_LOG_LEVEL
}
