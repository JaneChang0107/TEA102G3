<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增會員</title>
<style>
        .mybody{
            background-color: #E3F8F6;
        }
        .myform{
            border:1px solid gray;
            background-color: rgb(243, 241, 241);
            width: 500px;
            height: auto;
            margin: 20px auto;
			text-align: center;
        }
        .topcol{
            width:auto;
            height: 80px;
            background-color: #6CCFF3;
            margin:0px 0px 20px 0px;
            font-size: 24px;  
        }
		.gender{
			text-align: left;
			position: relative;
			padding: 10px 10px;
			
		}
		.gender>label{
			padding-right: 45px
		}
		#seller{
		    position:relative;
		    left:5px;
		    text-align:left;
		}
		#signup{
            background-color: #FFA000;
            width: 100px;
            border: 1px solid #707070;
            margin-left: 20px;
        }
        
		input.form-control{
			width:300px;
			margin-left:20px;
		}
		b{
         color: red;
        }
        ul{
        text-align: left;
        }
</style>
</head>
	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>


<body class="mybody">
    <link rel="stylesheet" href="../../vendors/bootstrap/css/bootstrap.min.css">
 
    <form class="myform" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" method="post">
        <div class="topcol">
            <br>
            <u>會員註冊</u>
            
        </div>
          	<c:if test="${not empty errorMsgs}">
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
        <div class="form-group row">
          <label for="inputEmail3" class="col-sm-2 col-form-label" >Email<b>*</b></label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="mail" name="m_email" value="<%=(memberVO == null) ? "" : memberVO.getM_email()%>" />
          </div>
		</div>
		
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label" >密碼<b>*</b></label>
			<div class="col-sm-10">
			  <input type="password" class="form-control" id="password" name="m_password" value="<%=(memberVO == null) ? "" : memberVO.getM_password()%>" />
			</div>
		  </div>

		  <div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label" >姓名<b>*</b></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="name" name="m_name" value="<%=(memberVO == null) ? "" : memberVO.getM_name()%>" />
			</div>
		  </div>

		  <div class="gender">
			<label>性別<b>*</b></label>
        <label>
			<input type="radio" name="m_gender" value="男" <%= memberVO != null && memberVO.getM_gender().equals("男") ? "checked" : "" %>>男 
	    </label>
		<label>
			<input type="radio" name="m_gender" value="女" <%= memberVO != null && memberVO.getM_gender().equals("女") ? "checked" : "" %> >女
		</label>
 		  </div>

		  <div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label" >電話<b>*</b></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control"  name="m_phone" id="phone" value="<%=(memberVO == null) ? "0999999999" : memberVO.getM_phone()%>" />
			</div>
		  </div>

		  <div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">地址<b>*</b></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="address" name="m_address" value="<%=(memberVO == null) ? "請填入地址" : memberVO.getM_address()%>" />
			</div>
		  </div>

		  <div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">生日<b>*</b></label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="f_date1" name="m_birth">
			</div>
		  </div>
          <div id="seller">
		  <input type="checkbox" class="btn btn-primary"value="成為賣家" onclick="location.href='<%=request.getContextPath()%>/Front_end/members/addMemSeller.jsp'"><label>我要成為賣家</label>
          </div>
		  <input type="hidden" name="action" value="insert"> 
		  <button type="submit" class="btn btn-primary" value="註冊" id="signup">註冊</button>

	  </form>
	  


</body>

	  	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

<!-- =====Date time picker設定======== -->
<%
	java.sql.Date m_birth = null;
	try {
		m_birth = memberVO.getM_birth();
	} catch (Exception e) {
		m_birth = new java.sql.Date(System.currentTimeMillis());
	}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value:   new Date(),
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>



</html>