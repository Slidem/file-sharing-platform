function submitForm(event){
	var url = window.location.toString().replace("signUp", "register");
	var method = "POST";
	
	event.preventDefault();
	disable('submit_button');
	try{
		JsonAjaxHandler.call(
			method, 
			url, 
			UserFactory.getUserFromForm(), 
			submitSuccessHandler,
			submitFailureHandler
			);
	} finally{
		enable('submit_button');
	}
}

function setUpLoginButton(buttonId){
	var button  = $('#' + buttonId);
	button.click(function(){
		$('#email_parameter').val($('#email').val());
		var form = $('#loginForm');
		form.attr("method", "get");
		form.attr("action", "login");
		form.submit();	
	});
}

function enable(buttonId){
	$('#' + buttonId).removeAttr("disabled");
}

function disable(buttonId){
	$('#' + buttonId).attr("disabled", true);
}

function submitSuccessHandler(user){
	$('#email_container').html(user.email);
	$('#signUpModal').modal('show');
}

function submitFailureHandler(data){
	var error = data.responseJSON;
	$('#error_container').html(error.message);
	$('#signUpErrorModal').modal('show');	
}

//ON DOCUMENT READY
$(function(){
	$('#signUpForm').validator();
	$("#signUpForm").validator().on('submit', function(e){
		if(e.isDefaultPrevented()){
			//do nothing
		} else {
			submitForm(e);
		}
	});
	setUpLoginButton('login_button');
});



