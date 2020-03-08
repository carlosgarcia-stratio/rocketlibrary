package com.stratio.rocket.flow

import com.stratio.rocket.stages.PreBuild
import com.stratio.rocket.stages.PostBuild
import com.stratio.rocket.stages.PreStage
import com.stratio.rocket.stages.PostStage

def execute(flow) {
    node{
        new PreBuild().executeStage()
        flow.each { s ->
            executeStage(s)
        }
        new PostBuild().executeStage()
    }
}

def executeStage(fStage){
    stage(fStage.name){
        new PreStage().executeStage(fStage)
        fStage.executeStage()
        new PostStage().executeStage(fStage)
    }
}

return this
