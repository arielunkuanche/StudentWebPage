function main() {

	fetch("http://localhost:8080/WebAppExercises/students")
		.then(response => response.json())
		.then(studentList => printStudents(studentList))
}

function printStudents(studentList) {

	let studentTableBody = document.getElementById("tbodyStudents");
	let outputText = "";

	for (let student of studentList) {
		outputText += `
		
		<tr>
			<td>${student.id}</td>
			<td>${student.lastname}</td>
			<td>${student.firstname}</td>
			<td>${student.streetaddress}</td>
			<td>${student.postcode}</td>
			<td>${student.postoffice}</td>
			<td class="createUpdateDeleteLinks">${createUpdateDeleteLinks(student.id)}</td>
		</tr>
		
		`;
	}

	studentTableBody.innerHTML = outputText;
}

function addStudent() {

	location.replace("studentAdd.html");
}

function createUpdateDeleteLinks(studentId) {

	return "<span class='link' onclick='updateStudentHere(" + studentId + ");'>Update</span>" +"&nbsp;&nbsp;" +
			"<span class='link' onclick='deleteStudentHere(" + studentId + ");'>Delete</span>";
}

function deleteStudentHere(studentId){
	let confirmation = confirm("Delete student " + studentId + "?");
	
	if(confirmation){
		deleteStudent(studentId);
	
	} else{
		alert("Delete cancelled!")
	}
}

function updateStudentHere(studentId){
	location.replace("http://localhost:8080/WebAppExercises/StudentUpdateByIdServlet?studentId=" + studentId);
}


main();
