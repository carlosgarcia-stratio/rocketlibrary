#!groovy

def call(Map props = [:]) {

    context.init(props)
    rocket.initInstances()
    flow.executor.execute(flow.builder.create())
}
