var selectedRow = null;
var previous = "";

function fetchData() {
    $.get("/Documents/FetchData",
            function(data, status) {
                var json = jsonParse(data);
                var results =
                    "<table> <tr> <th> Document Id </th> <th> Title </th> <th> Author </th> <th> Number of Pages </th> <th> Format </th> <th> Type </th> </tr>";
                for (let i = 0; i < json.length; i++) {
                    var mydoc = json[i];
                    results += "<tr>";
                    results += "<td>" + mydoc["Id"] + "</td>";
                    results += "<td>" + mydoc["Title"] + "</td>";
                    results += "<td>" + mydoc["Author"] + "</td>";
                    results += "<td>" + mydoc["NumberPages"] + "</td>";
                    results += "<td>" + mydoc["Format"] + "</td>";
                    results += "<td>" + mydoc["Type"] + "</td>";
                    results += "</tr>";
                }
                $("#mydata").html(results);
            });
}

$(document).ready(function() {
    selectedRow = null;
    var previous = "";

    $("#addform").hide();
    $("#updateform").hide();

    $(document).on('click', '#deletebutton', deleteDocument);
    $(document).on('click', '#updatebutton', preUpdateDocument);
    $(document).on('click', '#added', addDocument);
    $(document).on('click', '#updated', updateDocument);

    $(document).on('click', 'tr',
        function () {
            mark(this.rowIndex);
        });

    $(document).on('click', '#addbutton', function() {
        $("#addform").show();
    });

    $("#signout").on('click',
        function() {
            if (confirm("Are you sure you want to log out?")) location.href = "Login";
        });

    $("#filterbutton").on('click', function (e) {
        e.preventDefault();
        var type = $("#filterinput").val();
        $.get("/Documents/FetchFiltered",
            { type: type }).done(function (data) {
                fetchFiltered(data, status);
        });
        var currentType = $('#lastfilter').val();
        if (currentType === "") {
            $('#lastfilter').val("No Previous Filter");
            $('#lastlabel').val('Last Filter On:');
            previous = type;
        }
        else {
            $('#lastfilter').val(previous);
            previous = type;
        }
    });
});
function jsonParse(text) {
    var json;
    try {
        json = JSON.parse(text);
    } catch (e) {
        return false;
    }
    return json;
}

function mark(row) {
    if (selectedRow !== null) {
        var beforeFor = 'table tr:eq(' + selectedRow.toString() + ')';
        $(beforeFor).css('background-color', 'initial');
    }
    selectedRow = row;
    var lookFor = 'table tr:eq(' + selectedRow.toString() + ')';
    $(lookFor).css('background-color', 'red');
}

function deleteDocument() {
    var text = "tr:eq(" + selectedRow + ")";
    var row = $(text)[0];
    var idDel = row.firstChild.textContent;
    if (selectedRow != null) {
        if (confirm("Are you sure you want to delete selected document?")) {
            $.post("/Documents/DeleteData",
                { id: idDel },
                function() {
                    alert("Successfully deleted document...");
                    fetchData();
                }
            );
        }

    } else {
        alert('No document was selected...');
    }
}

function preUpdateDocument() {
    if (selectedRow != null) {
        var text = "tr:eq(" + selectedRow + ")";
            var row = $(text)[0];
            var id = parseInt(row.childNodes[0].textContent);
            var title = row.childNodes[1].textContent;
            var author = row.childNodes[2].textContent;
            var numberPages = parseInt(row.childNodes[3].textContent);
            var type = row.childNodes[5].textContent;
            var format = row.childNodes[4].textContent;
            $("#titleU").attr("value", title);
            $("#authorU").attr("value", author);
            $("#docId").attr("value", id);
            $("#docId").attr("readonly", "readonly");
            $("#numberPagesU").attr("value", numberPages);
            $("#typeU").attr("value", type);
            $("#" + format + "U").prop("checked", true);
            $("#updateform").show();
    } else {
        alert('No document was selected...');
    }
}

function addDocument() {
    var formats = document.getElementsByName('format');
    var myFormat = null;
    var length = formats.length;
    for (var i = 0; i < length; i++) {
        if (formats[i].checked) {
            myFormat = formats[i].value;
            break;
        }
    }
    var params = {
            author: $('#author').val(),
            title: $('#title').val(),
            numberPages: $('#numberPages').val(),
            type: $('#type').val(),
            format: myFormat
    }
    var errors = validate(params);
    if (errors.length > 0) {
        alert(errors);
    }
    else
    {
        $.post("/Documents/AddData",
            params,
            function() {
                alert("Successfully added document...");
                fetchData();
                $("#addform").hide();
                $("#addform").find("input[type=text], textarea").val("");
            });
    }
}

function updateDocument() {
    if (confirm("Are you sure you want to update selected document?")) {
        var formats = document.getElementsByName('formatU');
        var myFormat = null;
        var length = formats.length;
        for (var i = 0; i < length; i++) {
            if (formats[i].checked) {
                myFormat = formats[i].value;
                break;
            }
        }

        var params = {
            id: $("#docId").val(),
            author: $('#authorU').val(),
            title: $('#titleU').val(),
            numberPages: $('#numberPagesU').val(),
            type: $('#typeU').val(),
            format: myFormat
        }
        var errors = validate(params);
        if (errors.length > 0) {
            alert(errors);
        } else {
            $.post("/Documents/UpdateData",
                params,
                function() {
                    alert("Successfully updated document...");
                    fetchData();
                    $("#updateform").hide();
                });
        }
    } else {
        $("#updateform").hide();
    }
}

function fetchFiltered(data) {
    var json = jsonParse(data);
    var row = null;
    var results = "<table> <tr> <th> Document Id </th> <th> Title </th> <th> Author </th> <th> Number of Pages </th> <th> Format </th> <th> Type </th> </tr>";
    for (let i = 0; i < json.length; i++) {
        var mydoc = json[i];
        results += "<tr>";
        results += "<td>" + mydoc["Id"] + "</td>";
        results += "<td>" + mydoc["Title"] + "</td>";
        results += "<td>" + mydoc["Author"] + "</td>";
        results += "<td>" + mydoc["NumberPages"] + "</td>";
        results += "<td>" + mydoc["Format"] + "</td>";
        results += "<td>" + mydoc["Type"] + "</td>";
        results += "</tr>";
    }
    $("#filteredData").html(results);
}


function validate(params) {
    if (params['title'].length === '') return "title can't be null";
    if (params['author'].length < 5) return "author name can't be null";
    if (params['type'].length === '') return "type can't be null";
    if (params['numberPages'] < 0) return "number of pages can't be negative";
    return "";
}