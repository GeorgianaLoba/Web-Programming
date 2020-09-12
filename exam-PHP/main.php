<?php
    include_once 'connect.php';

    if (!isset($_SESSION['id'])) {
        header("Location: index.php");
    }
?>

<!DOCTYPE html>
<html>
<head>
    <title> Assets </title>
    <script src="jquery-2.0.3.js"></script>
    <script>



    $(document).ready(function () {
        currentPage = 1;
        recordPerPage = 5;
        lengthArray = 0;
        jsonArr = "";

        fetchData();
         $('#prev').click(function(){
            if (currentPage > 1){
                currentPage -=1;
                paginate(currentPage);
            }                   
         });

         $('#next').click(function(){
            if (currentPage<lengthArray){
                currentPage +=1;
                paginate(currentPage);
            }
         });
    });

    function fetchData() {
        id = $('#id').text();
        $.get("fetchData.php",
            function (data, status) {
            var json = jsonParse(data);

            mostViewedFile(json);

            jsonArr = json;
            lengthArray = json.length;

            paginate(1);
            // var results =
            //     "<table> <tr> <th> File Id Id </th> <th> User Id </th> <th> File Name  </th> <th> File Path </th> <th> Size </th> </tr>";
            //     $.each(json, function(index, document){
            //         results+="<tr>";
            //         $.each(document, function(key, value){
            //         results+="<td>" + value + "</td>"; 
            //      });
            // results+="</tr>";
            // });
            // $("#mydata").html(results);
            });
    }

function jsonParse(text) {
    var json;
    try {
        json = JSON.parse(text);
    } catch (e) {
        return false;
    }
    return json;
}


function paginate(page){
    // $('#prev').hide();
    // if (len<=5){    $('#next').hide(); }

    if (page === 1) { $('#prev').hide();}
    else { $('#prev').show();}
    if (page * 5 >= lengthArray) {$('#next').hide();}
    else {$('#next').show();}


    var results = "<table> <tr> <th> File Id Id </th> <th> User Id </th> <th> File Name  </th> <th> File Path </th> <th> Size </th> </tr>";
    let maxx = page * recordPerPage;
    if (maxx > lengthArray) { maxx = lengthArray;}
    for (var i = (page-1) * recordPerPage; i < maxx; i++) {
        results+="<tr>";
        results+="<td>" + jsonArr[i].id + "</td>"; 
        results+="<td>" + jsonArr[i].userid + "</td>"; 
        results+="<td>" + jsonArr[i].filename + "</td>"; 
        results+="<td>" + jsonArr[i].filepath + "</td>"; 
        results+="<td>" + jsonArr[i].size + "</td>"; 
        results+="</tr>";
    }
    $("#mydata").html(results);
}

function mostViewedFile(data){
    freq={};
    $.each(data, function(index, document){
        $.each(document, function(key, value){
            if (key === 'filename'){    
                if (!freq[value]) {
                    freq[value] = 0;
                }
                freq[value] += 1;
            }
        });
    });
    max = 0;
    maxFile = "";
    $.each(freq, function(key, value){
        if (value > max){
            max = value;
            maxFile = key;
        }
    });
    $('#mostViewed').text(maxFile);
    $('#counter').text(max);
}

</script>
</head>
<body>
    <section id="data">
        <div id="mydata">
        </div>
        <button id="prev" value="prev">Previous</button>
        <button id="next" value="next">Next</button>

    </section>

    <p> Most viewed file is: <h4 id="mostViewed"></h4> with <h4 id="counter"></h4> occurences! </p>


</body>
</html>