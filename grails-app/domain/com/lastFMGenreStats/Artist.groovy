package com.lastFMGenreStats

class Artist {
	String artistName
	//static hasMany = [ tags : Tag ]
	//static belongsTo = [ user : User ]
	static hasMany [ tagRatios : TagRatio ]
			
    static constraints = {
			artistName(nullable:false, unique:true)
    }
}
