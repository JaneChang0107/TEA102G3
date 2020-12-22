$("#uploadImg").change(function(e) {
    $("#showImg").html("");
    for(let i = 0; i < this.files.length; i++) {
        let result = showImg(i);
        $("#showImg").append(`<img src="${result}" class="viewImg">`);
    }
})

$(".uploadImgBtn").on("click", function() {
    let result = showImg(0);
    $(this).siblings("img").attr("src", result);
});

function showImg(which) {
    let reader = new FileReader();
    let result = "";
    reader.readAsDataURL(e.target.files[which]);
    reader.addEventListener("load", function() {
        result = reader.result;  
    })
    return result;
}