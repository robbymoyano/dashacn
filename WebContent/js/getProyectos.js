function getProyectos(fechaActual,perfil){
	/*
	* Autor: Robby Moyano
	* Date: Noviembre 2017
	* https://github.com/robbymoyano
	*/
	$.ajax({
		type: "GET",
		dataType: "json",
		//url: "data/data4.json",
		url:'http://10.43.24.100:7001/DashboardRest/resources/dash?fecha='+fechaActual+'&perfil='+perfil,
		cache:false,
		async: false,
		success: function(response){
			subTitle=response.subTitle;
			Categorias=response.categorias;
			dataAcumulado=response.dataAcumulado;
			dataManual=response.dataManual;
			dataRobot=response.dataRobot;
			fueraSLA=response.fueraSLA;
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