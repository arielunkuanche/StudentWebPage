/**
 * 
 */function addStudent(){
	
	let url = "http://localhost:8080/WebAppExercises/addStudent";
	
	
	let form = document.forms["formStudent"];
	console.log(form);
	
	let parameterData = 
		"id=" + form["txtid"].value +
 		"&firstname=" + form["txtfirstName"].value +
 		"&lastname=" + form["txtlastName"].value +
 		"&streetaddress=" + form["txtstreetAddress"].value +
 		"&postcode=" + form["txtpostCode"].value +
 		"&postoffice=" + form["txtpostOffice"].value;
 	
 	let requestOptions = { 
 		method: "POST", 
 		headers: {"Content-Type": "application/x-www-form-urlencoded"},
 		body: parameterData
 
 	};
 	
 	fetch(url, requestOptions)
		 .then(response => {
			if(response.ok){
				return response.json();
		 }throw new Error("HTTP status code is " + response.status);
	})
	.then(status => processResponseStatus(status))
	.catch(errorText => alert("Post request failed: " + errorText));
	
}

function processResponseStatus(status){
	
		if(status.errorCode === 0){
			alert("Student data added!");
		}else if(status.errorCode === 1){
			alert("Cannot add the student. The id is already in use!");
		}else if(status.errorCode === -1){
			alert("The database is temporarily unavailable. Please try again later.");
		}
}

