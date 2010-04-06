package com.lastFMGenreStats

class Artist {
		String artistName
		static hasMany [ tags : Tag ]

    static constraints = {
			artistName(nullable:false, unique:true)
    }
}
