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

		def tags = [:]
		int tagSum = 0
		int totalPlays = 0
		Artist currentArtist
		
		//for (artist in 0..(topArtistsList.size()-1)) {
		for (artist in 0..10) {
			def artistName 	= topArtistsXML.topartists.artist[artist].name.text()
			def artistPlays = topArtistsXML.topartists.artist[artist].playcount.text().toInteger()
			totalPlays += artistPlays
			//currentArtist = Artist.findByArtistName(artistName)
			//if (currentArtist == null){
				Thread.currentThread().sleep(1000)
				def artistURL 	= "${APIroot}?method=artist.gettoptags&artist=${artistName}&api_key=${APIkey}".replaceAll(' ', '%20')				
				def artistRESTResponse = artistURL.toURL().getText()
				def artistXML 	= new XmlSlurper().parseText(artistRESTResponse)
				def newArtist = new Artist(artistName: artistName).save()
				def totalTagCount = 0
				for (tag in 1..5){
					totalTagCount += artistXML.toptags.tag[tag].count.text().toInteger()
				}
				for (tag in 1..5){
					def tagName = artistXML.toptags.tag[tag].name.text().toLowerCase()
					def tagCount = artistXML.toptags.tag[tag].count.text().toInteger()
					float tagRatio = tagCount/totalTagCount
					def newTagRatio = new TagRatio(tagName: tagName, tagRatio: tagRatio, artist: newArtist).save()
					newArtist.addToTagRatios(newTagRatio)
				}
				currentArtist = newArtist
			//}					
			for (tag in currentArtist.tagRatios){
				def tagName = tag.tagName
				float tagRatio = tag.tagRatio
				float weightedTag = artistPlays * tagRatio
				if (tags.get(tagName) == null) { 
					tags.put(tagName, weightedTag)
					//render "not found: ${tags.get(tagName)}<br />"
				}else{
					float tempWeightedTag = weightedTag + tags.get(tagName)
					tags.put(tagName, tempWeightedTag)
					//render "found: ${tags.get(tagName)}<br />"
				}
			}
		}
		return [tags: tags, totalPlays: totalPlays, username: params.username, period: params.period]
	}
}
