function aa(post){
    console.log(post)
    alert("asdasdasdasdasd")
}
function bb(post){
    console.log(post)
    alert("asdasdasdasdasd")
}
function saveComment(postId){

    let content = $("#comment_input").val();

    console.log(content);
    console.log(postId);
    if(content.trim()==""){
        alert("댓글 내용을 입력해주세요");
        return;
    }

    let commentDto ={
        content : content,
        postId : postId
    }
    $.ajax({
        type: "POST",
        url: "/api/comments",
        data:JSON.stringify(commentDto),
        contentType:"application/json",
        success:function (response) {
            console.log(response)
            if(response == false){
                alert("로그인후 이용가능합니다!");
                window.location.href ="http://localhost:8080/user/login"
            }else{
                alert("댓글 작성 성공");
                location.reload();
            }
        }
    })
}
function deletePost(postId){

    var result = confirm("정말 삭제하시겠습니까?")
    if(!result)
        return;
    let putUrl = `/api/posts/${postId}`;
    $.ajax({
        type: "DELETE",
        url: putUrl,
        contentType:"application/json",
        success:function (response) {
            alert("삭제 완료");
            window.location.href = "http://localhost:8080"
        }
    })
}
function deleteComment(commentId){

    var result = confirm("정말 삭제하시겠습니까?")
    if(!result)
        return;
    let putUrl = `/api/comments/${commentId}`;
    $.ajax({
        type: "DELETE",
        url: putUrl,
        contentType:"application/json",
        success:function (response) {
            alert("삭제 완료");
            location.reload();
        }
    })
}

function modifyComment(commentId){
    console.log(commentId);
    let text = $(`#comment_textarea${commentId}`).val();
    let putUrl = `/api/comments/${commentId}`;
    if(text.trim()==""){
        alert("댓글 수정 내용을 입력해주세요");
        return;
    }
    let commentRequestDto = {
        content:text
    }
    $.ajax({
        type: "PUT",
        url: putUrl,
        data:JSON.stringify(commentRequestDto),
        contentType:"application/json",
        success:function (response) {
            alert("댓글 수정 완료!")
            location.reload();
        }
    })
}
function cancleModify(commentId){
    $(`#comment_textarea${commentId}`).css('display','none');
    $(`#modifybutton${commentId}`).css('display','block');
    $(`#dropbutton${commentId}`).css('display','block');
    $(`#comment_text${commentId}`).css('display','block');
    $(`#modifySubmit${commentId}`).css('display','none');
    $(`#modifyCancle${commentId}`).css('display','none');
}

function modifyModalOpen(comment){
    console.log(comment);
    let text = $(`#comment_text${comment.id}`).text();

    $(`#comment_textarea${comment.id}`).css('display','block');
    $(`#modifybutton${comment.id}`).css('display','none');
    $(`#dropbutton${comment.id}`).css('display','none');
    $(`#comment_text${comment.id}`).css('display','none');
    $(`#modifySubmit${comment.id}`).css('display','block');
    $(`#modifyCancle${comment.id}`).css('display','block');
    $(`#comment_textarea${comment.id}`).val(`${text}`);
}

function goLoginPage(){
    window.location.href="http://localhost:8080/user/login"
}
function logout(){
    window.location.href="http://localhost:8080/user/logout"
}