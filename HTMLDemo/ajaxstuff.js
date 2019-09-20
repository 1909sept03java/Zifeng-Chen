window.onload = function () {
    console.log('here2');
    document.getElementById("b1").addEventListener("click",function(){
        var https = new XMLHttpRequest();
        console.log('here');
        https.open("Get",  'https://api.thecatapi.com/v1/images/search', true);
        https.send();
        https.onload = function() {
            var u = https.responseText;
            let obj = JSON.parse(u);
            console.log(obj[0].url);
           img = document.createElement('img');
            img.src =obj[0].url
            document.body.append(img);

        };
        
    },false);
};
