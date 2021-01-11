<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.member.model.MemberService"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.member.model.MemberVO"%>

<%
	String loginId = session.getAttribute("loginId").toString();
	MemberService memberSvc = new MemberService();

	List<MemberVO> memberlist = (List<MemberVO>) memberSvc.getAll();
	for (int i = 0; i < memberlist.size(); i++) {
		if (memberlist.get(i).getM_id().equals(loginId)) {
			memberlist.remove(i);
		}
	}

	pageContext.setAttribute("memberlist", memberlist);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1, maximum-scale=1"> -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/css/friendchat.css" type="text/css" /> --%>

<title>聊天室</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/chatG1.css" />

</head>


<body onload="connect();" onunload="disconnect();">


	<div class="info_chat_use">
		<div class="info_img">
			<img id="only_img" src="<%=request.getContextPath()%>/images/chaticon.png" alt="" title="聊聊">
		</div>


		<div class="all_div_chat onoe">

			<div class="sidepanel">
			<div>
				<h2>
					<font color="red">${memberVO.m_name}</font>的賣場
				</h2>
</div>
				<ul class="chat_all_member">
					<c:forEach var="memberlist" items="${memberlist}">
						<div id="" class="column" name="friendName"
							value="${memberlist.m_email}">
							<h2>${memberlist.m_name}</h2>
						</div>
					</c:forEach>
				</ul>
			</div>

			<div class="chat_in_here">
				<div class="noly_div">
					<div class="just_word" id="just_word">
						<!--點擊好友後顯示名稱位子-->
					</div>
					<button class="X_btn">x</button>
				</div>


				<div class="info_chat_div" id="info_chat_div">
					<!-- 訊息顯示 -->
				</div>

				<div class="message_div">
					<input type="text" id="message" class="message_input"
						onkeydown="if (event.keyCode == 13) sendMessage();"> 
					<input type="submit" class="submit_btn" value="傳送"
						onclick="sendMessage();"> 
					<input type="hidden" id="friend" value="">
				</div>

				<h2>登入者：${loginName}</h2>
				<input type="hidden" class="hiddeninput" value="">

			</div>
		</div>
	</div>


	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script>

	$("#only_img").click(function () {
        $(".info_img").addClass("onoe");
        $(".all_div_chat").fadeIn(1000, function () {
            $(".all_div_chat").removeClass("onoe");
        })
    })
    $(".X_btn").click(function () {
        $(".all_div_chat").fadeOut(1000, function () {
            $(".all_div_chat").addClass("onoe");
            $(".info_img").removeClass("onoe");
        })

    })

    //以下為webSocket

    var MyPoint = "/FriendWS/${account}";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    console.log(endPointURL)

    var statusOutput = document.getElementById("just_word");
    var messagesArea = document.getElementById("info_chat_div");
    var self = '${account}';
    var webSocket;

    function connect() {
        // create a websocket
        webSocket = new WebSocket(endPointURL);
        webSocket.onopen = function (event) {
            console.log(event);
            console.log("Connect Success!");
        };

        webSocket.onmessage = function (event) {
            //從webSocket中取到值
            var jsonObj = JSON.parse(event.data);
            console.log(jsonObj);
            console.log("chat" === jsonObj.type && $(".hiddeninput").val() == jsonObj.sender)

            if ("open" === jsonObj.type) {
                refreshFriendList(jsonObj);
            } else if ("history" === jsonObj.type) {
                messagesArea.innerHTML = '';
                var ul = document.createElement('ul');
                ul.id = "area";
                messagesArea.appendChild(ul);

                // 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
                var messages = JSON.parse(jsonObj.message);
                for (var i = 0; i <messages.length; i++) {
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
                //如果發送過來的訊息並不是現在正在聊天的老友
                if (jsonObj.sender === $("#friend").val()) {
                    if (jsonObj.sender === self) {
                        li.className += 'me'
                        liTime.className += 'me'
                    }
                    if ($(".hiddeninput").val() == jsonObj.sender) {
                        li.className += 'friend'
                        liTime.className += 'friend'
                    }
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
                //-------------------------------原本的end-------
                webSocket.onclose = function (event) {
                    console.log("Disconnected!");
                };
            }
        }

        function sendMessage() {
        	console.log("有")
            var inputMessage = document.getElementById("message");
            var friend = $(".hiddeninput").val(); //g哥幫改
            var message = inputMessage.value.trim();

            if (message === "") {
                alert("Input a message");
                inputMessage.focus();
            } else if (friend === "") {
                alert("Choose a friend");
            } else {
                var jsonObj = {
                    "type": "chat",
                    "sender": self,
                    "receiver": friend,
                    "message": message,
                    "time": Date.now()
                };
                webSocket.send(JSON.stringify(jsonObj));
                inputMessage.value = "";
                inputMessage.focus();
            }
        }

        // 有好友上線或離線就更新列表
        function refreshFriendList(jsonObj) {
            var friends = jsonObj.users;
            var chat_all_member = document.getElementById("chat_all_member");
            addListener();
        }

        // 註冊列表點擊事件並抓取好友名字以取得歷史訊息
        function addListener() {
            $(".chat_all_member").click(function (e) {
            	console.log(e);
                var friend = e.target.childNodes[0].nodeValue;
                updateFriendName(friend);
                $("#friend").val(friend);
                var jsonObj = {
                    "type": "history",
                    "sender": self,
                    "receiver": friend,
                    "message": "",
                    "time": Date.now()
                };
                webSocket.send(JSON.stringify(jsonObj));
            })
        }
        function disconnect() {
            webSocket.close();
        }

        function updateFriendName(name) {
        	console.log(name);
            statusOutput.innerHTML = name;
        }
    }
	</script>
</body>
</html>