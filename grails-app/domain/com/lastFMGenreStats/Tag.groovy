package com.lastFMGenreStats

class Tag {
		String name
		int count

    static constraints = {
			name(nullable:true, unique:true)
    }

		static hasMany [artists:Artist]
}
