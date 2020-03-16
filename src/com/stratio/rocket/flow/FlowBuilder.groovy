package com.stratio.rocket.flow

def create(){
    try {
        def flowBuildKeys = selectFlowType()
        def flowStages = []
        flowBuildKeys.each { key ->
            flowStages << Stages.instances[key]
        }
        return flowStages
    } catch(ex) {
        log.error this.getClass().getSimpleName() + " error, " + ex.getMessage()
        error ex.getMessage()
    }
}

def String selectFlowType() {

    def flow = Flows.FLOW_KEY_EMPTY

    if(rocket.api.dev.isActive && rocket.pre.api.isActive && rocket.pro.api.isActive) {
        flow = Flows.FLOW_KEY_DEV_PRE_PRO
    } else if(rocket.dev.api.isActive && rocket.pro.api.isActive) {
        flow = Flows.FLOW_KEY_DEV_PRO
    } else {
        log.error("No flow found for active instances")
        error("No flow found for active instances")
    }
    return Flows.FLOWS[flow]
}

return this
