$(document).on("click", "#btn_send",function(){
	let key = $("#key").val().trim();
	let value = $("#value").val().trim();
	
	let Reg = /^[(alM0-9_)]{3,6}$/;
	if(value.length == 0){
		alert('內容請勿空白')
	}else if(key.length == 0){
		alert('請輸入all或是M00001會員編號')
	}
	if(!Reg.test(key)){
		alert('只能輸入all或是M00001會員編號')
	}
	let form_data = {
		"key" : key,
		"value" : value
	}
	
	let form_str = JSON.stringify(form_data);
	$.ajax({
		url: "http://localhost:8081/TEA102G3/Send",
		type: "POST",
		data: form_data,
//		dataType: "json"
		success: function(){
			list_html= "";
			
			list_html +='<table>';
			list_html +='';
			list_html +='	<tr>';
			list_html +='		<td>輸入:</td>';
			list_html +='		<td><input name="key" id="key" type="text" value="all">(all為系統公告或是輸入會員編號)</td>';
			list_html +='	</tr>';
			list_html +='	';
			list_html +='	<tr>';
			list_html +='		<td>內容:</td>';
			list_html +='		<td><input name="value" id="value" type="text"></td>';
			list_html +='	</tr>';
			list_html +='</table>';
			list_html +='<button type="button" id="btn_send">送出新增</button>';
			
			$("#div_input").html(list_html);
		},
		error: function(){
			console.log("error")
		}
	})
})

$(document).on("click", "#order_mam-tab",function(){
	$.ajax({
		url: "http://localhost:8081/TEA102G3/odlist",
		type: "POST",
		dataType:"JSON",
		success:function(data){
			console.log (data);
			list_html="";
			
			list_html += '<table>';
			list_html += '	<tr>';
			list_html += '		<th>流水號</th>';
			list_html += '		<th>訂單編號</th>';
			list_html += '		<th>商品編號</th>';
			list_html += '		<th>產品數量</th>';
			list_html += '		<th>修改</th>';
			list_html += '		<th>刪除</th>';
			list_html += '	</tr>';
			$.each(data.odlist,function(index,item){
			list_html += '		<tr>';
			list_html += '			<td>'+item.od_id+'</td>';
			list_html += '			<td>'+item.o_id+'</td>';
			list_html += '			<td>'+item.p_id+'</td>';
			list_html += '			<td>'+item.od_count+'</td>';
			list_html += '			';
			list_html += '			<td>';
			list_html += '				<button type="button" id="btn_odchange" >修改</button>';
//			list_html += '			     <input type="hidden" name="od_id"  value="${orderdetailVO.od_id}">';
			list_html += '			</td>';
			list_html += '			<td>';
			list_html += '			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/OrderdetailServlet" style="margin-bottom: 0px;">';
			list_html += '			     <input type="submit" value="刪除">';
			list_html += '			     <input type="hidden" name="od_id"  value="${orderdetailVO.od_id}">';
			list_html += '			     <input type="hidden" name="action" value="delete"></FORM>';
			list_html += '			</td>';
			list_html += '		</tr>	';
			list_html += '	</c:forEach>';
			list_html += '</table>';
			})
			
			$("#div_odlist").html(list_html);
		},
		error: function(){
			console.log("error")
		}
	})
	
})

