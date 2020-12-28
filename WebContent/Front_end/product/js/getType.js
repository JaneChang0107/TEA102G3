$(function() {
    let contextPath = $("#contextPath").val();
    let type = $("#type").val();
    let pname = $("#name").val();
    
    console.log(type);
    console.log(pname);

    $.ajax({
        url: contextPath + "/ProductTypeAjax",
        type : "GET",
        dataType : "json",
        success : function(data) {
            $("#ptype").html(`<option value="no">選擇類型</option>`);
            $.each(data, function(index, value) {
                $("#ptype").append(
                    `<option value="${value.pt_id}" ${value.pt_id == type ? "selected" : ""}>${value.pt_platform} ${value.pt_kind}</option>`
                )
            })
            $("#searchBar").val(pname);
        }
    })
})
$("#search").click(function() {
    $("#searchForm").submit();
});