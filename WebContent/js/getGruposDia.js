function getGruposDias(){
	/*
	* Autor: Robby Moyano
	* Date: Diciembre 2017
	* https://github.com/robbymoyano
	*/
	console.info("vamos a rescatar desde json");
	var f=getFechaActual();
	console.info(f);

	$.ajax({
		type: "GET",
		dataType: "json",
		url: "resources/dash/grupos?fecha="+f,
		cache:false,
		async: false,
		success: function(response){
			//pasar todo a los arreglos categories y data
			$.each(response, function(i, post) {
				categories.push(post.grupo);
				data.push(post.cantidad);
			});
		},

		beforeSend: function () {
		},

		error:function(){
			$("#alerta").removeClass("hidden");
		}
	});
};


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