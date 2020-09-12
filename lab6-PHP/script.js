var previous ="";

function makeTable(array, div){
    results= "<table> <tr> <th> Document Id </th> <th> Title </th> <th> Author </th> <th> Number of Pages </th> <th> Type </th> <th> Format </th> </tr>";
    $.each(array, function(index, document){
        results+="<tr>";
        $.each(document, function(key, value){
            results+="<td>" + value + "</td>"; 
        });
        results+='<td><img class="paint" src="https://img.icons8.com/cotton/64/000000/paint-brush.png"/></td>';
        results+='<td><img class="xout" src="https://img.icons8.com/flat_round/64/000000/delete-sign.png"/></td>';
        results+="</tr>";
    });
    $("#"+div).html(results);        
}

function makeTableFiltered(array, div){
    results= "<table> <tr> <th> Document Id </th> <th> Title </th> <th> Author </th> <th> Number of Pages </th> <th> Type </th> <th> Format </th> </tr>";
    $.each(array, function(index, document){
        results+="<tr>";
        $.each(document, function(key, value){
            results+="<td>" + value + "</td>"; 
        });
        results+="<td></td><td></td>";
        results+="</tr>";
    });
    $("#"+div).html(results);        
}

function fetchData(){
    $.get("fetchData.php",function(data){
        makeTable(JSON.parse(data), 'mydata');
    });    
}

function performAdd(params){
    console.log(params);
    var parameters = {
        'author': params[0],
        'title': params[1],
        'numberPages': params[2],
        'type' : params[3],
        'format' : params[4]
    };
    console.log(parameters);
     $.post('addDocument.php',parameters).done(function(data){
             alert(data);
             fetchData();
     });
}

function performUpdate(params){
    console.log(params);
    var parameters = {
        'id': params[0],
        'author': params[1],
        'title': params[2],
        'numberPages': params[3],
        'type' : params[4],
        'format' : params[5]
    };
    console.log(parameters);
    $.post('updateDocument.php',parameters).done(function(data){
            alert(data);
            fetchData();
    });
}

$(document).ready(function(){

    $('#addbutton').on("click", function(e){
        e.preventDefault();
        window.open('addform.html', '_blank', 'width=200,height=350');
    });

    $(document).on('click', '.paint', function (event) {
        $id =  $(this).parent().parent().find("td:eq(0)").html();
        $title =  $(this).parent().parent().find("td:eq(1)").html();
        $author =  $(this).parent().parent().find("td:eq(2)").html();
        $numberPages =  $(this).parent().parent().find("td:eq(3)").html();
        $type =  $(this).parent().parent().find("td:eq(4)").html();
        $format =  $(this).parent().parent().find("td:eq(5)").html();
        var parsed = "?id=" + $id +"&title=" + $title + "&author="+ $author + "&numberPages=" + $numberPages + "&type="+ $type + "&format="+$format;
        window.open('updateform.html'+parsed , '_blank', 'width=200,height=400');
    });

    $(document).on('click', '.xout', function (event) {
        $id =  $(this).parent().parent().find("td:eq(0)").html();
        var deleteRequest = $.post('deleteDocument.php',{'id': $id});
        deleteRequest.done(function(data){
                    alert(data);
                    fetchData();
          });
    });

    $('#filterbutton').on('click', function (e){
        e.preventDefault();
        $type = $('#filterinput').val();
        $.get("filterData.php", {'type': $type}).done(function(data){
            makeTableFiltered(JSON.parse(data), 'filteredData');
        });   
        $currentType = $('#lastfilter').val();
        if ($currentType ===""){
            $('#lastfilter').val("No Previous Filter");
            $('#lastlabel').val('Last Filter On:')
            previous = $type;
        }
        else{
            $('#lastfilter').val(previous);
            previous = $type;
        }
    });
});