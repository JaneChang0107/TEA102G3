let pathname = location.pathname;
let position = pathname.indexOf("/", 2);
const contextPath = pathname.substr(0, position);

//console.log(contextPath)

whichProduct("all");

$("#selectStatus").on("change", () => {
	let selected = $("#selectStatus").val();
	whichProduct(selected);
})

function whichProduct(which) {
    $.ajax({
        url: contextPath + "/ProductServlet",
        type: "GET",
        data: {
            "action" : "allProduct",
            "status" : which
        },
        datatype: "json",
        success: function(data) {
            let count = 0;
            $("#allProduct").html(
                `<table class="table">
                    <thead>
                        <tr>
                        <th scope="col">商品編號</th>
                        <th scope="col">商品名稱</th>
                        <th scope="col">種類</th>
                        <th scope="col">價格</th>
                        <th scope="col">數量</th>
                        <th scope="col">狀態</th>
                        <th scope="col">修改</th>
                        <th scope="col">審核</th>
                        <th scope="col">刪除</th>
                        </tr>
                    </thead>
                    <tbody id="products">
                        
                    </tbody>
                </table>`
            );

            $.each(JSON.parse(data), (index, value) => {
//                console.log(value.p_name);
                $("#products").append(
                    `<tr>
                    <td id="${value.p_id}" scope="row">${value.p_id}</td>
                    <td id="name${count}">${value.p_name}</td>
                    <td id="type${count}" data-type="${value.pt_id}">${value.pt_idName}</td>
                    <td id="price${count}">${value.p_price}元</td>
                    <td id="count${count}">${value.p_count}個</td>
                    <td data-status="${value.p_status}">${value.p_statusMeans}</td>
                    <td>
                        <form action="${contextPath}/ProductServlet" method="post">
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="updateOne">
                            <input type="submit" class="btn btn-primary" value="修改">
                        </form>
                    </td>
                    <td>
                        <form class="check">
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="check">
                            <input type="hidden" name="checked" value="ok">
                            <input type="button" class="checkBtn btn-primary"${(value.p_status != 11 && value.p_status != 12) ? "style='display : none'" : ""} value="通過">
                        </form>
                        <form class="check">
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="check">
                            <input type="hidden" name="checked" value="no">
                            <input type="button" class="checkBtn btn-primary" ${(value.p_status != 11 && value.p_status != 12) ? "style='display : none'" : ""} value="不通過">
                        </form>
                    </td>
                    <td>
                        <form>
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="delete">
                            <input type="button" class="checkBtn btn-primary" value="刪除">
                        </form>
                    </td>
                </tr>`
                )
            })
        }
    });
}


$("#allProduct").on("click", ".checkBtn", (e) => {

    let formData = $(e.target).closest("form").serializeArray();

    if(confirm("確定" + e.target.value)) {

        $.ajax({
            url : contextPath + "/ProductServlet",
            type : "POST",
            data : formData,
            success : () => {
                whichProduct("all");
            }
        });
    }
})