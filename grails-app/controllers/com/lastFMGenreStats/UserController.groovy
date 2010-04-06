package com.lastFMGenreStats

class UserController {
		
	def scaffold = true

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
			int tagSum = 0
			
			render "<br />"
			//for (artist in 0..(topArtistsList.size()-1)) {
			for (artist in 0..4) {
				Thread.currentThread().sleep(1000)
				def artistRank 	= topArtistsXML.topartists.artist[artist].@rank.text()
				def artistName 	= topArtistsXML.topartists.artist[artist].name.text()
				def artistPlays = topArtistsXML.topartists.artist[artist].playcount.text()
				render "${artistRank}: ${artistName} (${artistPlays}) <br />"
				def artistURL 	= "${APIroot}?method=artist.gettoptags&artist=${artistName}&api_key=${APIkey}".replaceAll(' ', '%20')				
				def artistRESTResponse = artistURL.toURL().getText()
				def artistXML 	= new XmlSlurper().parseText(artistRESTResponse)
				for (tag in 1..5){
					def tagName = artistXML.toptags.tag[tag].name.text()
					def tagCount = artistXML.toptags.tag[tag].count.text().toInteger()
					tagSum += tagCount
					if (tags.get(tagName) == null) { 
						tags.put(tagName, tagCount)
					}else{
						int tempCount = tagCount + tags.get(tagName)
						tags.put(tagName, tempCount)
					}
					render "&nbsp&nbsp ${tagName}: ${tagCount} <br />" 
				}
			}
			render "${tags.toString()}"
		}
}
