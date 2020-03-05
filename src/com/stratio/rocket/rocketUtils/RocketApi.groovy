package io.jenkins.pipeline.sample

class RocketApi implements Serializable {

	def isActive = false

	RocketApi(String urlVar) {
		echo "${env[urlVar]}"
	}

}