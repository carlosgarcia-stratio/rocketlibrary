package com.stratio.rocket.flow

import com.stratio.rocket.constants.FlowConstants

class Context implements Serializable {

    String buildStatus = FlowConstants.SUCCESS
    String error = ""
}
