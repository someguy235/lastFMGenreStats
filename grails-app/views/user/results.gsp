<html>
	<head>
		<title>Last.fm Genre Results for ${username}</title>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div class="body">
			<h1>Results for ${username}</h1>
			<h2>${totalPlays} plays 
				<g:if test="${period == 'Overall'}">
					${period}
				</g:if>
				<g:else>
					over ${period}
				</g:else>
			</h2>
			<div class="tagOutput">
				<g:each in="${tags.sort{it.value as int}.collect{it}.reverse()}">
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
			<div class="clear"></div> 
			<g:link action='index'><h2>Search Again</h2></g:link>
		</div>
	</body>
</html>

