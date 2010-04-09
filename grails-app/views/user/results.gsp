<html>
	<head>
		<title>Last.fm Genre Results for </title>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<h1>Results</h1>
		<p>Total Plays: ${totalPlays}<br /><br /></p>
		float totalPct = 0.0
		tags.sort{ it.value as int }.each{ key, value ->
			render "${key}: ${value}<br />"
			float pct = value/totalPlays
			render "${pct}<br />"
			totalPct += pct
		}
		render "<br />${totalPct}<br />"
		<p>
		</p>
		<ul>
		</ul>
		<g:link action='index'>Search Again</g:link>
	</body>
</html>

