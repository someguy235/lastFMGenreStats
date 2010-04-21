<html>
	<head>
		<title>Last.fm Tag Results for ${username}</title>
		<meta name="layout" content="main"/>
		<g:javascript>
		
		
		</g:javascript>
	</head>
	<body>
	<g:set var="tagURLRoot" value="http://www.last.fm/tag/" />
	<g:set var="artistURLRoot" value="http://www.last.fm/music/" />
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
			<h3>
				<g:set var="first" value="${0}" />
				<g:each var="artist" in="${artistsPlayed}">
					<g:if test="${first == 0}">
						<g:set var="first" value="${1}" />
					</g:if>
					<g:else>
						-
					</g:else>
					<g:set var="artistURL" value="${artistURLRoot + artist}" />
					<g:link url="${artistURL}">${artist}</g:link>
				</g:each>
			</h3>
		</div>
		<br />
		<div class="main">
			<div class="tagCloud">
				<ul id="xdef" class="xmpl">
					<g:each var="tag" in="${tags.sort{a, b -> a.key <=> b.key}.collect{it}}">
						<g:set var="tagURL" value="${(tagURLRoot + tag.key).replaceAll(' ', '%20')}" />
						<li title="${tag.key} " value="${tag.value}">
							<g:link url="${tagURL}">${tag.key}</g:link>
						</li>
					</g:each>
				</ul>
				<g:javascript>
					$(document).ready(function() {
						$("#xdef").tagcloud({type:"list", sizemin:16, colormin: "A7B3BF", colormax: "005CBF"});
					});
				</g:javascript>
				
			</div>
		</div>
		<br />
		<div class="main">
			<h2>Popular Tags</h2>
			<div class="tagOutput">
				<g:each var="tag" in="${tags.sort{a, b -> a.value <=> b.value}.collect{it}.reverse()}">
					<g:set var="tagKey" value="${tag.key.replaceAll(' ', '_')}" />
					<g:set var="tagPct" value="${(tag.value / totalPlays * 100).round(1)}" />
					<g:set var="tagURL" value="${(tagURLRoot + tag.key).replaceAll(' ', '%20')}" />
					<div class="tagNameLabel">
						<g:link url="${tagURL}">${tag.key}</g:link>
					</div>
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
		<div class="search">
			<g:link action='index'><button aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="submit"><span class="ui-button-text">Search Again</span></button></g:link>
		</div>
	</body>
</html>

