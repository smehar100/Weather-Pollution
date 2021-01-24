function validation(){
	if(document.getElementById("input_city").value.length <= 0){
		alert("Please enter city name.")
		return false;
	}
	if(!document.getElementById("action_temp").checked &&
			!document.getElementById("action_temp_air").checked) {	
		alert("Please select an action.")
		return false;
	}
}

$(window).bind("pageshow", function() {
    var form = $('form'); 
    form[0].reset();
});