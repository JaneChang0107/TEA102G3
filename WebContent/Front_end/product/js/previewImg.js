$("#uploadImg").change(function(e) {
    $("#showImg").html("");
    for(let i = 0; i < this.files.length; i++) {
        let reader = new FileReader();
        let result = "";
        reader.readAsDataURL(e.target.files[i]);
        reader.addEventListener("load", function() {
            result = reader.result;
            $("#showImg").append(`<img src="${result}" class="viewImg">`);
        });
    }
})

$(".uploadImg").change(function(e) {
    let reader = new FileReader();
    let result = "";
    let img = this;
    reader.readAsDataURL(e.target.files[0]);
    reader.addEventListener("load", function() {
        result = reader.result;
        $(img).siblings("img").attr("src", result);
    });
});