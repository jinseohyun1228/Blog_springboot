
let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            //function이 아닌 이유? -> this를 바인딩하기 위해서
            this.save();
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

    }

};


index.init();