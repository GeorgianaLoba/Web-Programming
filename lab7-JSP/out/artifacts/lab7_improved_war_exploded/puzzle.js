function showPuzzle(){
    var user_name = $('#username').text();
    $.get("/servlet/puzzle",
        {"username": user_name}, function(response) {
        $.each(response, function (key, value) {
            if (key !== "-1") {
                document.getElementById(key.toString()).innerHTML = "<img src='/images/" + value + "'/>";
            }
            else{
                document.getElementById("moves").innerText = value;

            }
        });
    });
}

    $(document).ready(function(){
    $(document).on('click', 'img', function (){
        var source = $(this).attr('src');
        var position = $(this).parent().attr('id');
        $.post("/servlet/puzzle", {"position": position, "source": source},
            function (response) {
            $.each(response, function (key, value) {
                if (key!== "-1"){
                    document.getElementById(key.toString()).innerHTML = "<img src='/images/" + value + "'/>";
                }
                else{
                    document.getElementById("moves").innerText = value;
                }
            })})
            .done(function () {
                checkWin();
        });
    });
});

function checkWin() {
    var countCorrect = 0;
    var images = document.getElementsByTagName('img');
    var len=images.length
    for (var i=0; i<len;i++){
        var src = images[i].getAttribute('src').split("/")[2].split(".")[0];
        var id = images[i].parentElement.getAttribute('id');
        if (src === id) countCorrect+=1;
    }
    if (countCorrect === len) {
        var username = document.getElementById("username").innerText;
        var moves = document.getElementById("moves").innerText;
        $.post("/servlet/done", {"username": username, "moves": moves, "status": "won"});
    }
}

// function restart() {
//     var username = document.getElementById("username").innerText;
//     var moves = document.getElementById("moves").innerText;
//     alert('here');
//     $.post("/servlet/done", {"username": username, "moves": moves, "status": "abandoned"},
//         function (data){alert(data);});
//
// }
