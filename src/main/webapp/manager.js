function viewMyEmployeesPendingRequests(button_value) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);

}

function viewAllResolvedRequests(button_value) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);
}

function getApproveorDenyRequestsView() {
	var formText =
		"<label>Employee username:</label>" +
		"<input type=\"text\" name=\"employee_username\" id=\"employee_username\" /><br>" +
		"<br><label>Request Title:</label>" +
		"<input type=\"text\" name=\"request_title\" id=\"request_title\" /><br>" +
		"<button id=\"approveRequest\" name=\"ApproveRequests\"" +
		" value =\"ApproveRequests\" onclick=\"ApproveorDenyRequests('ApproveorDenyRequests',employee_username.value, request_title.value, 'Approved'); this.onclick=null;\">Approve</button>" +
		"<button id=\"denyRequest\" name=\"DenyRequests\"" +
		" value =\"DenyRequests\" onclick=\"ApproveorDenyRequests('ApproveorDenyRequests',employee_username.value, request_title.value, 'Denied'); this.onclick=null;\">Deny</button>"

	var allInfo = document.getElementById('MyInfo');
	allInfo.innerHTML = formText;
}
function ApproveorDenyRequests(button_value, username, title, approve_deny) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&username=" + username + "&title=" + title + "&approve_deny=" + approve_deny, true);
	xhr.send(params);
}

function viewAllEmployees(button_value) {
	var allInfo = document.getElementById('MyInfo');
	console.log("Inside viewAllEmployees");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);
}

function getRequestsFromMyEmployeeView() {
	var formText =
		"<label>Employee username:</label>" +
		"<input type=\"text\" name=\"employee_username\" id=\"employee_username\" /><br>" +
		"<button id=\"submitBtn\" name=\"viewRequestsFromMyEmployee\"" +
		" value =\"viewRequestsFromMyEmployee\" onclick=\"viewRequestsFromMyEmployee('viewRequestsFromMyEmployee',employee_username.value); this.onclick=null;\">Submit</button>"

	var allInfo = document.getElementById('MyInfo');
	allInfo.innerHTML = formText;
}
function viewRequestsFromMyEmployee(button_value, employee_username) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value, "employee_username": employee_username};
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&employee_username=" + employee_username, true);
	xhr.send(params);
}



function myFunction(button_value) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);

}
function myLogoutFunction() {
	window.location('/EmployeeReinbursementSystem/');
}

function getUpdateMyInfoView() {
	var formText =
		"<label>First Name:</label>" +
		"<input type=\"text\" name=\"firstname\" id=\"firstname\" />" +
		"<label>Last Name:</label>" +
		"<input type=\"text\" name=\"lastname\" id=\"lastname\" />" +
		"<button class=\"update\" name=\"updateInfo\"" +
		" value =\"updateInfo\" onclick=\"updateMyInfo('updateInfo',firstname.value, lastname.value); this.onclick=null;\">Update</button>"

	var allInfo = document.getElementById('MyInfo');
	allInfo.innerHTML = formText;
}

function updateMyInfo(button_value, firstname, lastname) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value, "firstname": firstname, "lastname": lastname };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&firstname=" + firstname + "&lastname=" + lastname, true);
	xhr.send(params);

}

function myPendingRequests(button_value) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);

}
function myApprovedRequests(button_value) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value, true);
	xhr.send(params);

}

function getNewRequestView() {
	var formText2 = "<form action=\"/EmployeeReinbursementSystem/FrontController\" method=\"post\" enctype=\"multipart/form-data\"> " +
		"<label>Title:</label>" +
		"<input type=\"text\" name=\"reimbtitle\" id=\"reimbtitle\" /><br>" +
		"<br><label>Description:</label>" +
		"<input type=\"text\" name=\"description\" id=\"description\" /><br>" +
		"<br><label>Amount:</label>" +
		"<input type=\"text\" name=\"amount\" id=\"amount\" /><br>" +
		"<br><label>Insert image:</label>" +
		"<input type=\"file\" name=\"receipt\" id=\"imgFile\" accept =\"image/*\"/><br>" +
		"<input type=\"submit\" class=\"requestSubmit\" value= \"Submit2\" name=\"buttonname\"/>" 
		"</form>"
	var allInfo = document.getElementById('MyInfo');
	allInfo.innerHTML = formText2;
}

function newRequest(button_value, reimbtitle, description, amount) {
	var allInfo = document.getElementById('MyInfo');

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value, "title": reimbtitle, "description": description, "amount": amount };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&reimbtitle=" + reimbtitle + "&description=" + description + "&amount=" + amount, true);
	xhr.send(params);

}
function getReceipt(button_value, username, title) {
	myId = "showImage" + username + title;
	console.log("button_Value: " + button_value + ", myID : " + myId);
	var xhr = new XMLHttpRequest();
	xhr.responseType = 'blob';
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var img = new Image();
			img.src = window.URL.createObjectURL(xhr.response);

			if (button_value == "getReceiptForManager")
				var canvas = document.getElementById("showImage");
			else 
				var canvas = document.getElementById("showImageForEmployee");
			var context = canvas.getContext('2d');
			context.clearRect(0, 0, canvas.width, canvas.height);
			img.onload = function() {
				context.drawImage(img, 0, 0);
			}
		}
	}
	var params = { "buttonname": button_value, "title": title };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&employeeusername=" + username + "&title=" + title, true);
	xhr.send(params);
}