
let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            //function이 아닌 이유? -> this를 바인딩하기 위해서
            this.save();
        });

        /*
        $("#btn-login").on("click", () => {
            //function이 아닌 이유? -> this를 바인딩하기 위해서
            this.login();
        });

        */

    },

    save: function () {
        // alert("user의 save함수 호출");
        let data = {
            username: $("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        console.log(data);

        //ajax 호출은 default으로 비동기 호출. (따라서 순차적으로 실행 x)
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),  //http body 데이터-> mine 필요 json문자열
            contentType: "application/json; charset=utf-8",    //body 데이터가 어떤 타입인지
            dataType: "json" //기본적으로 string (생긴게 json이라면) => js오브젝트로 변경해준다.
        }).done(function (resp) {   //성공시
            if(resp.status === 500){
                alert("회원가입에 실패하였습니다.");
            }else{
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청하기

    }

    /*login: function () {
        // alert("user의 save함수 호출");
        let data = {
            username: $("#username").val(),
            password:$("#password").val(),
        };

        console.log(data);

        //ajax 호출은 default으로 비동기 호출. (따라서 순차적으로 실행 x)
        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data),  //http body 데이터-> mine 필요 json문자열
            contentType: "application/json; charset=utf-8",    //body 데이터가 어떤 타입인지
            dataType: "json" //기본적으로 string (생긴게 json이라면) => js오브젝트로 변경해준다.
        }).done(function (resp) {   //성공시
            alert("로그인이 완료되었습니다.");
            console.log(resp)
            location.href = "/";
        }).fail(function (error) {   //실패시
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청하기

    }

     */
};


index.init();