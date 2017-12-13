var dataCircular=[];

function getCasuisticas(grupo){
	/*
	* Autor: Robby Moyano
	* Date: Diciembre 2017
	* https://github.com/robbymoyano
	*/
	console.info("vamos a rescatar desde json para el grafico de torta");
	console.info(grupo);
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "resources/dash/grupos/"+grupo+"?fecha=2",
		cache:false,
		async: false,
		success: function(response){
			dataCircular=[];
			//pasar todo a los arreglos categories y data
			$.each(response, function(i, post) {
				console.log("grupo: "+post.casuistica+", cantidad: "+post.cantidad);
				var myo={name:post.casuistica,y:post.cantidad};
				console.info(myo);
				dataCircular.push(myo);
			});
			pintarCirculo(grupo);

		},

		beforeSend: function () {
		},

		error:function(){
			$("#alerta").removeClass("hidden");
		}
	});
};



function pintarCirculo(titulo){
	Highcharts.chart('circle', {
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
			text: 'Top Casuísticas '+titulo
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					format: '{point.y} tickets<br>{point.percentage:.1f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				},
				showInLegend: true
			}
		},
		series: [{
			name: 'Brands',
			colorByPoint: true,
			data: dataCircular
		}]
	});
}
