<%@page import="java.util.Iterator"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.member.model.MemberVO"%>

<%
	String loginId = session.getAttribute("loginId").toString();
	MemberService memberSvc = new MemberService();
	// 			List<MemberVO> memberlist = (List<MemberVO>) memberSvc.getAll();
	// 			for(int i=0;i<memberlist.size();i++){
	// 				if(memberlist.get(i).getM_id().equals(loginId)){
	// 					memberlist.remove(i);
	// 				}
	// 			}

	List<MemberVO> memberlist = (List<MemberVO>) memberSvc.getAll();
	for (int i = 0; i < memberlist.size(); i++) {
		if (memberlist.get(i).getM_id().equals(loginId)) {
			memberlist.remove(i);
		}
	}

	pageContext.setAttribute("memberlist", memberlist);
%>
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/friendchat.css" type="text/css" />
<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
<style type="text/css">
</style>
<title>最大私人聊天室</title>

<script
	src='http://production-assets.codepen.io/assets/editor/live/console_runner-079c09a0e3b9ff743e39ee2d5637b9216b3545af0de366d4b9aad9dc87e26bfd.js'></script>
<script
	src='http://production-assets.codepen.io/assets/editor/live/events_runner-73716630c22bbc8cff4bd0f07b135f00a0bdc5d14629260c3ec49e5606f98fdd.js'></script>
<script
	src='http://production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script>

<meta charset='UTF-8'>
<meta name="robots" content="noindex">

<link rel="shortcut icon" type="image/x-icon"
	href="http://production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type=""
	href="http://production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg"
	color="#111" />
<link rel="canonical"
	href="https://codepen.io/emilcarlsson/pen/ZOQZaV?limit=all&page=74&q=contact+" />
<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300'
	rel='stylesheet' type='text/css'>

<script src="https://use.typekit.net/hoy3lrg.js"></script>
<script>
	try {
		Typekit.load({
			async : true
		});
	} catch (e) {
	}
</script>
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/chat.css" />



</head>

<body onload="connect();" onunload="disconnect();">
	<div id="frame">
		<div id="sidepanel">
			<div id="profile">
				<div class="wrap">
					<img id="profile-img"
						src="<%=request.getContextPath()%>/images/main.png" class="online"
						alt="" />
					<p>${memberVO.m_name}的賣場</p>
					<div id="status-options">
						<ul>
							<li id="status-online" class="active"><span
								class="status-circle"></span>
								<p>Online</p></li>
							<li id="status-offline"><span class="status-circle"></span>
								<p>Offline</p></li>
						</ul>
					</div>
				</div>
			</div>

			<div id="contacts">
				<c:forEach var="memberlist" items="${memberlist}">
					<div id="" class="wrap" name="friendName"
						value="${memberlist.m_email}">
						<h2>${memberlist.m_name}</h2>
					</div>
				</c:forEach>

				<ul>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status online"></span> <img
								src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
							<div class="meta">
								<p class="name">Kun</p>
								<p class="preview">歡迎留言</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="content">
			<div class="contact-profile">
				<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
				<p>${loginName}</p>
			</div>

			<div class="messages">
				<ul>
					<li class="sent"><img
						src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
						<p>哈囉</p></li>
					<li class="replies"><img
						src="<%=request.getContextPath()%>/images/main.png" alt="" />
						<p>你好</p></li>
					<li class="replies"><img
						src="<%=request.getContextPath()%>/images/main.png" alt="" />
						<p>再見</p></li>
				</ul>
			</div>

			<div class="message-input">
				<div class="wrap">
					<input type="text" placeholder="Write your message..." />
					<!-- 				<i class="fa fa-paperclip attachment" aria-hidden="true"></i> -->
					<button class="submit">
						<i class="fa fa-paper-plane" aria-hidden="true"></i>
					</button>
				</div>
			</div>
		</div>
	</div>



<h3 id="statusOutput" class="statusOutput"></h3>

	<div id="row">
		<!-- 				<div id="" class="column" name="friendName" -->
		<%-- 					value="${memberVO.m_email}"> --%>
		<h2>${memberVO.m_name}的賣場</h2>


		<c:forEach var="memberlist" items="${memberlist}">
			<div id="" class="column" name="friendName"
				value="${memberlist.m_email}">
				<h2>${memberlist.m_name}</h2>
			</div>
		</c:forEach>

	</div>


<!-- 	<div id="messagesArea" class="panel message-area"></div> -->
<!-- 	<div> -->
<!-- 		<div class="panel input-area"> -->
<!-- 			<input id="message" class="text-field" type="text" -->
<!-- 				placeholder="Message" -->
<!-- 				onkeydown="if (event.keyCode == 13) sendMessage();" /> <input -->
<!-- 				type="submit" id="sendMessage" class="button" value="Send" -->
<!-- 				onclick="sendMessage();" /> -->
<!-- 		</div> -->
<!-- 		<h1> -->
<%-- 			登入帳號為:<font color=red> ${loginName} </font> --%>
<!-- 		</h1> -->
<!-- 		<input type="hidden" class="hiddeninput" value=""> -->

<!-- 	</div> -->
</body>
<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src="<%=request.getContextPath()%>/js/chat.js"></script>

<script>
	var MyPoint = "/FriendWS/${account}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	console.log(endPointURL)
	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${account}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj);
			console.log("chat" === jsonObj.type
					&& $(".hiddeninput").val() == jsonObj.sender)

			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var longTime = historyData.time;
					var time = new Date(longTime);
					console.log(time.toLocaleString());
					var li = document.createElement('li');
					var liTime = document.createElement('div');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me'
							: li.className += 'friend';
					historyData.sender === self ? liTime.className += 'time_me'
							: liTime.className += 'time_friend';
					li.innerHTML = showMsg;
					liTime.innerHTML = time.toLocaleString();

					ul.appendChild(li);
					ul.appendChild(liTime);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				var liTime = document.createElement('li');

				// 				jsonObj.sender === self ? li.className += 'me'
				// 						: li.className += 'friend';
				if (jsonObj.sender === self) {
					li.className += 'me'
					liTime.className += 'me'
				}
				if ($(".hiddeninput").val() == jsonObj.sender) {
					li.className += 'friend'
					liTime.className += 'friend'
				}
				var time = new Date(jsonObj.time);
				console.log(time.toLocaleString());
				li.innerHTML = jsonObj.message;
				liTime.innerHTML = time;
				console.log(li);
				console.log(liTime);

				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}

		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		// 		var friend = statusOutput.textContent;
		var friend = $(".hiddeninput").val();
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message,
				"time" : Date.now()
			};
			console.log(jsonObj);

			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		// 		row.innerHTML = '';
		// 		for (var i = 0; i < friends.length; i++) {
		// 			if (friends[i] === self) { continue; }
		// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
		// 		}
		addListener();
	}

	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		// 		container.addEventListener("click", function(e) {
		// 			var friend = e.srcElement.textContent
		$('.column')
				.click(
						function(e) {
							var friend = e.currentTarget.attributes.value.textContent;
							$(".hiddeninput").val(friend);
							var friend_name = e.currentTarget.children[0].childNodes[0].textContent;
							console.log(e);
							console.log(friend_name);
							updateFriendName(friend_name);
							var jsonObj = {
								"type" : "history",
								"sender" : self,
								"receiver" : friend,
								"message" : "",
								"time" : Date.now()
							};
							console.log(jsonObj)
							webSocket.send(JSON.stringify(jsonObj));
						});
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>