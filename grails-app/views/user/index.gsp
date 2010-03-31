<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Last.fm Genre Stats</title>
		<link type="text/css" href="css/custom-theme/jquery-ui-1.8.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
	</head>
	<body>
		<formset>
			<legend>Enter a Username</legend>
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
		</formset>
	</body>
</html>
