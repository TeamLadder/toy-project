// 채팅창의 스크롤을 아래로 내리는 애니메이션을 실행하는 함수
$(".messages").animate({scrollTop: $(document).height()}, "fast");

// 프로필 이미지를 클릭했을 때 상태 옵션을 토글하는 함수
$("#profile-img").click(function () {
    $("#status-options").toggleClass("active");
});

// 확장 버튼을 클릭했을 때 프로필과 연락처를 확장 또는 축소하는 함수
$(".expand-button").click(function () {
    $("#profile").toggleClass("expanded");
    $("#contacts").toggleClass("expanded");
});

// 상태 옵션 중 하나를 클릭했을 때 해당 상태를 활성화하고 프로필 이미지에 클래스를 적용하는 함수
$("#status-options ul li").click(function () {
    $("#profile-img").removeClass();
    $("#status-online").removeClass("active");
    $("#status-away").removeClass("active");
    $("#status-busy").removeClass("active");
    $("#status-offline").removeClass("active");
    $(this).addClass("active");

    if ($("#status-online").hasClass("active")) {
        $("#profile-img").addClass("online");
    } else if ($("#status-away").hasClass("active")) {
        $("#profile-img").addClass("away");
    } else if ($("#status-busy").hasClass("active")) {
        $("#profile-img").addClass("busy");
    } else if ($("#status-offline").hasClass("active")) {
        $("#profile-img").addClass("offline");
    } else {
        $("#profile-img").removeClass();
    }
    // 상태 옵션을 닫음
    $("#status-options").removeClass("active");
});

// 새로운 메시지를 추가하는 함수
// function newMessage() {
//     // 입력된 메시지 가져오기
//     message = $(".message-input input").val();
//     // 메시지가 공백인 경우 아무것도 안 함
//     if ($.trim(message) == '') {
//         return false;
//     }
//     // 보낸 메시지를 채팅창에 추가하고 입력창 비우기
//     $('<li class="sent"><!--<img src="../static/img/짱구1.jpeg" alt="" />--><p>' + message + '</p></li>').appendTo($('.messages ul'));
//     $('.message-input input').val(null);
//     // 활성화된 연락처의 프리뷰에 사용자의 메시지를 표시
//     $('.contact.active .preview').html('<span>You: </span>' + message);
//     // 채팅창을 아래로 스크롤
//     $(".messages").animate({scrollTop: $(document).height()}, "fast");
// };

// '전송' 버튼 클릭 시 새로운 메시지 추가
// $('.submit').click(function () {
//     newMessage();
// });

// 엔터 키를 누를 때도 새로운 메시지 추가
// $(window).on('keydown', function (e) {
//     if (e.which == 13) {
//         newMessage();
//         return false;
//     }
// });

//채팅 소켓
document.addEventListener("DOMContentLoaded", function() {
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log("Connected: " + frame);
            stompClient.subscribe('/topic/messages', function(message) {
                showMessage(JSON.parse(message.body).content, false);
            });
        });
    }

    document.querySelector("#send").addEventListener("click", function() {
        var messageContent = document.querySelector("#messageInput").value;
        if(messageContent && stompClient) {
            var chatMessage = {
                content: messageContent,
            };
            stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
            // 메시지 전송 후 채팅창에 메시지 추가
            showMessage(messageContent, true);
            document.querySelector("#messageInput").value = '';
        }
    });

    // 메시지 표시 함수 수정
    // isSender 변수를 추가하여 메시지가 보내는 사람으로부터 온 것인지 구분
    function showMessage(message, isSender) {
        var messageElement = document.createElement('li');
        messageElement.classList.add(isSender ? 'sent' : 'replies');
        // 메시지에 보낸 사람 또는 받는 사람에 따른 스타일을 적용
        var messageText = document.createElement('p');
        messageText.textContent = message;
        messageElement.appendChild(messageText);
        document.querySelector("#messageArea").appendChild(messageElement);
        // 채팅창을 아래로 스크롤
        $(".messages").animate({scrollTop: $(document).height()}, "fast");
    }

    connect();
});