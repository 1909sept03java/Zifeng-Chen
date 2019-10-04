var info;
window.onload = function () {

    this.fetch('http://localhost:8082/ReimbursementProject/session')
    .then ((resp) =>resp.json())
    .then(function (data){
        console.log(data);
        info = data;
    	var tt = document.getElementById("head");
        tt.append ("Welcome " + data[0].name);
        var x = document.getElementById("admin1");
        if (data[0].rank===1) {
          x.style.display = "block";
        } else {
          x.style.display = "none";
        }
       
        
    }

    )
    .catch(function (error)
    {
        console.log(error);
    }
    )



    
    
};


document.getElementById("empthenre").addEventListener("click",function(){
	fetch('http://localhost:8082/ReimbursementProject/process', {
	    method : "POST",
		   
	     body : JSON.stringify({
	         user : 'empthenre' ,
	         empid :info[0].id
	     })
	})
    .then ((resp) =>resp.json())
    .then(function (data){
        console.log(data[0]);
	     length = data[0].length;
	        var div = document.getElementById('display1');
	        div.innerHTML = '';
	        div.innerHTML = '<strong>Show All My Employee</strong>';
	        var tbl = document.createElement('table');
	        tbl.style.width = '100%';
	       // tbl.setAttribute('border', '1');
	        console.log(data)
	        var tbdy = document.createElement('tbody');
	        
	        for(var i = 0; i < length+1; i++){
	            var tr = tbl.insertRow();
	            for(var j = 0; j < 4; j++){
	                    var td = tr.insertCell();
	                    
	                    if(i==0 && j ==0)
	                        td.appendChild(document.createTextNode('Name'));
	                    else if (i==0 && j ==1)
	                        td.appendChild(document.createTextNode('Email'));
	                    else if(i==0 && j==2)
	                    	td.appendChild(document.createTextNode('ID'));
	                    else if(i==0 && j==3)
	                    	td.appendChild(document.createTextNode('Rank'));
	                    else if(i!= 0 &&j==0)
	                        td.appendChild(document.createTextNode(data[0][i-1].name));
	                    else if(i!= 0 &&j==1)
	                        td.appendChild(document.createTextNode(data[0][i-1].email));
	                    else if(i!= 0 &&j==2)
	                    	td.appendChild(document.createTextNode(data[0][i-1].id));
	                    else if(i!= 0 &&j==3)
	                    	td.appendChild(document.createTextNode(data[0][i-1].rank));
	                //    td.style.border = '1px solid black';
	                    div.append(tbl);
	            }
	        }

        
        div.innerHTML += ("<strong>Enter Employee name to see all reimbursements</strong><br>");;
        var i1 = document.createElement("input"); //input element, text
        i1.setAttribute('type',"text");
        i1.setAttribute('name',"userid");
        i1.setAttribute("id", "userid1");
        var s = document.createElement("input"); //input element, Submit button
        s.setAttribute("id", "useraccept");
        s.setAttribute('type',"button");
        s.setAttribute('value',"Submit");

        div.append(i1);
        div.append(s);
        
	    var second = document.createElement('div');
	    second.setAttribute('id',"second");
        div.append(second);
        
        document.getElementById("useraccept").addEventListener("click",function(){
         console.log("user heer");
         console.log(  document.getElementById("userid1").value);
         var zz =    document.getElementById("userid1").value;
         var div = document.getElementById('second');
         	div.innerHTML ="";
         
         	var length = data.length;
         	div.innerHTML = '';
	        var tbl = document.createElement('table');
	        tbl.style.width = '100%';
	        var tbdy = document.createElement('tbody');

	        
	        for(var i = 0; i < length; i++){
	            var tr = tbl.insertRow();
	            for(var j = 0; j < 3; j++){
	                    var td = tr.insertCell();
	                    
	                    if(i==0 && j ==0)
	                        td.appendChild(document.createTextNode('ID'));
	                    else if (i==0 && j ==1)
	                        td.appendChild(document.createTextNode('Money'));
	                    else if(i==0 && j==2)
	                    	td.appendChild(document.createTextNode('Owner'));  
	                    
	                    if(i!= 0 && zz===data[i].onwer){
	                    	console.log("here");
	                    	if(j==0)
	                    		td.appendChild(document.createTextNode(data[i].id));
	                    	else if(j==1)
	                    		td.appendChild(document.createTextNode(data[i].money));
	                    	else if(j==2)
	                    		td.appendChild(document.createTextNode(data[i].onwer));
	                    }
	            	}
	            
	                //    td.style.border = '1px solid black';
	                    div.append(tbl);
	            }
	        
         
         
         
        },false);
    }

    )
    .catch(function (error)
    {
        console.log(error);
    }
    )
	
	   
	
	
},false);

document.getElementById("resolved").addEventListener("click",function(){
	const url = "http://localhost:8082/ReimbursementProject/process";
	fetch(url, {
	    method : "POST",
	   
	     body : JSON.stringify({
	         user : 'resolved'
	     })
	})
	.then ((resp) =>resp.json())
	 .then(function (data){
	     length = data.length;
	        var div = document.getElementById('display1');
	        div.innerHTML = '';
	        div.innerHTML = '<strong>Show All Resolved Reimbursement</strong>';
	        var tbl = document.createElement('table');
	        tbl.style.width = '100%';
	       // tbl.setAttribute('border', '1');
	        console.log(data)
	        var tbdy = document.createElement('tbody');
	        
	        for(var i = 0; i < length+1; i++){
	            var tr = tbl.insertRow();
	            for(var j = 0; j < 3; j++){
	                    var td = tr.insertCell();
	                    
	                    if(i==0 && j ==0)
	                        td.appendChild(document.createTextNode('ID'));
	                    else if (i==0 && j ==1)
	                        td.appendChild(document.createTextNode('Money'));
	                    else if(i==0 && j==2)
	                    	td.appendChild(document.createTextNode('Owner'));
	                    else if(i!= 0 &&j==0)
	                        td.appendChild(document.createTextNode(data[i-1].id));
	                    else if(i!= 0 &&j==1)
	                        td.appendChild(document.createTextNode(data[i-1].money));
	                    else if(i!= 0 &&j==2)
	                    	td.appendChild(document.createTextNode(data[i-1].onwer));
	                //    td.style.border = '1px solid black';
	                    div.append(tbl);
	            }
	        }
	 }
	
	    )
},false);	

document.getElementById("empsee").addEventListener("click",function(){
	
	fetch('http://localhost:8082/ReimbursementProject/process', {
	    method : "POST",
		   
	     body : JSON.stringify({
	         user : 'emp'
	     })
	})
    .then ((resp) =>resp.json())
    .then(function (data){
        console.log(data);
        length = data.length;
        var div = document.getElementById('display1');
        div.innerHTML = '';
        div.innerHTML = '<strong>Show All Employees</strong>';
        var tbl = document.createElement('table');
        tbl.style.width = '100%';
       // tbl.setAttribute('border', '1');
        var tbdy = document.createElement('tbody');
        
        for(var i = 0; i < length; i++){
            var tr = tbl.insertRow();
            for(var j = 0; j < 4; j++){
                    var td = tr.insertCell();
                    
                    if(i==0 && j ==0)
                        td.appendChild(document.createTextNode('Name'));
                    else if (i==0 && j ==1)
                        td.appendChild(document.createTextNode('Email'));
                    else if(i==0 && j==2)
                        td.appendChild(document.createTextNode('Id'));
                    else if(i==0&& j==3)
                    	td.appendChild(document.createTextNode('Rank'));
                    else if(i!= 0 &&j==0)
                        td.appendChild(document.createTextNode(data[i].name));
                    else if(i!= 0 &&j==1)
                        td.appendChild(document.createTextNode(data[i].email));
                    else if(i!= 0 &&j==2)
                    	td.appendChild(document.createTextNode(data[i].id));
                    else if(i!= 0 &&j==3)
                    	 td.appendChild(document.createTextNode(data[i].rank));
                //    td.style.border = '1px solid black';
                    div.append(tbl);
            }
        }
    }

    )
    .catch(function (error)
    {
        console.log(error);
    }
    )
},false);	




document.getElementById("managesee").addEventListener("click",function(){
	
	console.log("here6");
    console.log(info);
    
    var div = document.getElementById('display1');
    div.innerHTML = '';
    div.innerHTML = '<strong>Accept your Employees Pending Reimbursements</strong>';
    var tbl = document.createElement('table');
    tbl.style.width = '100%';
   // tbl.setAttribute('border', '1');
    var tbdy = document.createElement('tbody');
    
    var length = info.length;
    var stop = 1;
    for(var k = 1; k<length;k++){
    	if (info[0].name == info[k].onwer){
 
    		stop++;
    	}
    }
    

    console.log(info);
    for(var i = 0; i < length-stop+1; i++){
        var tr = tbl.insertRow();
        for(var j = 0; j < 3; j++){
                var td = tr.insertCell();
                
                if(i==0 && j ==0)
                    td.appendChild(document.createTextNode('ID'));
                else if (i==0 && j ==1)
                    td.appendChild(document.createTextNode('Money'));
                else if(i==0&& j==2)
                	td.appendChild(document.createTextNode('Owner'));
                
                if(i!= 0 && info[i+stop-1].status==false){
                	if(i!= 0 &&j==0)
                		td.appendChild(document.createTextNode(info[i+stop-1].id));
                	else if(i!= 0 &&j==1)
                		td.appendChild(document.createTextNode(info[i+stop-1].money));  
                	else if(i!= 0 &&j==2)
                		td.appendChild(document.createTextNode(info[i+stop-1].onwer));
                	}
            //    td.style.border = '1px solid black';
        	}
        }
   div.appendChild(tbl);
    div.innerHTML += ("<strong>Enter reimbursement id to accept request</strong><br>");
    var f = document.createElement("form");
    f.setAttribute('method',"post");
    f.setAttribute('action',"profile");
    var i1 = document.createElement("input"); //input element, text
    i1.setAttribute('type',"number");
    i1.setAttribute('name',"id");
    i1.setAttribute("id", "in");
    var s = document.createElement("input"); //input element, Submit button
    s.setAttribute("id", "accept");
    s.setAttribute('type',"submit");
    s.setAttribute('value',"Submit");

    f.append(i1);
    f.append(s);
    div.append(f);

},false);

document.getElementById("out").addEventListener("click",function(){
	console.log("here5");
    window.location.href = "login";
},false);

document.getElementById("pass").addEventListener("click",function(){
	console.log("here4");
    var div = document.getElementById('display1');
    div.innerHTML = ''; 
    div.innerHTML = ("<strong>Changing Passwords</strong>");
    var f = document.createElement("form");
    f.setAttribute('method',"post");
    f.setAttribute('action',"profile");
    
    var i1 = document.createElement("input"); //input element, text
    i1.setAttribute('type',"text");
    i1.setAttribute('name',"pass");

    var s = document.createElement("input"); //input element, Submit button
    s.setAttribute('type',"submit");
    s.setAttribute('value',"Submit");
    
    f.appendChild(i1);
    f.appendChild(s);

    div.append(f);
   //ddd 
},false);


document.getElementById("info").addEventListener("click",function(){
	console.log("here3");
    var div = document.getElementById('display1');
    div.innerHTML = ''; 
    div.innerHTML = ("<strong>Here is your personal information</strong>");
    div.innerHTML +=("<br>Name: " + info[0].name);
    div.innerHTML +=("<br>Email: " + info[0].email);
    div.innerHTML +=("<br>Password: " + info[0].password);
    div.innerHTML +=("<br>Employee Identification Number: " + info[0].id);
   //ddd 
},false);


document.getElementById("submit").addEventListener("click",function(){
	console.log("here2");
    var div = document.getElementById('display1');
    div.innerHTML = ''; 
    div.innerHTML = ("<strong>Please Enter the Value of Reimbursement</strong>");
    var f = document.createElement("form");
    f.setAttribute('method',"post");
    f.setAttribute('action',"profile");
    
    var i1 = document.createElement("input"); //input element, text
    i1.setAttribute('type',"number");
    i1.setAttribute('name',"money");
    i1.setAttribute('step',".01");
    i1.setAttribute('value',"420.69");
    var s = document.createElement("input"); //input element, Submit button
    s.setAttribute('type',"submit");
    s.setAttribute('value',"Submit");
    
    f.appendChild(i1);
    f.appendChild(s);

    div.append(f);

},false);

document.getElementById("account").addEventListener("click",function(){
	
	console.log("here");
    console.log(info);
    
    var div = document.getElementById('display1');
    div.innerHTML = '';
    div.innerHTML = '<strong>Show All My Reimbursements</strong>';
    var tbl = document.createElement('table');
    tbl.style.width = '100%';
   // tbl.setAttribute('border', '1');
    var tbdy = document.createElement('tbody');
    
    var length = info.length;
    var stop = 1;
    for(var k = 1; k<length;k++){
   		console.log(info[0].name);
		console.log( info[k].onwer);
    	if (info[0].name == info[k].onwer){
 
    		stop++;
    	}
    }
    
    console.log(stop);
    
    for(var i = 0; i < stop; i++){
        var tr = tbl.insertRow();
        for(var j = 0; j < 4; j++){
                var td = tr.insertCell();
                
                if(i==0 && j ==0)
                    td.appendChild(document.createTextNode('ID'));
                else if (i==0 && j ==1)
                    td.appendChild(document.createTextNode('Money'));
                else if(i==0 && j==2)
                    td.appendChild(document.createTextNode('Status'));
                else if(i==0&& j==3)
                	td.appendChild(document.createTextNode('Owner'));
                else if(i!= 0 &&j==0)
                    td.appendChild(document.createTextNode(info[i].id));
                else if(i!= 0 &&j==1)
                    td.appendChild(document.createTextNode(info[i].money));
                else if(i!= 0 &&j==2 && !info[i].status )
                    td.appendChild(document.createTextNode('Not Processed')); 
                else if(i!= 0 &&j==2 && info[i].status )
                    td.appendChild(document.createTextNode('Processed')); 
                else if(i!= 0 &&j==3)
                	 td.appendChild(document.createTextNode(info[i].onwer));
            //    td.style.border = '1px solid black';

        }
    }
    

    
    
    div.appendChild(tbl);
    
    
    
    
    
},false);
function newFunction() {
    return "http://stackoverflow.com";
}

