let pathname = location.pathname;
let position = pathname.indexOf("/", 2);
const contextPath = pathname.substr(0, position);

$.ajax({
    url: contextPath + "/ProductServlet",
    type: "GET",
    data: {
    	"action" : "sellerProduct"
    },
    datatype: "json",
    success: function(data) {
        let count = 0;
        $("#sellerProduct").html(
            `<h1>我的商品</h1>
            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">商品名稱</th>
                    <th scope="col">種類</th>
                    <th scope="col">價格</th>
                    <th scope="col">數量</th>
                    <th scope="col">狀態</th>
                    <th scope="col">修改</th>
                    <th scope="col">刪除</th>
                    </tr>
                </thead>
                <tbody id="products">
                    
                </tbody>
            </table>`
        );

        $.each(JSON.parse(data), (index, value) => {
            console.log(value.p_name);
            $("#products").append(
                `<tr>
                <td id="${++count}" data-pid="${value.p_id}" scope="row">${count}</td>
                <td id="name${count}">${value.p_name}</td>
                <td id="type${count}" data-type="${value.pt_id}">${value.pt_idName}</td>
                <td id="price${count}">${value.p_price}元</td>
                <td id="count${count}">${value.p_count}個</td>
                <td data-status="${value.p_status}">${switchStatus(value.p_statusMeans)}</td>
                <td>
                    <form action="${contextPath}/ProductServlet" method="post">
                        <input type="hidden" name="pid" value="${value.p_id}">
                        <input type="hidden" name="action" value="updateOne">
                        <input type="submit" class="btn btn-primary" ${value.p_status == 99 || value.p_status == 0 ? "style=display:none" : ""} value="修改">
                    </form>
                </td>
                <td>
                    <form action="${contextPath}/ProductServlet" method="post">
                        <input type="hidden" name="pid" value="${value.p_id}">
                        <input type="hidden" name="action" value="sellerDelete">
                        <input type="button" class="btn checkBtn btn-primary" value="刪除">
                    </form>
                </td>
                </tr>`
            )
        })
    }
});

$(document).on("click", ".checkBtn", (e) => {
    if(confirm("確定要刪除?")) {
    	$(e.target).closest("form").submit();
    }
});

$(document).on("click", ".statusBtn", (event) => {
        let pstatus = $(event.target).closest("td").attr("data-status");
        let pid = $(event.target).closest("tr").find("td").eq(0).attr("data-pid");

        $.ajax({
            url : contextPath + "/ProductAjaxChangeStatus",
            type : "POST",
            data : {
                "pstatus" : pstatus,
                "pid" : pid,
            },
            datatype : "json",
            success : (e) => {
                let value = JSON.parse(e);
                $(event.target).closest("td").attr("data-status", value.p_status);
                $(event.target).closest("td").html(switchStatus(value.p_statusMeans));
            }
        });

});


function switchStatus(status) {
    switch (status) {
        case "上架中" :
            return `<input type="button" class="btn statusBtn btn-primary" value="上架中">`;
        case "下架中" :
            return `<input type="button" class="btn statusBtn btn-primary" value="下架中">`;
        case "審核中(審核後上架)" :
            return `<input type="button" class="btn statusBtn btn-primary" value="審核後上架">`;;
        case "審核中(審核後下架)" :
            return `<input type="button" class="btn statusBtn btn-primary" value="審核後下架">`;
        case "已售出" :
            return "已售出";
        case "待出貨" :
            return "待出貨";
        case "無法上架" :
            return "無法上架";
        default :
            return "ssss";
    }
}