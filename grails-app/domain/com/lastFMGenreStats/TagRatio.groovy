package com.lastFMGenreStats

class TagRatio {

	String tagName
	float tagRatio

	static belongsTo = [artist:Artist]

    static constraints = {
    }
}
