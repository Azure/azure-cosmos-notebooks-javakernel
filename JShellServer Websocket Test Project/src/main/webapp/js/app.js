var kernelId = null;
var websocket = null;

function init() {
	if ("WebSocket" in window) {
		while (kernelId === null) {
			kernelId = prompt("Enter user name");
		}

		websocket = new WebSocket(`ws://localhost:9090/JShellServer/api/kernels/${kernelId}/channels`);
		websocket.onopen = function(data) {
			document.getElementById("main").style.display = "block";
		};

		websocket.onmessage = function(data) {
			alert(data.data);
			setMessage((JSON.parse(data.data))['content']['data']['text/plain']);
		};

		websocket.onerror = function(e) {
			alert('An error occured, closing application');
			
			cleanUp();
		};

		websocket.onclose = function(data) {
			cleanUp();
		
			var reason = (data.reason && data.reason !== null) ? data.reason : 'Goodbye';
			alert(reason);
		};
	} else {
		alert("Websockets not supported");
	}
}

function cleanUp() {
	document.getElementById("main").style.display = "none";

	userName = null;
	websocket = null;
}

function sendMessage() {
	var messageContent = document.getElementById("message").value;
	var message = buildMessage(userName, messageContent);

	document.getElementById("message").value = '';

	setMessage(message);
	websocket.send(JSON.stringify(message));
}

function sendInterrupt() {
	alert("interrupt going to be called");
$.post("http://localhost:9090/JShellServer/api/kernels/" + kernelId + "/interrupt", null,
    function(data,status){
      alert("interrupt called");
    });
}

function buildMessage(message) {
	return {
		content : {code: message}
	};
}

function setMessage(msg) {
	var currentHTML = document.getElementById('scrolling-messages').innerHTML;
	var newElem;

	if (msg.username === userName) {
		newElem = '<p style="background: #ebebe0;"><span>' + msg.username
				+ ' : ' + msg.message + '</span></p>';
	} else {
		newElem = '<p><span>' + msg.username + ' : ' + msg.message
				+ '</span></p>';
	}

	document.getElementById('scrolling-messages').innerHTML = currentHTML
			+ newElem;
}