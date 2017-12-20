var categories=[];
var data=[];
var fecha;

function getGruposDias(fechaIn, perfil){
	/*
	* Autor: Robby Moyano
	* Date: Diciembre 2017
	* https://github.com/robbymoyano
	*/
	console.info("Grupos por dia "+fechaIn+",perfil "+perfil);
	fecha=fechaIn;
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "resources/dash/grupos?fecha="+fechaIn+"&perfil="+perfil,
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