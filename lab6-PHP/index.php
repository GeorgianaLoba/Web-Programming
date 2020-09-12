<!DOCTYPE html>
<html>
    <head>
        <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
        <meta content="utf-8" http-equiv="encoding">

        <script src="jquery-2.0.3.js"></script>
        <script src="script.js"></script>
        <link rel="stylesheet" href="style.css">
        <title> Documents </title>
    
    </head>
    <body onload="fetchData()">
        <section id="add">
            <input type="button" value="Add Document" id="addbutton">
        </section>
        <section id="data">
            <div id="mydata">
            </div>
        </section>
        <section id="filter">
            <div>
            <form id="filterform">
                <input type="button" value="Type Filter " id="filterbutton">
                <input type="text" placeholder="Enter Type Here" id="filterinput">
                <input type="button" value="" id="lastlabel"> 
                <input type="button" value="" id="lastfilter"> 
            </form>
            </div>
            <div id="filteredData"> </div>
        </section>
    </body>
</html>