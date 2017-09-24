var UserFactory = {

	getUserFromForm: function(){
		return new User(
			$('#first_name').val(),
			$('#last_name').val(),
			$('#email').val(),
			$('#pwd').val()
			)
	},

	getUserFromAjaxResponse: function(data){
		return new User(
			data.firstName,
			data.lastName,
			data.email,
			data.password
			);
	}

};


//User object
function User(firstName, lastName, email, password) {

	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;

}