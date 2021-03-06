<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"	rel="stylesheet" id="bootstrap-css">
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html class=''>
<head>

<meta charset="BIG5">
<title>Insert title here</title>

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




<body>
	<div id="frame">
		<div id="sidepanel">
			<div id="profile">
				<div class="wrap">
					<img id="profile-img"
						src="<%=request.getContextPath()%>/images/main.png" class="online"
						alt="" />
					<p>Jia</p>
					<!-- 				<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i> -->
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

			<div id="search">
				<label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
				<input type="text" placeholder="Search contacts..." />
			</div>

			<div id="contacts">
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
					<li class="contact">
						<div class="wrap">
							<span class="contact-status online"></span> <img
								src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
							<div class="meta">
								<p class="name">David</p>
								<p class="preview">還有貨嗎</p>
							</div>
						</div>
					</li>
					<li class="contact active">
						<div class="wrap">
							<span class="contact-status offline"></span> <img
								src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
							<div class="meta">
								<p class="name">Peter</p>
								<p class="preview">請問兩款差別在哪</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status online"></span> <img
								src="http://emilcarlsson.se/assets/rachelzane.png" alt="" />
							<div class="meta">
								<p class="name">Jason</p>
								<p class="preview">請問商品狀況如何？</p>
							</div>
						</div>
					</li>
				</ul>
			</div>

			<div id="bottom-bar">
				<button id="addblock">
					<i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add
						block</span>
				</button>
				<!-- 			<button id="settings"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>Settings</span></button> -->
			</div>
		</div>

		<div class="content">
			<div class="contact-profile">
				<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
				<p>Peter</p>

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


	<script
		src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
	<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script >
$(".messages").animate({ scrollTop: $(document).height() }, "fast");

$("#profile-img").click(function() {
	$("#status-options").toggleClass("active");
});

$(".expand-button").click(function() {
  $("#profile").toggleClass("expanded");
	$("#contacts").toggleClass("expanded");
});

$("#status-options ul li").click(function() {
	$("#profile-img").removeClass();
	$("#status-online").removeClass("active");
	$("#status-away").removeClass("active");
	$("#status-busy").removeClass("active");
	$("#status-offline").removeClass("active");
	$(this).addClass("active");
	
	if($("#status-online").hasClass("active")) {
		$("#profile-img").addClass("online");
	} else if ($("#status-away").hasClass("active")) {
		$("#profile-img").addClass("away");
	} else if ($("#status-busy").hasClass("active")) {
		$("#profile-img").addClass("busy");
	} else if ($("#status-offline").hasClass("active")) {
		$("#profile-img").addClass("offline");
	} else {
		$("#profile-img").removeClass();
	};
	
	$("#status-options").removeClass("active");
});

function newMessage() {
	message = $(".message-input input").val();
	if($.trim(message) == '') {
		return false;
	}
	$('<li class="sent"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
	$('.message-input input').val(null);
	$('.contact.active .preview').html('<span>You: </span>' + message);
	$(".messages").animate({ scrollTop: $(document).height() }, "fast");
};

$('.submit').click(function() {
  newMessage();
});

$(window).on('keydown', function(e) {
  if (e.which == 13) {
    newMessage();
    return false;
  }
});
//# sourceURL=pen.js
</script>
</body>
</html>