$(document).on("click", "#btn_send",function(){
	let key = $("#key").val().trim();
	let value = $("#value").val().trim();
	
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