package com.lastFMGenreStats

class UserController {
		
    def index = {
		}

		def results = {
			def APIkey = "503475a6b1d2cafdedf2f69ed4f677c3"
			def APIroot = "http://ws.audioscrobbler.com/2.0/"

			def topArtistsURL = "${APIroot}?method=user.gettopartists&user=${params.username}&period=${params.period}&api_key=${APIkey}"
			def topArtistsRESTResponse = new URL(topArtistsURL).getText()
			def topArtistsXML = new XmlSlurper().parseText(topArtistsRESTResponse)
			def topArtistsList = topArtistsXML.topartists.artist

			render "user: ${params.username} <br />"
			render "period: ${params.period} <br />"
			
			def tags = [:]
			//artistPlays.put("test1", 1)
			//render "test?: ${artistPlays.get("test1")}<br />"
			
			render "<br />"
			//for (artist in 0..(topArtistsList.size()-1)) {
			for (artist in 0..4) {
				Thread.currentThread().sleep(1000)
				def artistRank 	= topArtistsXML.topartists.artist[artist].@rank.text()
				def artistName 	= topArtistsXML.topartists.artist[artist].name.text()
				def artistPlays = topArtistsXML.topartists.artist[artist].playcount.text()
				render "${artistRank}: ${artistName} (${artistPlays}) <br />"
				def artistURL 	= "${APIroot}?method=artist.gettoptags&artist=${artistName}&api_key=${APIkey}".replaceAll(' ', '%20')				
				//render "${artistURL}<br />"
				//def artistRESTResponse = new URL(artistURL).getText()
				def artistRESTResponse = artistURL.toURL().getText()
				//render "${artistRESTResponse}<br />"
				def artistXML 	= new XmlSlurper().parseText(artistRESTResponse)
				//render "${artistXML}"
				//def artistTags	= artistXML.toptags.tag
				//render "${artistTags}"
				for (tag in 1..5){
					def tagName = artistXML.toptags.tag[tag].name.text()
					def tagCount = artistXML.toptags.tag[tag].count.text()
					tags.put(tagName, tagCount)
					render "&nbsp&nbsp ${tagName}: ${tagCount} <br />" 
				}
			}
			render "${tags.toString()}"
		}
}
