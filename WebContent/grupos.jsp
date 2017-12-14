<!DOCTYPE html>
<html>
	<head>
		<title>Cierre de Tickets</title>
		<meta charset="UTF-8">
		<script src="jquery-2.2.1.min.js"></script>

		<script src="Highcharts-4/js/highcharts.js"></script>
		<script src="Highcharts-4/js/highcharts-more.js"></script>
		<script src="Highcharts-4/js/modules/exporting.js"></script>

		<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
		<script src="dist/js/bootstrap.js"></script>
		<link href="css/docs.min.css" rel="stylesheet" type="text/css" />
		<meta name="viewport"
			  content="width=device-width, initial-scale=1, maximum-scale=0.9">

		<script src="js/getGruposDia.js"></script>
		<script src="js/getCasuistica.js?v=0.2"></script>

		<link type="text/css" rel="stylesheet" href="css/main.css?v=1.1">
		<style>
			#logo{
				position: relative;
				top:-14px;
			}
			#grafico{
				width: 100%;
				height: 430; /* 180 cargo fijo +  50 por banda= 250=430*/
			}
			.col-md-12{
				padding: 0px !important;
			}

			body{
				padding-top: 70px;
			}
		</style>

		<script>
			$(document).ready(
				function() {
					getGruposDias('<%= request.getParameter("fecha") %>');
					getCasuisticas(categories[0],'<%= request.getParameter("fecha") %>');
				});
		</script>
	</head>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar" aria-expanded="false"
							aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand"> 
						<img src="imag/logo3.png" id="logo" class="icon-bar" width="130px">
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="index.html">Cierre de Tickets</a></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a href="http://www.accenture.com" target="_blank">Powered
							by Accenture</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>


		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
				</div>
				<div class="col-md-5">
					<div id="circle" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
				</div>
			</div>
		</div>

	</body>
</html>