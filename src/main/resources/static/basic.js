$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/posts",
        success:function (response) {
            console.log(response);
            postappend(response);
        }
    })
})
function postappend(listPostDto){
    for (let i = 0; i < listPostDto.length; i++) {
        let temphtml = postHtml(listPostDto[i]);
        $("#content_main").append(temphtml);
    }
}
function datetime(createdAt){
    let datetime = new Date(createdAt)
    let date = datetime.getDate();
    let month = datetime.getMonth()+1;
    let year = datetime.getFullYear();
    let hours = datetime.getHours();
    let minute = datetime.getMinutes();

    return `${year}/${month}/${date} ${hours}시 ${minute}분`
}
function postHtml(postDto){
    let temphtml = `<div class="post_wrap" >
            <div class="content" onclick='postopen(${JSON.stringify(postDto)})'>
                <div class="content_title">
                    <p>${postDto.title}</p>
                </div>
                <div class="content_content">
                    <div>
                        ${postDto.content}
                    </div>
                </div>
                <div class="content_footer">
                    <p>written by ${postDto.name} ${datetime(postDto.createdAt)}</p>
                </div>
            </div>
        </div>`
    return temphtml
}
function postopen(postDto){
    var myModal = new bootstrap.Modal(document.getElementById('postmodal'), {
        keyboard: false
    })
    myModal.show();
    console.log(postDto);
    $("#modal_title").text(postDto.title);
    $("#modal_content").text(postDto.content);
    $("#modal_content").append(`<div class="modal_content_footer" id="content_footer">
                                written by ${postDto.name} ${datetime(postDto.createdAt)}
                                </div>`)

}
function postingopen(){
    var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
        keyboard: false
    })
    myModal.show();
}

function posting(){
    let name = $("#name").val();
    let title = $("#title").val();
    let content = $("#content").val();
    if(name.trim() == "" || title.trim() =="" || content.trim() == ""){
        alert("안적으신 부분이 있습니다! 확인해주세요");
        return;
    }

    console.log(title)
    console.log(content)
    console.log(name);
    let postDto = {
        name : name,
        title : title,
        content : content
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/posts",
        data:JSON.stringify(postDto),
        contentType:"application/json",
        success:function (response) {
            console.log(response);
            window.location.reload();
        }
    })
}