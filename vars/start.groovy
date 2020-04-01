#!groovy

def call(Map props = [:]) {

    node{
        context.init(props)
        rocket.initInstances()
    }
    context.withContext {
        context.buildStatus = "A"
        println context.buildStatus
        flow.executor.execute(flow.builder.create())
        println context.buildStatus
    }
}
