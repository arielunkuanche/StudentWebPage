function deleteStudent(studentId){
	
	let url = "http://localhost:8080/WebAppExercises/deleteStudent";
	let parameterData = "id=" + studentId;
	
	let requestOptions = {
		method: "POST",
		headers:{"Content-Type": "application/x-www-form-urlencoded"},
		body: parameterData
	};
	
	fetch(url, requestOptions)
		.then(response =>{
			if(response.ok){
				return response.json();
			}throw new Error("HTTP status code is " + response.status);
			
		})
		.then(status => processResponseStatus(status))
		.catch(errorText => alert("Post request failed: " + errorText));
	
}

function processResponseStatus(status){
	if(status.errorCode === 0){
		alert("Student data deleted!");
	}else if(status.errorCode === 1){
		alert("Student data not deleted. Unknown student id!");
	}else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}