
document.addEventListener("DOMContentLoaded", function () {
    // Fetch student data when the page loads
    fetchStudent();
});

function fetchStudent(){
	
    const urlParams = new URLSearchParams(window.location.search);
    const studentId = urlParams.get('studentId');
  
	fetch(`http://localhost:8080/WebAppExercises/StudentUpdateByIdServlet?studentId=${studentId}`)
		.then(response => {
			if(response.ok){
				return response.json();
			}throw new Error("HTTP status code is " + response.status);
		})
		.then(student => printStudent(student) )
		.catch(errorText => console.error("Fetch failed: " + errorText));
	
}

function printStudent(student) {
		
		document.getElementById("txtid").value = student.id;
        document.getElementById("txtid").disabled = true; 
        document.getElementById("txtfirstName").value = student.firstname;
        document.getElementById("txtlastName").value = student.lastname;
        document.getElementById("txtstreetAddress").value = student.streetaddress;
        document.getElementById("txtpostCode").value = student.postcode;
        document.getElementById("txtpostOffice").value = student.postoffice;
			
}

function updateStudent(){
	
	let url = "http://localhost:8080/WebAppExercises/updateStudent";
	let parameterData = 
        "id=" + document.getElementById("txtid").value +
		"&firstname=" + document.getElementById("txtfirstName").value +
		"&lastname=" + document.getElementById("txtlastName").value +
		"&streetaddress=" + document.getElementById("txtstreetAddress").value +
		"&postcode=" + document.getElementById("txtpostCode").value +
		"&postoffice=" + document.getElementById("txtpostOffice").value;  
 	
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

function cancel(){
	
	location.replace("studentList.html");
}

fetchStudent();