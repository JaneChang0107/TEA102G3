
let contextPath = $("#contextPath").val();
$.ajax({
    url: contextPath + "/ProductTypeAjax",
    type : "GET",
    dataType : "json",
    success : function(data) {
        $.each(data, function(index, value) {
            $("#ptype").append(
                `<option value="${value.pt_id}">${value.pt_platform} ${value.pt_kind}</option>`
            )
        })
    
    }
})


$("#searchBtn").click(function() {
    let type = $("#ptype").val();
    let pname = $("#pname").val();
    // console.log(pname)
    var data = {
        "type" : type,
        "pname" : pname
    }
    $.ajax({
        url: contextPath + "/Productajax",
        type : "GET",
        dataType : "json",
        data : data,
        success : function(data) {
            // console.log(data);
            $("#allProduct").html("");
            $.each(data, function(index, value) {

                findPicture(value.p_id).then((data) => {
                    
                    let ppid = data;
                    $("#allProduct").append(
                        `<tr>
                            <td>${value.p_name}</td>
                            <td>${value.pt_id}</td>
                            <td>${value.p_count}</td>
                            <td>${value.p_price}</td>
                            <td>${value.p_addDateSec}</td>
                            <td><img src="${contextPath}/ShowPicture?type=pp&id=${ppid}"></td>
                            <td>
                                <form action="${contextPath}/ProductServlet" method="POST">
                                    <input type="hidden" name="pid" value="${value.p_id}">
                                    <input type="hidden" name="action" value="findthis">
                                    <input type="submit" value="詳細">
                                </form>
                            </td>
                        </tr>`
                    );
                })
            });
        }
    });
});

function findPicture(pid) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: contextPath + "/Productajax",
            type : "GET",
            dataType : "text",
            data : {
                "pid" : pid
            },
            success : function(data) {
                resolve(data);
                
            }
        })
    }) 
}
