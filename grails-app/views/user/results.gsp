<html>
	<head>
		<title>Last.fm Genre Results for ${username}</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div class="main">
			<h2>Results for <b>${username}</b></h2>
			<h3>${totalPlays} plays 
				<g:if test="${period == 'overall'}">
					${period}
				</g:if>
				<g:else>
					over ${period}
				</g:else>
				from ${numArtists} 
				<g:if test="${numArtists == '1'}">
					artist.
				</g:if>
				<g:else>
					artists.
				</g:else>
			</h3>
			<h3>${artistsPlayed.toString() }</h3>
		</div>
		<br />
		<div class="main">
			<div class="tagCloud">
			</div>
		</div>
		<br />
		<div class="main">
			<h2>Popular Tags</h2>
			<div class="tagOutput">
				<g:each in="${tags.sort{a, b -> a.value <=> b.value}.collect{it}.reverse()}">
					<g:set var="tagKey" value="${it.key.replaceAll(' ', '_')}" />
					<g:set var="tagPct" value="${(it.value / totalPlays * 100).round(1)}" />
					<div class="tagNameLabel">${it.key}</div>
					<g:javascript>
					$(document).ready(function() {
						$("#${tagKey}").progressbar({ value: ${tagPct} });
					});	
					</g:javascript>
					<div class="tagBar" id="${tagKey}"></div>
					<div class="tagPctLabel">&nbsp ${tagPct}%</div>	
					<br /><br />
				</g:each>
			</div>
		</div>
		<br />
		<div class="main">
			<g:link action='index'><h2>Search Again</h2><br /></g:link>
		</div>
	</body>
</html>

