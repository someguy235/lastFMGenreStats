package com.lastFMGenreStats

class UserController {

		//def scaffold = true

    def index = {
		}

		def results = {
			render "user: ${params.username} <br />"
			def artistPlays = [:]
			artistPlays.put("test1", 1)
			artistPlays.put("test2", 2)
			render "test?: ${artistPlays.get("test1")}<br />"
			render "test?: ${artistPlays.get("test2")}<br />"
		}
}
