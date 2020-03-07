#!groovy

import groovy.transform.Field
import com.stratio.rocket.flow.FlowBuilder

@Field static def builder = new FlowBuilder()
@Field static def executor = new FlowExecutor()
