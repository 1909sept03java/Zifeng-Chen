
/*

    create a large div on the page
    use JS to add an event listener that will display the coordinates of the cursor position
    in a dynamically created box 
    bonus: 
    create box when mouse enters the div
    remove box when mouse leaves the div
*/

(window.onload = function() {
    document.getElementById('div').onmousemove = handleMouseMove;
    document.getElementById('div').onmouseleave = hideDiv;
    document.getElementById('div').onmouseenter = showDiv;
    function handleMouseMove(event) {
        document.getElementById('div').innerHTML = null;
        document.getElementById('div').innerHTML += event.pageX + ',' + event.pageY;
    }
    function hideDiv(){
        var x = document.getElementById("div");
        x.style.opacity = "0"; 
    }
    function showDiv(){
        var x = document.getElementById("div");
        x.style.opacity = "1"; 
    }


})();
