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
        $("#product").html("");
        $.each(JSON.parse(data), function(index, value) {
            $("#sellerProduct").append(
                `			<tr>
                <td>${value.p_id}</td>
                <td>${value.p_name}</td>
                <td>${value.p_price}</td>
                <td>${value.pt_id}</td>
                <td>${value.p_count}</td>
                <td>${value.p_addDateSec}</td>
                <td>${value.p_reviseDateSec}</td>
                <td>${value.p_status}</td>
                <td>${value.m_id}</td>
                <td>
                    <form action="${contextPath}/ProductServlet" method="post">
                        <input type="hidden" name="pid" value="${value.p_id}">
                        <input type="hidden" name="action" value="updateOne">
                        <input type="submit" value="修改">
                    </form>
                </td>
                <td>
                    <form action="${contextPath}/ProductServlet" method="post">
                        <input type="hidden" name="pid" value="${value.p_id}">
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" value="刪除">
                    </form>
                </td>
            </tr>`
        )
        })
    }
});