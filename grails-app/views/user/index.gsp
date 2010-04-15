<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta name="layout" content="main"/>
		<g:javascript>
			$(function() {
				$("#period").buttonset();
			});
		</g:javascript>
		<title>Last.fm Genre Stats</title>
	</head>
	<body>
		<div class="body">
			<g:form action="results">
				<label for="username">Username</label>
				<g:textField name="username" />
				<br />
				<div class="ui-buttonset" id="period">
					<input class="ui-helper-hidden-accessible" id="Overall" name="period" type="radio" checked="checked"><label aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left ui-state-active" aria-pressed="true"  for="Overall"><span class="ui-button-text">Overall</span></label>
					<input class="ui-helper-hidden-accessible" id="7day"    name="period" type="radio">                  <label aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-button-text-only"                                aria-pressed="false" for="7day">   <span class="ui-button-text">7 Days</span></label>
					<input class="ui-helper-hidden-accessible" id="3month"  name="period" type="radio">                  <label aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-button-text-only"                                aria-pressed="false" for="3month"> <span class="ui-button-text">3 Months</span></label>
					<input class="ui-helper-hidden-accessible" id="6month"  name="period" type="radio">                  <label aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-button-text-only"                                aria-pressed="false" for="6month"> <span class="ui-button-text">6 Months</span></label>
					<input class="ui-helper-hidden-accessible" id="12month" name="period" type="radio">                  <label aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-right"                aria-pressed="false" for="12month"><span class="ui-button-text">12 Months</span></label>
				</div>
				<br />
				<button aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="submit"><span class="ui-button-text">Submit</span></button>
			</g:form>
		</div>
	</body>
</html>
