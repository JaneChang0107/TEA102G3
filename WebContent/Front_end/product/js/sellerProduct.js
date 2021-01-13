let pathname = location.pathname;
let position = pathname.indexOf("/", 2);
const contextPath = pathname.substr(0, position);

console.log(contextPath)

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
                <td data-status="${value.p_status}">${value.p_statusMeans}</td>
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