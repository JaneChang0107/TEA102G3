//載入頁面時 
$.ajax({
        url: "http://localhost:8081/TEA102G3/Refresh",           // 資料請求的網址
        type: "POST",                 // GET | POST | PUT | DELETE | PATCH
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function(){       // 在 request 發送之前執行
        },
        success: function(data){      // request 成功取得回應後執行
        	
        	 $("#update_without").html("");

          let list_html = "";

          list_html +='<div class="container update_without">';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='            	<p>員工ID:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';               
       	  list_html +=                 data.emp.e_id;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>門市:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
//       	  list_html +='                <select size="1" id="st_id" name="st_id">';
//       	  for(let i = 0; i < data.store.length; i++){
//       	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_name ;         		  
//       	  }       	 
//       	  list_html +='				</select>';
       	  list_html +=            		data.emp.st_name;
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
//       	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
//       	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
       	  list_html +=				data.emp.e_title;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>電話:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" id="e_phone" name="e_phone" value="'+ data.emp.e_phone +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>員工姓名:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" name="e_name" value="'+ data.emp.e_name +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>性別:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='             <input type="radio" name="e_gender" value="MEN" '+ ((data.emp.e_gender == 'MEN') ? 'checked' : '') +'>MEN';
       	  list_html +='				<input type="radio" name="e_gender" value="WOMEN" '+ ((data.emp.e_gender == 'WOMEN') ? 'checked' : '') +'>WOMEN';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>身分證字號:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" name="e_identity" value="'+ data.emp.e_identity +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>生日:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input name="e_birth" id="f_date1" type="text">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>地址:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" id="e_address" name="e_address" value="'+ data.emp.e_address +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>信箱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" name="e_email" value="'+ data.emp.e_email +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row forget-row">';
       	  list_html +='            <div class="col btn_col">';
       	  list_html +='                <button type="button" id="btn_enter" class="btn btn-primary forget-btn">確認</button>';
       	  list_html +='                <input type="hidden" name="e_id" id="e_id" value="'+ data.emp.e_id +'">';
       	  list_html +='                <input type="hidden" name="st_id" id="st_id" value="'+ data.emp.st_id +'">';
       	  list_html +='                <input type="hidden" name="e_title" id="e_title" value="'+ data.emp.e_title +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	
       	  $("#update_without").html(list_html);

       	  
 
       	 $('#f_date1').datetimepicker({
             theme: '',              //theme: 'dark',
   	       timepicker:false,       //timepicker:true,
   	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
   		   value: data.emp.e_birth, // value:   new Date(),
             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日
             //minDate:               '-1970-01-01', // 去除今日(不含)之前
             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
          });


        },
        error: function(xhr){         // request 發生錯誤的話執行
          console.log("error");
        }
      });     


// 按下基本資料按鈕
  $("#emp_modify-tab").on("click", function(){
	 let e_id = $("#e_id").val();

      //let form_data = new FormData();
      //form_data.append("user_id", user_id);
      //form_data.append("name", task_text);
      let form_data = {   	  
        "e_id" : e_id	
      };
      let data_string =  JSON.stringify(form_data);
      $.ajax({
        url: "http://localhost:8081/TEA102G3/ajax",           // 資料請求的網址
        type: "POST",                 // GET | POST | PUT | DELETE | PATCH
        data: data_string,              // 傳送資料到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function(){       // 在 request 發送之前執行
        },
        success: function(data){      // request 成功取得回應後執行


          let list_html = "";

          list_html +='<div class="container update_without">';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='            	<p>員工ID:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';               
       	  list_html +=                 data.emp.e_id;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>門市:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
//   	  list_html +='                <select size="1" id="st_id" name="st_id">';
//   	  for(let i = 0; i < data.store.length; i++){
//   	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_name ;         		  
//   	  }       	 
//   	  list_html +='				</select>';
       	  list_html +=            		data.emp.st_name;
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
//   	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
//   	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
       	  list_html +=				data.emp.e_title;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>電話:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" id="e_phone" name="e_phone" value="'+ data.emp.e_phone +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>員工姓名:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" name="e_name" value="'+ data.emp.e_name +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>性別:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='             <input type="radio" name="e_gender" value="MEN"'+ ((data.emp.e_gender == 'MEN') ? 'checked' : '') +'>MEN';
       	  list_html +='				<input type="radio" name="e_gender" value="WOMEN"'+ ((data.emp.e_gender == 'WOMEN') ? 'checked' : '') +'>WOMEN';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>身分證字號:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" name="e_identity" value="'+ data.emp.e_identity +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>生日:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input name="e_birth" id="f_date1" type="text">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>地址:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" id="e_address" name="e_address" value="'+ data.emp.e_address +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>信箱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" name="e_email" value="'+ data.emp.e_email +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row forget-row">';
       	  list_html +='            <div class="col btn_col">';
       	  list_html +='                <button type="button" id="btn_enter" class="btn btn-primary forget-btn">確認</button>';
       	  list_html +='                <input type="hidden" name="e_id" id="e_id" value="'+ data.emp.e_id +'">';
       	  list_html +='                <input type="hidden" name="st_id" id="st_id" value="'+ data.emp.st_id +'">';
       	  list_html +='                <input type="hidden" name="e_title" id="e_title" value="'+ data.emp.e_title +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	
       	$("#update_without").html(list_html);

       	  
       	 $.datetimepicker.setLocale('zh'); 
       	 $('#f_date1').datetimepicker({
             theme: '',              //theme: 'dark',
   	       timepicker:false,       //timepicker:true,
   	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
   		   value: data.emp.e_birth, // value:   new Date(),
             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日
             //minDate:               '-1970-01-01', // 去除今日(不含)之前
             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
          });

          

        },
        error: function(xhr){         // request 發生錯誤的話執行
          console.log("error");
        }
      });     
  });

//按下確認修改員工基本資料
  $(document).on("click", "#btn_enter", function(){
	 let e_id = $("[name='e_id']").val();
	 let select_store = $("[name='st_id']").val();
	 let e_title = $("[name='e_title']").val();
	 let e_phone = $("[name='e_phone']").val();
	 let e_name = $("[name='e_name']").val();
	 let e_gender = $("[name='e_gender']:checked").val();
	 let e_identity = $("[name='e_identity']").val();
	 let f_date1 = $("#f_date1").val();
	 let e_address = $("[name='e_address']").val();
	 let e_email = $("[name='e_email']").val();
	 
	 if(e_title == null){
		 e_title="EMPLOYEE";
	 }
	 

      //let form_data = new FormData();
      //form_data.append("user_id", user_id);
      //form_data.append("name", task_text);
      let form_data = {   	  
        "e_id" : e_id,
        "st_id": select_store,
        "e_title" : e_title,
        "e_phone" : e_phone,
        "e_name" : e_name,
        "e_gender" : e_gender,
        "e_identity" : e_identity,
        "f_date1" : f_date1,
        "e_address" : e_address,
        "e_email" : e_email
      };
      let data_string =  JSON.stringify(form_data);
      $.ajax({
        url: "http://localhost:8081/TEA102G3/update_without",           // 資料請求的網址
        type: "POST",                 // GET | POST | PUT | DELETE | PATCH
        data: data_string,              // 傳送資料到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function(){       // 在 request 發送之前執行
        },
        success: function(data){      // request 成功取得回應後執行
        	
          console.log(data)

          let list_html = "";

          list_html +='<div class="container update_without">';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='            	<p>員工ID:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';               
       	  list_html +=                 data.emp.e_id;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>門市:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
//   	  list_html +='                <select size="1" id="st_id" name="st_id">';
//   	  for(let i = 0; i < data.store.length; i++){
//   	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_name ;         		  
//   	  }       	 
//   	  list_html +='				</select>';
       	  list_html +=            		data.emp.st_name;
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
//   	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
//   	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
       	  list_html +=				data.emp.e_title;
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>電話:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" id="e_phone" name="e_phone" value="'+ data.emp.e_phone +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>員工姓名:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" id="e_name" name="e_name" value="'+ data.emp.e_name +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>性別:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='             <input type="radio" name="e_gender" value="MEN"'+ ((data.emp.e_gender == 'MEN') ? 'checked' : '') +'>MEN';
       	  list_html +='				<input type="radio" name="e_gender" value="WOMEN"'+ ((data.emp.e_gender == 'WOMEN') ? 'checked' : '') +'>WOMEN';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>身分證字號:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input type="TEXT" name="e_identity" value="'+ data.emp.e_identity +'">';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>生日:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';
       	  list_html +='                <input name="e_birth" id="f_date1" type="text">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>地址:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" id="e_address" name="e_address" value="'+ data.emp.e_address +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col-3">';
       	  list_html +='                <p>信箱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col-9">';
       	  list_html +='                <input type="TEXT" name="e_email" value="'+ data.emp.e_email +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row forget-row">';
       	  list_html +='            <div class="col btn_col">';
       	  list_html +='                <button type="button" id="btn_enter" class="btn btn-primary forget-btn">確認</button>';
       	  list_html +='                <input type="hidden" name="e_id" id="e_id" value="'+ data.emp.e_id +'">';
       	  list_html +='                <input type="hidden" name="st_id" id="st_id" value="'+ data.emp.st_id +'">';
       	  list_html +='                <input type="hidden" name="e_title" id="e_title" value="'+ data.emp.e_title +'">';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	  
       	  let error_list = "";
       	  
       	  error_list +='<div class="row">';
       	  error_list +='    <div class="col">';
       	  error_list +='        錯誤表列';
       	  error_list +='            <ul>';
       	  
       	  $.each(data.errorMsg, function(item, index){
       	  error_list +='                    <li style="color:red">'+ index +'</li>';
          })
       	  
          error_list +='            </ul>';
       	  error_list +='    </div>';
       	  error_list +='</div>';       	         	  
       	  
       	if(data.errorMsg != null){
       		$("#update_without").prepend(error_list);
       	} else {
       	$("#update_without").html(list_html);

       	  
       	 $.datetimepicker.setLocale('zh'); 
       	 $('#f_date1').datetimepicker({
             theme: '',              //theme: 'dark',
   	       timepicker:false,       //timepicker:true,
   	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
   		   value: data.emp.e_birth, // value:   new Date(),
             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日
             //minDate:               '-1970-01-01', // 去除今日(不含)之前
             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
          });

          alert('success');
       	}

        },
        error: function(xhr){         // request 發生錯誤的話執行
          console.log("error");
        }
      });     
  });
  
  
//按下確認修改密碼基本資料
  $(document).on("click", "#enter_forget", function(){
	  let e_password = $("[name='e_password']").val()
	  let new_password = $("[name='new_password']").val()
	  let check_password = $("[name='check_password']").val()
	  //let form_data = new FormData();
	  //form_data.append("user_id", user_id);
	  //form_data.append("name", task_text);
	  let form_data = {   	  
		  "e_password" : e_password,
		  "new_password": new_password,
		  "check_password" : check_password
	  };
	  let data_string =  JSON.stringify(form_data);
	  $.ajax({
		  url: "http://localhost:8081/TEA102G3/update_pwd",           // 資料請求的網址
		  type: "POST",                 // GET | POST | PUT | DELETE | PATCH
		  data: data_string,              // 傳送資料到指定的 url
		  dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
		  beforeSend: function(){       // 在 request 發送之前執行
		  },
		  success: function(data){      // request 成功取得回應後執行
		  			  		  	  
			  $("[type='password']").val("");

//			  for(let i=0; data){}
			  alert(data);
			  
		  },
		  error: function(xhr){         // request 發生錯誤的話執行
			  console.log("error");
		  }
	  });     
  });

//查全部員工
  $("#emp_mana-tab").on("click", function(){
	  $.ajax({
		  url:"http://localhost:8081/TEA102G3/ListAll",
		  type:"POST",
		  dataType:"json",
		  beforeSend:function(){},
		  success:function(data){

			  let list_html = ""
			  
			  list_html+='<div class="row">';
			  list_html+='    <div class="col search-col">';
			  list_html+='        <p>查詢</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="text" id="input_search" name=""> ';
			  list_html+='        <button type="button" id="button_search" class="btn btn-primary">查詢</button>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <button type="button" id="btn_add" class="btn btn-outline-primary">新增</button>';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <div class="row listAll_row">';
			  list_html+='            <div class="col">員工id</div>';
			  list_html+='            <div class="col">員工密碼</div>';
			  list_html+='            <div class="col">身分證字號</div>';
			  list_html+='            <div class="col">員工姓名</div>';
			  list_html+='            <div class="col">性別</div>';
			  list_html+='            <div class="col">生日</div>';
			  list_html+='            <div class="col">員工信箱</div>';
			  list_html+='            <div class="col">員工電話</div>';
			  list_html+='            <div class="col">員工地址</div>';
			  list_html+='            <div class="col">職稱</div>';
			  list_html+='            <div class="col">員工狀態</div>';
			  list_html+='            <div class="col">門市id</div>';
			  list_html+='            <div class="col">修改</div>';
			  list_html+='            <div class="col">刪除</div>';
			  list_html+='        </div>';	  
				  
			  $.each(data.emp_list, function(index, item){
				  list_html+='		<div class="row" id="div_append">';
				  list_html+='            <div class="col AutoNewline" id="e_id">'+ item.e_id +'</div>';
				  list_html+='            <div class="col AutoNewline" id="e_password"><p class="para">'+ item.e_password +'</p><input name="input_e_password" class="-none" value="'+ item.e_password +'"></div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_identity +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_name +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_gender +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_birth +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_email +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_phone +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_address +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_title +'</div>';
				  list_html+='            <div class="col AutoNewline" id="e_status" data-e_id = "' + item.e_status + '">'+ item.e_status_view +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.st_name +'</div>';
				  list_html+='            <div class="col">';
				  list_html+='                 <button type="button" id="modify">修改';
				  list_html+='            </div>';
				  list_html+='            <div class="col">';
				  list_html+='                 <button type="button" id="toggle_status">更新狀態';
				  list_html+='            </div>';
				  list_html+='		</div>';
			  })
			  

			  
			  $("#con_mam").html(list_html);
			  
		  },
		  error:function(){
			  console.log("error");
			  }
		  })
  });
  
//點選管理員工的"新增"按鈕
  $(document).on("click","#btn_add" ,function(){
	  $.ajax({
		  url:"http://localhost:8081/TEA102G3/Add_before", 
		  type: "POST",
		  dataType: "json",
		  beforeSend: function(){},
		  success: function(data){
			  


			  
			  list_html = "";
			  
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>門市:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <select size="1" id="st_id_add" name="st_id">';
			  for(let i = 0; i < data.length; i++){			  
				  list_html+='            <option value="'+ data[i].st_id +'">'+ data[i].st_id +'';
			  };
			  list_html+='        </select>';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>職稱:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="radio" name="e_title" size="45" value="EMPLOYEE">EMPLOYEE';
			  list_html+='        <input type="radio" name="e_title" size="45" value="BOSS">BOSS';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <p>電話:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="TEXT" id="e_phone_add" name="e_phone">';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>員工姓名:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="TEXT" id="e_name_add" name="e_name">';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <p>性別:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="radio" name="e_gender" value="MEN"}>MEN';
			  list_html+='        <input type="radio" name="e_gender" value="WOMEN"}>WOMEN';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>身分證字號:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="TEXT" id="e_identity_add" name="e_identity">';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <p>生日:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input name="e_birth" id="f_date2" type="text" placeholder="請選擇日期">';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>地址:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="TEXT" id="e_address_add" name="e_address">';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <p>狀態:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="radio" name="e_status" value="0">在職';
			  list_html+='        <input type="radio" name="e_status" value="1">離職	';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <p>信箱:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="TEXT" id="e_email_add" name="e_email">';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <p>密碼:</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="password" id="e_password" name="e_password">';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row forget-row">';
			  list_html+='    <div class="col btn_col">';
			  list_html+='        <button type="button" id="btn_cancel" class="btn btn-primary forget-btn">取消</button>';
			  list_html+='        <button type="button" id="add_enter" class="btn btn-primary forget-btn">確認</button>';
			  list_html+='    </div>';
			  list_html+='</div>';
			  
			  $("#con_mam").html(list_html);
			  
			  $.datetimepicker.setLocale('zh'); 
		       	 $('#f_date2').datetimepicker({
		           theme: '',              //theme: 'dark',
		   	       timepicker:false,       //timepicker:true,
		   	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		   	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
//		   		   value: new Date(), // value:   new Date(),
		             //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		             //startDate:	            '2017/07/10',  // 起始日
		             //minDate:               '-1970-01-01', // 去除今日(不含)之前
		             //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		          });
		  },
		  error:function(){
			  console.log("error");
		  }
	  })
  })
  
//點選新增員工的"確認"按鈕
  $(document).on("click", "#add_enter",function(){
	     let e_password = $("#e_password").val();
		 let select_store = $("#st_id_add").val();
		 let e_title = $("[name='e_title']:checked").val();
		 let e_phone = $("#e_phone_add").val();
		 let e_name = $("#e_name_add").val();
		 let e_gender = $("[name='e_gender']:checked").val();
		 let e_identity = $("#e_identity_add").val();
		 let f_date1 = $("#f_date1").val();
		 let e_address = $("#e_address_add").val();
		 let e_email = $("#e_email_add").val();
		 let e_status = $("[name='e_status']:checked").val();
		 
		 
		 let form_data = { 
				 	"e_password" : e_password,
			        "st_id": select_store,
			        "e_title" : e_title,
			        "e_phone" : e_phone,
			        "e_name" : e_name,
			        "e_gender" : e_gender,
			        "e_identity" : e_identity,
			        "f_date1" : f_date1,
			        "e_address" : e_address,
			        "e_email" : e_email,
			        "e_status" : e_status
			      };
		 let data_string = JSON.stringify(form_data);
		 

		 
	  $.ajax({
		  url: "http://localhost:8081/TEA102G3/Add_after",
		  type: "POST",
		  data: data_string,
		  dataType: "json",
		  beforeSend: function(){		  
		  },
		  success: function(data){
			  
			  
			  let list_html = ""
				  
				  list_html+='<div class="row">';
				  list_html+='    <div class="col search-col">';
				  list_html+='        <p>查詢</p>';
				  list_html+='    </div>';
				  list_html+='    <div class="col">';
				  list_html+='        <input type="text" id="input_search" name=""> ';
				  list_html+='        <button type="button" id="button_search" class="btn btn-primary">查詢</button>';
				  list_html+='    </div>';
				  list_html+='    <div class="col">';
				  list_html+='        <button type="button" id="btn_add" class="btn btn-outline-primary">新增</button>';
				  list_html+='    </div>';
				  list_html+='</div>';
				  list_html+='<div class="row">';
				  list_html+='    <div class="col">';
				  list_html+='        <div class="row listAll_row">';
				  list_html+='            <div class="col">員工id</div>';
				  list_html+='            <div class="col">員工密碼</div>';
				  list_html+='            <div class="col">身分證字號</div>';
				  list_html+='            <div class="col">員工姓名</div>';
				  list_html+='            <div class="col">性別</div>';
				  list_html+='            <div class="col">生日</div>';
				  list_html+='            <div class="col">員工信箱</div>';
				  list_html+='            <div class="col">員工電話</div>';
				  list_html+='            <div class="col">員工地址</div>';
				  list_html+='            <div class="col">職稱</div>';
				  list_html+='            <div class="col">員工狀態</div>';
				  list_html+='            <div class="col">門市id</div>';
				  list_html+='            <div class="col">修改</div>';
				  list_html+='            <div class="col">刪除</div>';
				  list_html+='        </div>';	  
					  
				  $.each(data.emp_list, function(index, item){
					  list_html+='		<div class="row" id="div_append">';
					  list_html+='            <div class="col AutoNewline" id="e_id">'+ item.e_id +'</div>';
					  list_html+='            <div class="col AutoNewline"><p class="para">'+ item.e_password +'</p><input name="input_e_password" class="-none" value="'+ item.e_password +'"></div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_identity +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_name +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_gender +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_birth +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_email +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_phone +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_address +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.e_title +'</div>';
					  list_html+='            <div class="col AutoNewline" id="e_status" data-e_id = "' + item.e_status + '">'+ item.e_status_view +'</div>';
					  list_html+='            <div class="col AutoNewline">'+ item.st_name +'</div>';
					  list_html+='            <div class="col">';
					  list_html+='                 <button type="button" id="modify">修改';
					  list_html+='            </div>';
					  list_html+='            <div class="col">';
					  list_html+='                 <button type="button" id="toggle_status">更新狀態';
					  list_html+='            </div>';
					  list_html+='		</div>';
				  })
				  
				  
				  $("#con_mam").html(list_html);
			  
		  },
		  error: function(){
			  console.log("error");
		  }
	  })
  })
 
 //按查詢按鈕(員工)
  $(document).on("click", "#button_search", function(){
	  let inpute = $("#input_search").val();
	  
	  let form_data = {
			  "input" : inpute
	  };
	  
	  let form_string = JSON.stringify(form_data);
	  $.ajax({
		  url: "http://localhost:8081/TEA102G3/List_one",
		  type: "POST",
		  data: form_string,
		  dataType: "json",
		  beforeSend: function(){
		  },
		  success: function(data){
			  
			  list_html = "";
			  
			  list_html+='<div class="row">';
			  list_html+='    <div class="col search-col">';
			  list_html+='        <p>查詢</p>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <input type="text" id="input_search" name=""> ';
			  list_html+='        <button type="button" id="button_search" class="btn btn-primary">查詢</button>';
			  list_html+='    </div>';
			  list_html+='    <div class="col">';
			  list_html+='        <button type="button" id="btn_add" class="btn btn-outline-primary">新增</button>';
			  list_html+='    </div>';
			  list_html+='</div>';
			  list_html+='<div class="row">';
			  list_html+='    <div class="col">';
			  list_html+='        <div class="row listAll_row">';
			  list_html+='            <div class="col">員工id</div>';
			  list_html+='            <div class="col">員工密碼</div>';
			  list_html+='            <div class="col">身分證字號</div>';
			  list_html+='            <div class="col">員工姓名</div>';
			  list_html+='            <div class="col">性別</div>';
			  list_html+='            <div class="col">生日</div>';
			  list_html+='            <div class="col">員工信箱</div>';
			  list_html+='            <div class="col">員工電話</div>';
			  list_html+='            <div class="col">員工地址</div>';
			  list_html+='            <div class="col">職稱</div>';
			  list_html+='            <div class="col">員工狀態</div>';
			  list_html+='            <div class="col">門市id</div>';
			  list_html+='            <div class="col">修改</div>';
			  list_html+='            <div class="col">刪除</div>';
			  list_html+='        </div>';	  
				  
			  $.each(data, function(index, item){
				  list_html+='		<div class="row" id="div_append">';
				  list_html+='            <div class="col AutoNewline" id="e_id">'+ item.e_id +'</div>';
				  list_html+='            <div class="col AutoNewline" id="e_password"><p class="para">'+ item.e_password +'</p><input name="input_e_password" class="-none" value="'+ item.e_password +'"></div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_identity +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_name +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_gender +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_birth +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_email +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_phone +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_address +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.e_title +'</div>';
				  list_html+='            <div class="col AutoNewline" id="e_status" data-e_id = "' + item.e_status + '">'+ item.e_status_view +'</div>';
				  list_html+='            <div class="col AutoNewline">'+ item.st_name +'</div>';
				  list_html+='            <div class="col AutoNewline">';
				  list_html+='                 <button type="button" id="modify">修改';
				  list_html+='            </div>';
				  list_html+='            <div class="col">';
				  list_html+='                 <button type="button" id="toggle_status">更新狀態';
				  list_html+='            </div>';
				  list_html+='		</div>';
			  })
			  
			  $("#con_mam").html(list_html);
		  },
		  error: function(){
			  console.log("error");
		  }
	  })
  })
  
  
//更改單一員工狀態
  $(document).on("click", "#toggle_status", function(){
//	  console.log($(this));
	  //更改狀態                 按鈕     父層最近的div屬性       同層id="e_status"   div內的文字
	  let e_status = $(this).closest("div").siblings("#e_status").attr("data-e_id");
	  let e_id = $(this).closest("div").siblings("#e_id").text();
	  
	  let div_e_status = $(this).closest("div").siblings("#e_status")
	  
//	  if(e_status == 1){
//		  div_e_status.html("0")
//	  } else {
//		  div_e_status.html("1")
//	  }	
	  
	  
	  let form_data = {
			  "e_id" : e_id,
			  "e_status" : e_status 
	  }
	  
	 let form_string = JSON.stringify(form_data);
	  $.ajax({
		  url : "http://localhost:8081/TEA102G3/change_status", 
		  type : "POST",
		  data : form_string,
		  dataType:"json",
		  beforeSend : function(){},
		  success: function(v){
				  div_e_status.html(v.e_statusView);
				  div_e_status.attr("data-e_id",v.e_st);
		  },
		  error : function(){
			  console.log("error")
		  }
	  })
  })
  
//修改員工密碼
  $(document).on("click", "#modify", function(){
	  let update_val = ($(this).closest("div").siblings("#e_password").find("[name='input_e_password']").val()).trim();
//	  console.log(update_val)
	  
	  let e_id = $(this).closest("div").siblings("#e_id").text();
	  
	  let form_data = {
			  "e_id" : e_id,
			  "e_password" : update_val
	  }
	  
	  let form_string = JSON.stringify(form_data);
	  
	  if(update_val == ""){
		  alert('請輸入')
	  } else{
		  $(this).closest("div").siblings("#e_password").find("p.para").html(update_val).toggleClass("-none");
		  $(this).closest("div").siblings("#e_password").find("[name='input_e_password']").toggleClass("-none");
		  
		  if($(this).closest("div").siblings("#e_password").find("[name='input_e_password']").hasClass("-none")){
			  
			  $.ajax({
				  url:"http://localhost:8081/TEA102G3/change_password",
				  type:"POST",
				  data: form_string,
				  beforeSend:function(){},
				  success:function(){
					  alert('更新成功')
				  },
				  error:function(){
					  console.log("error")
				  }
			  })
		  }
	  }
  })
  
//顯示所有員工
  $("#acc_mgt-tab").on("click", function(){
	  $.ajax({
		  url: "http://localhost:8081/TEA102G3/List_member",
		  type: "POST",
		  dataType: "json",
		  beforeSend: function(){},
		  success: function(data){
			  list_html = "";
				  
			  list_html +='<div class="row">';
			  list_html +='    <div class="col" >';
			  list_html +='    尋找';
			  list_html +='			   <select id="search_m_status">';
			  list_html +='			   		<option value="0">未開通</opion>';
			  list_html +='			 	 	<option value="1">買家</opion>';
			  list_html +='			   		<option value="2">賣家未驗證</opion>';
			  list_html +='			 	 	<option value="3">賣家</opion>';
			  list_html +='			   </select>';
			  list_html +='        <button type="button" id="button_search_mem" class="btn btn-primary">查詢</button>';
			  list_html +='    </div>';
			  list_html +='</div>';
			  list_html +='<div class="row">';					  
			  list_html +='    <div class="col">';
			  list_html +='        <div class="row listAll_row" id="membercol">';
			  list_html +='            <div class="col">會員id</div>';
			  list_html +='            <div class="col">電子郵件</div>';
			  list_html +='            <div class="col">姓名</div>';
			  list_html +='            <div class="col">性別</div>';
			  list_html +='            <div class="col">電話</div>';
			  list_html +='            <div class="col">地址</div>';
			  list_html +='            <div class="col">生日</div>';
			  list_html +='            <div class="col">會員狀態</div>';
			  list_html +='            <div class="col">修改</div>';
			  list_html +='        </div>';
			  $.each(data, function(index, item){
			  list_html +='            <div class="row"  id="memberrow">';
			  list_html +='                <div class="col" id="div_m_id">'+ item.m_id +'</div>';
			  list_html +='                <div class="col" id="div_m_email">'+ item.m_email +'</div>';
			  list_html +='                <div class="col">'+ item.m_name +'</div>';
			  list_html +='                <div class="col">'+ item.m_gender +'</div>';
			  list_html +='                <div class="col">'+ item.m_phone +'</div>';
			  list_html +='                <div class="col">'+ item.m_address +'</div>';
			  list_html +='                <div class="col">'+ item.m_birth +'</div>';
//			  list_html +='                <div class="col" data_status="'+ item.m_status +'">'+ item.m_statusByString +'</div>';
			  list_html +='                <div class="col" id="div_m_status">';
//			  list_html +='                    <div class="row">';
//			  list_html +='                        <div class="col button_mem"><button type="button">未開通</div>';
//			  list_html +='                        <div class="col button_mem"><button type="button">買家</div>';
//			  list_html +='                    </div>';
//			  list_html +='                    <div class="row">';
//			  list_html +='                        <div class="col button_mem"><button type="button">未驗證</div>';
//			  list_html +='                        <div class="col button_mem"><button type="button">賣家</div>';
//			  list_html +='                    </div>';
			  list_html +='					   <select id="select_m_id">';
			  list_html +='					   		<option value="0" '+ ((item.m_status == 0) ? 'selected' :'') +'>未開通</opion>';
			  list_html +='					 	 	<option value="1" '+ ((item.m_status == 1) ? 'selected' :'') +'>買家</opion>';
			  list_html +='					   		<option value="2" '+ ((item.m_status == 2) ? 'selected' :'') +'>賣家未驗證</opion>';
			  list_html +='					 	 	<option value="3" '+ ((item.m_status == 3) ? 'selected' :'') +'>賣家</opion>';
			  list_html +='					   </select>';
			  list_html +='                    <input type="hidden" name="m_id" value="'+ item.m_id +'">';
			  list_html +='                </div>';
			  list_html +='                <div class="col">';
			  list_html +='					   <button id="btn_update_mstatus">修改'
			  list_html +='                </div>';
			  list_html +='            </div>';
			  });
			  list_html +='    </div>';
			  list_html +='</div>';
			  
			  $("#mem_page").html(list_html);
		  },
		  error: function(){
			  console.log("error")
		  }
	  })
  })
  
//修改會員狀態
  $(document).on("click", "#btn_update_mstatus",function(){
	  let update_val = $(this).closest("div").siblings("#div_m_status").find("#select_m_id").val();
	  let update_m_id = $(this).closest("div").siblings("#div_m_id").text();
	  let update_m_email = $(this).closest("div").siblings("#div_m_email").text();
	  console.log(update_m_id)
	  
	  let form_data = {
		"m_status" : update_val,
		"m_id" : update_m_id,
		"m_email" : update_m_email
	  }
	  
	  let form_str = JSON.stringify(form_data);
	  $.ajax({
		  url:"http://localhost:8081/TEA102G3/Update_m_status",
		  type: "POST",
		  data: form_str,
		  dataType: "json",
		  beforeSend: function(){},
		  success: function(data){
			  update_val = data.m_status;
		  },
		  error: function(){
			  console.log("error")
		  }
	  })
  })
  
//按查詢按鈕(會員)
  $(document).on("click", "#button_search_mem", function(){
	 let input_val = $("#search_m_status").val();
	 
	 let form_data = {
			 "m_status" : input_val
	 }
	 
	 let form_str = JSON.stringify(form_data);
	 $.ajax({
		 url: "http://localhost:8081/TEA102G3/Search_m_status",
		 type: "POST",
		 data: form_str,
		 dataType: "json",
		 beforeSend: function(){},
		 success: function(data){
			 list_html = "";
			 
			 list_html +='<div class="row">';
			  list_html +='    <div class="col" >';
			  list_html +='    尋找';
			  list_html +='			   <select id="search_m_status">';
			  list_html +='			   		<option value="0">未開通</opion>';
			  list_html +='			 	 	<option value="1">買家</opion>';
			  list_html +='			   		<option value="2">賣家未驗證</opion>';
			  list_html +='			 	 	<option value="3">賣家</opion>';
			  list_html +='			   </select>';
			  list_html +='        <button type="button" id="button_search_mem" class="btn btn-primary">查詢</button>';
			  list_html +='    </div>';
			  list_html +='</div>';
			  list_html +='<div class="row">';					  
			  list_html +='    <div class="col">';
			  list_html +='        <div class="row listAll_row" id="membercol">';
			  list_html +='            <div class="col">會員id</div>';
			  list_html +='            <div class="col">電子郵件</div>';
			  list_html +='            <div class="col">姓名</div>';
			  list_html +='            <div class="col">性別</div>';
			  list_html +='            <div class="col">電話</div>';
			  list_html +='            <div class="col">地址</div>';
			  list_html +='            <div class="col">生日</div>';
			  list_html +='            <div class="col">會員狀態</div>';
			  list_html +='            <div class="col">修改</div>';
			  list_html +='        </div>';
			  $.each(data, function(index, item){
			  list_html +='            <div class="row"  id="memberrow">';
			  list_html +='                <div class="col" id="div_m_id">'+ item.m_id +'</div>';
			  list_html +='                <div class="col" id="div_m_email">'+ item.m_email +'</div>';
			  list_html +='                <div class="col">'+ item.m_name +'</div>';
			  list_html +='                <div class="col">'+ item.m_gender +'</div>';
			  list_html +='                <div class="col">'+ item.m_phone +'</div>';
			  list_html +='                <div class="col">'+ item.m_address +'</div>';
			  list_html +='                <div class="col">'+ item.m_birth +'</div>';
//			  list_html +='                <div class="col" data_status="'+ item.m_status +'">'+ item.m_statusByString +'</div>';
			  list_html +='                <div class="col" id="div_m_status">';
			  list_html +='					   <select id="select_m_id">';
			  list_html +='					   		<option value="0" '+ ((item.m_status == 0) ? 'selected' :'') +'>未開通</opion>';
			  list_html +='					 	 	<option value="1" '+ ((item.m_status == 1) ? 'selected' :'') +'>買家</opion>';
			  list_html +='					   		<option value="2" '+ ((item.m_status == 2) ? 'selected' :'') +'>賣家未驗證</opion>';
			  list_html +='					 	 	<option value="3" '+ ((item.m_status == 3) ? 'selected' :'') +'>賣家</opion>';
			  list_html +='					   </select>';
			  list_html +='                    <input type="hidden" name="m_id" value="'+ item.m_id +'">';
			  list_html +='                </div>';
			  list_html +='                <div class="col">';
			  list_html +='					   <button id="btn_update_mstatus">修改'
			  list_html +='                </div>';
			  list_html +='            </div>';
			  });
			  list_html +='    </div>';
			  list_html +='</div>';
			  
			  $("#mem_page").html(list_html);
		 },
		 error: function(){
			 console.log("error")
		 }
	 })
  })