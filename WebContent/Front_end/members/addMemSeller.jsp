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
        b{
         color: red;
        }
        c{
	     color: orange;
        }
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

        ul{
        text-align: left;
        }
        
        .myclass{
        width:120px;
        padding-left:25px;
        text-align:left;
        }
        
</style>
</head>
<body class="mybody">
    <link rel="stylesheet" href="../../vendors/bootstrap/css/bootstrap.min.css">

	<FORM class="myform" METHOD="post" enctype="multipart/form-data" 
	ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet"	name="form1"
	enctype="multipart/form-data">        
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
            <input type="text" class="form-control" id="mail" name="m_email" value="<%=(memberVO == null) ? "a123@yahoo.com" : memberVO.getM_email()%>" />
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
			  <input type="text" class="form-control" id="name" name="m_name" value="<%=(memberVO == null) ? "MrJava" : memberVO.getM_name()%>" />
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
		  <input type="checkbox" class="btn btn-primary"value="成為賣家" onclick="location.href='addMem.jsp'" checked><label>我要成為賣家</label>
          </div>		  
		  
		 <div class="form-group row">
			<label for="inputEmail3" class="myclass" id="head">大頭照<c>*</c></label>
			<div class="col-sm-10">
			  <input type="file" name="m_headpic"  class="upld"
					value="<%=(memberVO == null) ? "" : memberVO.getM_headpic()%>" />
			</div>
		  </div>
		  
		 <div class="form-group row">
			<label for="inputEmail3" class="myclass">身分證字號<c>*</c></label>
			<div class="col-sm-10">
			  <input type="text" name="m_identity"
					value="<%=(memberVO == null) ? "F123456789" : memberVO.getM_identity()%>" class="form-control" />
			</div>
		  </div>
		  <div class="form-group row">
			<label for="inputEmail3" class="myclass" id="head">身分證照片<c>*</c></label>
			<div class="col-sm-10">
			  <input type="file" name="m_id_pic"
					value="<%=(memberVO == null) ? "請上傳身分證照" : memberVO.getM_id_pic()%>" />
			</div>
		  </div>
		  
		  <div class="form-group row">
			<label for="inputEmail3" class="myclass">銀行戶名<c>*</c></label>
			<div class="col-sm-10">
			  <input type="text" name="m_accountName"
					value="<%=(memberVO == null) ? "阿坤" : memberVO.getM_accountName()%>" class="form-control" />
			</div>
		  </div>
		  
		  <div class="form-group row">
			<label for="inputEmail3" class="myclass">銀行帳戶<c>*</c></label>
			<div class="col-sm-10">
			  <input type="text" name="m_account"
					value="<%=(memberVO == null) ? "12341234123412" : memberVO.getM_account()%>" class="form-control" />
			</div>
		  </div>
		  
		  <div class="form-group row">
			<label for="inputEmail3" class="myclass">銀行代號<c>*</c></label>
			<div class="col-sm-10">
			  <input type="text" name="b_code"
					value="<%=(memberVO == null) ? "700" : memberVO.getB_code()%>" class="form-control" />
			</div>
		  </div>
		  
		  <div class="form-group row">
			<label for="inputEmail3" class="myclass" id="head">存摺照片<c>*</c></label>
			<div class="col-sm-10">
			  <input type="file" name="m_bank_pic"
					value="<%=(memberVO == null) ? "請上傳存摺照" : memberVO.getM_bank_pic()%>" />
			</div>
		  </div>
          <div style="display:none">
          	<tr>
				<td>賣場名稱:<a>*</a></td>
				<td><input type="hi" name="m_storename"
					value="<%=(memberVO == null) ? "商場名字" : memberVO.getM_storename()%>"></td>
			</tr>
			<tr>
				<td>賣場簡介:</td>
				<td><input type="text" name="m_info"
					value="<%=(memberVO == null) ? "商場介紹" : memberVO.getM_info()%>"></td>
			</tr>
			<tr>
				<td>賣場封面:</td>
				<td><input type="file" name="m_cover"
					value="<%=(memberVO == null) ? "請上傳賣場照片" : memberVO.getM_cover()%>"></td>
			</tr>
			<tr>
				<td>聊天問候語:</td>
				<td><input type="text" name="m_hi"
					value="<%=(memberVO == null) ? "請輸入問候語" : memberVO.getM_hi()%>"></td>
			</tr>
			<tr>
				<td>離線問候語:</td>
				<td><input type="text" name="m_offlineHi"
					value="<%=(memberVO == null) ? "請輸入離線問候語" : memberVO.getM_offlineHi()%>"></td>
			</tr>
          </div>
          
          
		  <input type="hidden" name="action" value="insert2">
		  <button type="submit" class="btn btn-primary" value="註冊" id="signup">註冊</button>
	</FORM>
</body>


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
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : new Date(),
		maxDate : '+1970-01-01' // 去除今日(不含)之後
	});
</script>
</html>
