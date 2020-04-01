#!groovy

def call(Map props = [:]) {

    context.initNode(props)
    node(context.ctx.node){
        context.init(props)
        rocket.initInstances()
    }
    flow.executor.execute(flow.builder.create())

}
