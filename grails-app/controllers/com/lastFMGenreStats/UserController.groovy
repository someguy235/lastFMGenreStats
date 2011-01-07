package com.lastFMGenreStats

class UserController {
		
	//def scaffold = true

    def index = {
	}

	def results = {
    	def session = request.session
    	if (params.username == ""){
    		redirect(action: 'index')
    		flash.message = "Invalid User Name"
    	}
    	else{
			def APIkey = ""
			def APIroot = "http://ws.audioscrobbler.com/2.0/"
			def submitPeriod = params.period.replaceAll(' ', '').replaceAll('s', '')
			def topArtistsURL = "${APIroot}?method=user.gettopartists&user=${params.username}&period=${submitPeriod}&api_key=${APIkey}"
			def topArtistsRESTResponse = new URL(topArtistsURL).getText()
			def topArtistsXML = new XmlSlurper().parseText(topArtistsRESTResponse)
			def topArtistsList = topArtistsXML.topartists.artist
	
			def tags = [:]
			def artistsPlayed = []
			int tagSum = 0
			int totalPlays = 0
			Artist currentArtist
			int numArtistsPlayed = params.numArtists.toInteger()
			
			if (topArtistsList.size() < params.numArtists.toInteger()){
				numArtistsPlayed = topArtistsList.size()
				flash.message = "There weren't enough artists played in this time range, " +
						"so we used all ${numArtistsPlayed} that were there."
			}
			
			for (artist in 0..<numArtistsPlayed) {
				def artistName 	= topArtistsXML.topartists.artist[artist].name.text()
				artistsPlayed.add(artistName)
				def artistPlays = topArtistsXML.topartists.artist[artist].playcount.text().toInteger()
				totalPlays += artistPlays
				currentArtist = Artist.findByArtistName(artistName)
				if (currentArtist == null){
					Thread.currentThread().sleep(1000)
					def artistURL 	= "${APIroot}?method=artist.gettoptags&artist=${artistName}&api_key=${APIkey}".replaceAll(' ', '%20')				
					def artistRESTResponse = artistURL.toURL().getText()
					def artistXML 	= new XmlSlurper().parseText(artistRESTResponse)
					def newArtist = new Artist(artistName: artistName).save()
					def totalTagCount = 0
					def numTags = artistXML.toptags.tag.size()
					if ( numTags > 5 ){
						numTags = 5
					}
					for (tag in 0..<numTags){
						totalTagCount += artistXML.toptags.tag[tag].count.text().toInteger()
					}
					for (tag in 0..<numTags){
						def tagName = artistXML.toptags.tag[tag].name.text().toLowerCase()
						def tagCount = artistXML.toptags.tag[tag].count.text().toInteger()
						float tagRatio = tagCount/totalTagCount
						def newTagRatio = new TagRatio(tagName: tagName, tagRatio: tagRatio, artist: newArtist).save()
						newArtist.addToTagRatios(newTagRatio)
					}
					currentArtist = newArtist
				}
				for (tag in currentArtist.tagRatios){
					def tagName = tag.tagName
					float tagRatio = tag.tagRatio
					float weightedTag = artistPlays * tagRatio
					if (tags.get(tagName) == null) { 
						tags.put(tagName, weightedTag)
					}else{
						float tempWeightedTag = weightedTag + tags.get(tagName)
						tags.put(tagName, tempWeightedTag)
					}
				}
			}
			return [tags: tags, totalPlays: totalPlays, username: params.username, period: params.period, numArtists: numArtistsPlayed, artistsPlayed: artistsPlayed]
    	}
	}
}
