var categories=[];
var data=[];
var fecha;

function getGruposDias(fechaIn){
	/*
	* Autor: Robby Moyano
	* Date: Diciembre 2017
	* https://github.com/robbymoyano
	*/
	console.info("vamos a rescatar desde json");
	fecha=fechaIn;
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "resources/dash/grupos?fecha="+fechaIn,
		cache:false,
		async: false,
		success: function(response){
			$.each(response, function(i, post) {
				categories.push(post.grupo);
				data.push(post.cantidad);
			});
			pintarGrafico();
		},

		beforeSend: function () {
		},

		error:function(){
			$("#alerta").removeClass("hidden");
		}
	});
};



function pintarGrafico(){
	Highcharts.chart('container', {
		chart: {
			type: 'bar'
		},
		title: {
			text: 'Cerrados por Grupo'
		},
		subtitle: {
			text: fecha
		},
		xAxis: {
			categories:categories,
			title: {
				text: null
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Tickets',
				align: 'high'
			},
			labels: {
				overflow: 'justify'
			}
		},
		tooltip: {
			valueSuffix: ' tickets'
		},

		plotOptions: {
			bar: {
				dataLabels: {
					enabled: true
				}
			},
			series: {

				point: {
					events: {
						click: function () {
							console.info('Category: ' + this.category + ', value: ' + this.y);
							//alert('Hola mundo'+ this.category);
							//pintarCirculo(this.category);
							getCasuisticas(this.category,fecha);
							//window.open("grupos.html?dia=1","_self");
						}
					}
				}
			}
		},

		credits: {
			enabled: false
		},
		series: [{
			name: 'Cierre de Ticket',
			data: data
		}]
	});
}



function getFechaActual() {
	/*
	* link: https://stackoverflow.com/questions/5250244/jquery-date-formatting
	*/
	var d = new Date();
	var day = d.getDate();
	var month = d.getMonth() + 1;
	var year = d.getFullYear();
	if (day < 10) {
		day = "0" + day;
	}
	if (month < 10) {
		month = "0" + month;
	}
	var date = day + "-" + month + "-" + year;

	return date;
};