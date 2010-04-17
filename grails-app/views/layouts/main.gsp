<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/redmond',file:'jquery-ui-1.8.custom.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="jquery" />
        <g:javascript library="application" />
        <jq:plugin name="ui" />
    	<g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <h1>Last.fm Genre Stats</h1>
        <g:layoutBody />
        <h3>
        	All: &copy 2010, Ethan Shepherd
        </h3>
    </body>
</html>
