<html>
	<head>
		<title>Last.fm Genre Results for ${username}</title>
		<meta name="layout" content="main"/>
		<g:javascript>
			$(document).ready(function() {
				$("#progressbar").progressbar({ value: 25 });
			});
		</g:javascript>
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
				<g:each in="${tags.sort{it.value as int}}">
					<g:set var="tagPct" value="${it.value / totalPlays * 100}" />
					${it.key}: ${it.value} (${tagPct})<br />
					
					<br />
				</g:each>
			</div>
			<div id="progressbar"></div>
			<g:link action='index'>Search Again</g:link>
		</div>
	</body>
</html>

