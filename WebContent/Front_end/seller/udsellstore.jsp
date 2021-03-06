<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
//拿到登入ID
		String m_id = session.getAttribute("loginId").toString();

//拿到memberVO
	    MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.findOneMem(m_id);
	    session.setAttribute("memberVO", memberVO);
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>賣場介紹修改</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>

	.intro-form {
		background-color: #6fadd594;
        margin: auto;
        padding: 10px 40px 42px 31px;
        width:1000px;
        text-align:center;
	}
	.row-margin {
	    display: -ms-flexbox;
      display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px;
 		margin: 20px 0px; 
	}
	.row-title {
    	width: 220px;
        font-size: 25;
        background-color: bisque;
        text-align: center;
        line-height:90px;
	    margin:auto ;
	}
	.img {
	    width: 676px;
    	height: 200px;
	}
	.sells-intro {
		margin: auto;
		width:1000px;
	}
	.submit{
	    position: center;	
    }
    #stname-input{
    width: 676px;
    line-height: 86px;
    font-size: x-large;
    }
    .disnone{
    display:none;
    }
    .col btn_col{
    font-size: x-large;
    }
    btn_cancel{
    font-size: x-large;
   
    }
    
</style>
</head>
<body style="background: #F5D2CD; height: 100%;">
	
<!-- header----------->
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="../index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
<!-- header----------->

		<h1 class="sells-intro">編輯賣場介紹</h1>
	    <form class="intro-form" action="<%= request.getContextPath() %>/member/controller/MemberServlet" name="form1" method="POST" enctype="multipart/form-data">
	       	<div class="row-margin">
				<div  class="row-title">上傳圖片:</div>
				<div><input type="File" name="m_cover" id="imgInp" style="width:676px" multiple/></div>
				
			</div> 
			 <div class="row-margin">
			 	<div  class="row-title">賣場封面:</div>
				<img class="img" src="${memberVO.m_cover2}">
			</div>	
	        <div class="row-margin disnone" id="preshow">
				<div class="row-title">圖片預覽:</div>
				<div><img id="blah" height="200px" width="676px" /></div>
			</div>	
		
			<div class="row-margin">
				<div class="row-title">賣場名稱:</div>
				<div><input type="text" name="m_storename" id="stname-input" value='${memberVO == null ? "" : memberVO.getM_storename()}'></div>
			</div> 
			 
			 <div class="row-margin">
				<div class="row-title">賣場簡介:</div>
				<div>		
					<input type="text" name="m_info" style="width:676px;height:90px;font-size:x-large;font-style: italic" value='${memberVO == null ? "" : memberVO.getM_info()}'> 
				</div>
			</div>
		
		 <div class="row forget-row">
            <div class="col btn_col">
                <br><button type="button" id="btn_cancel" class="btn btn-primary forget-btn" style="font-size: x-large">取消</button>
                 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="submit" class="btn btn-primary forget-btn" style="font-size: x-large">確認</button>
                 <input type="hidden" name="action" value="updateSellstore">
				  <input type="hidden" name="m_id" value="<%=memberVO.getM_id()%>">
            </div>
        </div>
		 
<!-- 		   <input class="submit" type="submit" value="上傳修改"> -->
		</form>

	<!-- ----footer---- -->
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	
	
<script>
	$(function() {
		$("#imgInp").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}

				reader.readAsDataURL(this.files[0]);
			}
		});
	});

$("#imgInp").on("click",function(){
	$("#preshow").removeClass("disnone");
})


	    var btn_cancel = document.getElementById("btn_cancel");
	    btn_cancel.addEventListener('click',function(){
	    	location.href='<%=request.getContextPath()%>/Front_end/seller/udsellstore.jsp'
	    });
// 	    if(${errorMsgs_without != null}){
// 			alert("${errorMsgs_without}");			
// 		}



</script>
</body>
</html>