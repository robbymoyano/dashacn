function getProyectos(){
	/*
	* Autor: Robby Moyano
	* Date: Noviembre 2017
	* https://github.com/robbymoyano
	*/
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "data/data4.json",
		cache:false,
		async: false,
		success: function(response){
			subTitle=response.SubTitle;
			Categorias=response.Categorias;
			dataAcumulado=response.dataAcumulado;
			dataManual=response.dataManual;
			dataRobot=response.dataRobot;
			fechas=response.fechas;
			pintarGrafico();
		},

		beforeSend: function () {
		},

		error:function(){
			$("#alerta").removeClass("hidden");
		}
	});
};