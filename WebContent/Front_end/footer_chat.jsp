<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
#icon{
position: fixed;
            right: 20px;
            bottom: 130px;
            height: 80px;
            z-index: 2;
}
</style>


<!-- Bootstrap 的 CSS -->
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/W3C.css" /> --%>
</head>


<footer id="id_footer">
	<img
		src="<%=request.getContextPath()%>/images/white_LOGO字在外版(revised).png"
		class="logo" id="footerlogo">
	<div class="footerwords" id="footerwords">


		<span class="footerwords">關於我們</span><span class="footerwords">隱私權政策</span><br>
		<span class="footerwords">網站地圖</span><span class="footerwords">常用問答</span><br>
		<span class="footerwords">服務條款</span>
	</div>
	<div>
	<form id="myForm"
			action="<%=request.getContextPath()%>/websocketchat/NameServlet"
			method="POST" style="position: fixed">
			<input type="image" id="icon" value="賣場聊聊" src="<%=request.getContextPath()%>/images/chaticon.png" alt="Submit"> 
			<input type="hidden"
				name="m_id" value="${memberID}"> <input type="hidden"
				name="action" value="">
		</form>
		</div>
	<div class="copyright">Copyright © 2020 YuXiKun Co. ,Ltd. All
		rights reserved</div>
	

<%-- 		<form action="<%=request.getContextPath()%>/websocket/NameServlet" --%>
<!-- 		method="POST"> -->
<%-- 		<br> <input type="image" name="m_id" value="${memberID}" --%>
<%-- 			src="<%=request.getContextPath()%>/images/chaticon.png" alt="Submit"> --%>
<!-- 		<input type="hidden" name="action" value=""> -->
<!-- 	</form> -->
	
	
	
	
<!-- 	<button class="open-button" onclick="openForm()">聊聊</button> -->

<!-- <div class="chat-popup" id="myForm"> -->
<!--   <form action="/action_page.php" class="form-container"> -->
<!--     <h1>KUN Chat</h1> -->

<!--     <label for="msg"><b>聊天視窗</b></label> -->
<!--     <textarea placeholder="請輸入訊息" name="msg" required></textarea> -->

<!--     <button type="submit" class="btn">送出</button> -->
<!--     <button type="button" class="btn cancel" onclick="closeForm()">縮合</button> -->
<!--   </form> -->
<!-- </div> -->
				
</footer>

<!-- <script src="https://apps.elfsight.com/p/platform.js" defer></script> -->
<!-- <div class="elfsight-app-a4902ffd-293a-4002-aa51-048de6374163"></div> -->

<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
<script>
// function openForm() {
//   document.getElementById("myForm").style.display = "block";
// }

// function closeForm() {
//   document.getElementById("myForm").style.display = "none";
// }
</script>
          
<!-- <script> var a=document.createElement('a');a.setAttribute('href','javascript:;');a.setAttribute('id','easychat-floating-button');var img=document.createElement('img');img.src='https://chat-plugin.easychat.co/icon.svg';a.appendChild(img);var span=document.createElement('span');span.setAttribute('id', 'easychat-unread-badge');span.setAttribute('style','display: none');var d1=document.createElement('div');d1.setAttribute('id','easychat-close-btn');d1.setAttribute('class','easychat-close-btn-close');var d2=document.createElement('div');d2.setAttribute('id','easychat-chat-dialog');d2.setAttribute('class','easychat-chat-dialog-close');var ifrm=document.createElement('iframe');ifrm.setAttribute('id','easychat-chat-dialog-iframe');ifrm.setAttribute('src','https://client-chat.easychat.co/?appkey=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0ZWFtTmFtZSI6Ikt1biJ9.uOeo_lEPhWLQAXpDjxfFlgYzg4Eg2EgI1dKLLl4QXmc&lang=zh-Hant');ifrm.style.width='100%';ifrm.style.height='100%';ifrm.style.frameborder='0';ifrm.style.scrolling='on';d2.appendChild(ifrm);document.body.appendChild(a);document.body.appendChild(span);document.body.appendChild(d1);document.body.appendChild(d2);</script><script src='https://chat-plugin.easychat.co/easychat.js'></script> -->

</body>


</html>