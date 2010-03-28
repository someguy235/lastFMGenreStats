package com.lastFMGenreStats

class UserController {
		
    def index = {
		}

		def results = {
			def APIroot = "http://ws.audioscrobbler.com/2.0/"
			def topArtistsURL = APIroot + "?method=user.gettopartists&user=${params.username}&period=${params.period}&api_key=503475a6b1d2cafdedf2f69ed4f677c3"
			def topArtistsRESTResponse = new URL(topArtistsURL).getText()
			def topArtistsXML = new XmlSlurper().parseText(topArtistsRESTResponse)
			def topArtistsList = topArtistsXML.topartists.artist

			render "user: ${params.username} <br />"
			render "period: ${params.period} <br />"
			
			//def artistPlays = [:]
			//artistPlays.put("test1", 1)
			//render "test?: ${artistPlays.get("test1")}<br />"
			
			render "<br />"
			for (artist in 0..(topArtistsList.size()-1)) {
				render "${topArtistsXML.topartists.artist[artist].@rank.text()}:\t"
				render "${topArtistsXML.topartists.artist[artist].name.text()}"
				render " (${topArtistsXML.topartists.artist[artist].playcount.text()})<br />"
			
			}
		}
}
