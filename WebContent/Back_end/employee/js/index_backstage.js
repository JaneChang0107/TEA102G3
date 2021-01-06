//載入頁面時 
$.ajax({
        url: "http://localhost:8081/TEA102G3/Refresh",           // 資料請求的網址
        type: "POST",                 // GET | POST | PUT | DELETE | PATCH
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function(){       // 在 request 發送之前執行
        },
        success: function(data){      // request 成功取得回應後執行
        	console.log(data);
        	
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
       	  list_html +='                <select size="1" id="st_id" name="st_id">';
       	  for(let i = 0; i < data.store.length; i++){
       	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_id ;         		  
       	  }       	 
       	  list_html +='				</select>';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
       	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
       	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
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
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	
       	  $("#update_without").html(list_html);
       	  console.log("ajax_action")
       	  
 
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
	 console.log(e_id)
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
       	  list_html +='                <select size="1" id="st_id" name="st_id">';
       	  for(let i = 0; i < data.store.length; i++){
       	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_id ;         		  
       	  }       	 
       	  list_html +='				</select>';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
       	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
       	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
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
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	
       	$("#update_without").html(list_html);
        console.log("ajax_action")
       	  
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
	 let e_title = $("[name='e_title']:checked").val();
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
	 
	 console.log(e_id)
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
       	  list_html +='                <select size="1" id="st_id" name="st_id">';
       	  for(let i = 0; i < data.store.length; i++){
       	  list_html +='					<option value="'+ data.store[i].st_id +'" '+((data.emp.st_id==data.store[i].st_id)? 'selected':'' )+'>'+ data.store[i].st_id ;         		  
       	  }       	 
       	  list_html +='				</select>';
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='        <div class="row">';
       	  list_html +='            <div class="col">';
       	  list_html +='                <p>職稱:</p>';
       	  list_html +='            </div>';
       	  list_html +='            <div class="col">';											
       	  list_html +='             <input type="radio" name="e_title" size="45" value="EMPLOYEE" '+ ((data.emp.e_title == 'EMPLOYEE') ? 'checked' : '') +'>EMPLOYEE';
       	  list_html +='				<input type="radio" name="e_title" size="45" value="BOSS" '+ ((data.emp.e_title == 'BOSS') ? 'checked' : '')+'>BOSS';
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
       	  list_html +='            </div>';
       	  list_html +='        </div>';
       	  list_html +='    </div>';
       	
       	$("#update_without").html(list_html);
        console.log("ajax_action")
       	  
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
			  console.log(data);
//			  for(let i=0; data){}
			  alert(data);
			  
		  },
		  error: function(xhr){         // request 發生錯誤的話執行
			  console.log("error");
		  }
	  });     
  });

//查全部員工