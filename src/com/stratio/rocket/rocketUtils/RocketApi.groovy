package com.stratio.rocket.rocketUtils

class RocketApi implements Serializable {

	def isActive = false

	RocketApi(Map env, String urlVar) {
		echo "${env[urlVar]}"
	}

}