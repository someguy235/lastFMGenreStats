<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta name="layout" content="main"/>
		<title>Last.fm Genre Stats</title>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#progressbar").progressbar({ value: 25 });
			});
		</script>
		
	</head>
	<body>
		<div class="body">
			<g:form action="results">
				<label for="username">Username</label>
				<g:textField name="username" />
				<br />
				<label for="period">Period</label>
				<br />
				<g:radio name="period" value="Overall" checked="true" />
				Overall <br />
				<g:radio name="period" value="7day" />
				7 Days <br />
				<g:radio name="period" value="3month" />
				3 Months <br />
				<g:radio name="period" value="6month" />
				6 Months <br />
				<g:radio name="period" value="12month" />
				12 Months <br /><br />
				<g:submitButton name="search" value="Submit"/>
			</g:form>
		</div>
	</body>
</html>
