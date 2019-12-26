var floor;
function manageMenuFloor(val){
    fieldNameElement = document.getElementById("informationFloor");
    while(fieldNameElement.childNodes.length >= 1) {
        fieldNameElement.removeChild(fieldNameElement.firstChild);
      }
    console.log(val);
    var select = document.getElementById("room-choice");
    select.options.length=0;
    if (val != "base"){
        var options = ["0", "1"];
        for(var i = 0; i < options.length; i++) {
            var opt = val*10+options[i];
            var el = document.createElement("option");
            el.textContent = opt;
            el.value = opt;
            select.appendChild(el);
        }
        var fl = document.createElement("p");
            fl.textContent = 'Floor : ' + val;
            document.getElementById("informationFloor").append(fl);
            
            this.floor = val;
    }
}
function httpGet(theUrl)
{
    var xhttp = new XMLHttpRequest();
    xhttp.open( "GET", theUrl, false ); // false for synchronous request
    xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    xhttp.send();
            
    if(this.readyState == 4 && this.status == 200) {
    serverResponse = JSON.parse(xhttp.responseText);
        for (var i in serverResponse) {
            this.responsestatus = serverResponse[i];
            console.log("In for " + this.responsestatus);
            document.createTextNode(this.responsestatus);
        }
    }
}

function manageMenuRoom(val){
    console.log(val);
    fieldNameElement = document.getElementById("informationRoom");
    while(fieldNameElement.childNodes.length >= 1) {
        fieldNameElement.removeChild(fieldNameElement.firstChild);
      }
    var el = document.createElement("p");
            el.textContent = 'Room : ' + val;
    document.getElementById("informationRoom").appendChild(el);
    //TODO make the function who get  usecase 1 result and print them 
    //same with usecase2
    //put in a table
    httpGet("http://localhost:8081/Use_Case_1/"+floor+"/"+val);

    
}