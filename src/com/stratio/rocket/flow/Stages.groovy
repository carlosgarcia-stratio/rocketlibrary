package com.stratio.rocket.flow

import com.stratio.rocket.stages.*

class Stages implements Serializable {

    static String CHECKOUT = "Checkout"
    static String VALIDATE = "Validate"
    static String DEPLOY = "Deploy"
    static String RELEASE = "Release"

    static def instances = [
            (Stages.CHECKOUT) : new Checkout(),
            (Stages.VALIDATE) : new Validate(),
            (Stages.DEPLOY) : new Deploy(),
            (Stages.RELEASE): new Release()
    ]

}
