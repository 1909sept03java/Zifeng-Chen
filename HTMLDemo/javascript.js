window.onload = function () {
    // select element with id "fizzBuzzButton"
    // apply an event listener to it to make it clickable
    // define anonymous function to be invoked when it is clicked
    // this anonymous function is a CALLBACK FUNCTION - excecutes when another function 
    // has finished executing 
    let count = 0;
    document.getElementById("cadd").addEventListener("Click",function(){
        count++;
        if(count > 10)
        { 
            document.getElementById("cadd").removeEventListener("Click");
          
        }
        console.log(count);
    });
    /*
    document.getElementById("nfizzBuzzButton").addEventListener("click", function () {
         var num1 = document.getElementById("num1").value;
         var word1 = document.getElementById("word1").value;
        nfizzBuzzOrSimilar(num1, word1);
    });
*/

/*
    document.getElementById("fizzBuzzButton").addEventListener("click", function () {
        let num1 = document.getElementById("num1").value;
        let word1 = document.getElementById("word1").value;
        let num2 = document.getElementById("num2").value;
        let word2 = document.getElementById("word2").value;
        fizzBuzzOrSimilar(num1, word1, num2, word2);
    });

*/
}

function nfizzBuzzOrSimilar(num1, word1){
    var arrNum1 = num1.split(",");
    var arrWord1 = word1.split(",");
    var arrIntNum = arrNum1.map(Number);
    for( var i in arrIntNum)
      //  console.log(arrIntNum[i] );
    for(var j in arrWord1)
     //   console.log(arrWord1[j]);
    if( arrIntNum.length !== arrWord1.length){
        console.log("Length of 2 arrays are not equal");
    }
    else{
        for(a = 1; a < 100; a++){

            var array = [];

            for( var b in arrIntNum){
                if(a%arrIntNum[b]===0){
                    array.push(arrWord1[b]);
                }
            }

            if(array.length!==0)
                console.log(array.join(','));

            else
                console.log(a);
        }
        //do nfizzbuzz here
    }
}
/*
// THE ARGUMENT NAMES ARE ARBITRARY AND NOT AT ALL RELATED TO IDENTIFIERS IN THE ABOVE FUNCTION!
function fizzBuzzOrSimilar(num1, word1, num2, word2) {
    // this is template string - introduced in ES6
    console.log(`num1 is ${num1}, replaced by ${word1}, and num2 is ${num2}, replaced by ${word2}`);
    // console.log to print from 1-100 with all multiples of num1 replaced by word1, same for word2
    // all multiples of both replaced with word1word2
    for (i = 1; i < 100; i++) {
        if (i % num1 === 0 && i % num2 === 0) {
            console.log(word1 + word2 + ", ");
        }

        else if (i % num1 === 0) {
            console.log(word1 + ", ");
        }

        else if (i % num2 === 0) {
            console.log(word2 + ", ");
        }
        else
            console.log(i + ", ");
        
    }

}
*/