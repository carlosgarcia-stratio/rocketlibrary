package com.stratio.rocket.rocketUtils

class RocketApi implements Serializable {

	def isActive = false

	RocketApi(String urlVar) {
		echo "${env[urlVar]}"
	}

}