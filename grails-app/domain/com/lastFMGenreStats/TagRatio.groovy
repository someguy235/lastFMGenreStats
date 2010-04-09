package com.lastFMGenreStats

class TagRatio {

	String tagName
	float tagRatio

	static belongsTo = [artist:Artist]

	String toString(){
		"tagName: (${tagName}), tagRatio: (${tagRatio})"//, belongsTo: (${belongsTo.toString()})"
	}
    static constraints = {
    }
}
