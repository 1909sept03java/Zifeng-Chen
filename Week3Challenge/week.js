
window.onload = function () {
    console.log('here2');
    document.getElementById("b1").addEventListener("click",function(){
       $.ajax({
        url: 'https://randomuser.me/api/?results=25&nat=us',
        dataType: 'json',
        success: function(data) {

          console.log(data.results);
        
          var tab = document.getElementById("usertable");
          var x = tab.rows.length;
        if( x > 1 ){
            for ( y = x-1;y > 0;y--){
                tab.deleteRow(y);
            }
        }

        for ( i = 0; i < 25 ; i ++){

            var row = tab.insertRow(i+1);
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);
            var cell3 = row.insertCell(3);
            var cell4 = row.insertCell(4);
            var ele = document.createElement('div');

          
            var img = document.createElement('img');
            img.src =data.results[i].picture.thumbnail;
            cell0.append(img);

            cell1.innerHTML =  (data.results[i].name.last + ", " + data.results[i].name.first );
            cell2.innerHTML = (data.results[i].email);
            cell3.innerHTML = (data.results[i].location.street +"<br>"+ data.results[i].location.city + " , " + data.results[i].location.state);
            cell4.innerHTML = (data.results[i].dob.age);
            // console.log(data.results[i].picture.thumbnail);
            }
  


        }
      });
    },false);
};
