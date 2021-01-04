
const host = location.host;
const path = location.pathname;
const context = location.pathname.substr(0, path.indexOf("/", 1));
const wsURL = "ws://" + host + context + "/WebSocket";

console.log(wsURL)

connect();

function connect() {
    let ws = new WebSocket(wsURL);

    ws.onopen = (e) => {
        console.log("onopen")
    }

    ws.onmessage = (e) => {
        console.log("onmessage")
        let data = JSON.parse(e.data);
        
        showToast(data);
    }

}


function showToast(data) {
    $("#showToast").html("");

    let addthing =
    `<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="5000">
        <div class="toast-header">
            <img src="..." class="rounded mr-2" alt="...">
            <strong class="mr-auto">New Product</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="toast-body">
            <small>${data.message}</small>
        </div>
    </div>`
    announcement()
    $("#showToast").append(addthing);
    $(".toast").toast("show");
}

