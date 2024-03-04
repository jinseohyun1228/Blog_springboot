
let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            //function이 아닌 이유? -> this를 바인딩하기 위해서
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        $("#btn-btn-save").on("click", () => {
            this.replySave();
        });




    },

    save: function () {
        // alert("user의 save함수 호출");
        let data = {
            title: $("#title").val(),
            content:$("#content").val()
        };


        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {   //성공시
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청하기
    },

    update: function () {
        let id = $("#id").val()

        let data = {
            title: $("#title").val(),
            content:$("#content").val()
        };


        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {   //성공시
            alert("글 수정이 완료되었습니다.");
            location.href = "/board/"+id;
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        });
    },

    deleteById: function () {
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function (resp) {   //성공시
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청하기

    },
    replySave: function () {
        // alert("user의 save함수 호출");
        let data = {
            content:$("#reply-content").val(),
            boardId:$("#boardId").val(),
            userId:$("#userdId").val()
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {   //성공시
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청하기
    }

};


index.init();