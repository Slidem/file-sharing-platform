var JsonAjaxHandler = {

	call: function (httpMethod, url, data, onSuccessHandler, onErrorHandler){
		$.ajax({
			type : httpMethod,
			contentType : "application/json",
			url : url,
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				onSuccessHandler(result);
			},
			error : function(error) {
				onErrorHandler(error);
			}
		});
	}
};