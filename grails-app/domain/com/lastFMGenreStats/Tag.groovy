package com.lastFMGenreStats

class Tag {
	String tagName
	int tagCount
	//float tagRatio

    	static constraints = {
		tagName(nullable:false, unique:true)
    	}

	//static hasMany = [artists:Artist]
	static belongsTo = Artist
	//static belongsTo = User
}
