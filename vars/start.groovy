#!groovy

def call(Map props = [:]) {

    node{
        context.init(props)
        rocket.initInstances()
    }
    context.withContext {
        flow.executor.execute(flow.builder.create())
    }
}
