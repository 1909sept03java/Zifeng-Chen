window.onload = function () {
    this.fetch('http://localhost:8082/ServletTest/session')
    .then ((resp) =>resp.json())
    .then(function (data){
        console.log(data);
        var tt = document.getElementById("div1");
        tt.innerHTML = ("Welcome " + data.firstname + " " + data.lastname );
        
    }

    )
    .catch(function (error)
    {
        console.log(error);
    }
    )
};
