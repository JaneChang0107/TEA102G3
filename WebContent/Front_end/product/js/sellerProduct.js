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
        $("#product").html("");
        $.each(JSON.parse(data), (index, value) => {
            console.log(value.p_name);
            getptVO(value.pt_id).then((ptVO) => {
                $("#products").append(
                    `<tr class="alert alert-primary">
                    <td>${++count}</td>
                    <td>${value.p_name}</td>
                    <td>${ptVO.pt_platform} ${ptVO.pt_kind}</td>
                    <td>${value.p_price}元</td>
                    <td>${value.p_count}個</td>
                    <td data-status="${value.p_status}">${value.p_statusMeans}</td>
                    <td>
                        <form action="${contextPath}/ProductServlet" method="post">
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="updateOne">
                            <input type="submit" class="btn btn-primary" value="修改">
                        </form>
                    </td>
                    <td>
                        <form action="${contextPath}/ProductServlet" method="post">
                            <input type="hidden" name="pid" value="${value.p_id}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" class="btn btn-primary" value="刪除">
                        </form>
                    </td>
                </tr>`
                )
            });
        })
    }
});
function getptVO(ptid) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url : contextPath + "/ProductTypeAjax",
            type : "GET",
            data : {
                "action" : "getOneType",
                "ptid" : ptid
            },
            datatype : "json",
            success : function(data) {
                resolve(JSON.parse(data));
            }
        });
    });
}