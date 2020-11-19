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
		"<input type=\"submit\" class=\"requestSubmit\" value= \"Submit\" name=\"buttonname\"/>"
		"</form>"
	var allInfo = document.getElementById('MyInfo');
	allInfo.innerHTML = formText2;
}

function newRequest(button_value, reimbtitle, description, amount, img) {
	var allInfo = document.getElementById('MyInfo');
	console.log("img: " + img);

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.response : " + xhr.response);
			var returnValue = xhr.responseText;
			allInfo.innerHTML = returnValue;
		}

	}
	var params = { "buttonname": button_value, "title": reimbtitle, "description": description, "amount": amount };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&reimbtitle=" + reimbtitle + "&description=" + description + "&amount=" + amount + "&img=" + img, true);
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

			var canvas = document.getElementById("showImageForEmployee");
			var context = canvas.getContext('2d');
			context.clearRect(0, 0, canvas.width, canvas.height);
			img.onload = function() {
				context.drawImage(img, 0, 0);
			}
		}
	}
	var params = { "buttonname": button_value, "title": title };
	xhr.open('POST', '/EmployeeReinbursementSystem/FrontController?buttonname=' + button_value + "&username=" + username + "&title=" + title, true);
	xhr.send(params);
}
