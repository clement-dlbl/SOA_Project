var floor, serverResponse, responsestatus;

function ajouteLigne(tableID, type, value) {
  // Récupération d'une référence à la table
  var refTable = document.getElementById(tableID);

  // Insère une ligne dans la table à l'indice de ligne 0
  var nouvelleLigne = refTable.insertRow(0);

  // Insère une cellule dans la ligne à l'indice 0
  var cellule = nouvelleLigne.insertCell(0);
  var cellule1 = nouvelleLigne.insertCell(1);

  // Ajoute un nœud texte à la cellule
  var text = document.createTextNode(type);
  var text1 = document.createTextNode(value);
  //var nouveauTexte = document.createElement(value)
  cellule.appendChild(text);
  cellule1.appendChild(text1);
}


function manageMenuFloor(val){
    fieldNameElement = document.getElementById("informationFloor");
    while(fieldNameElement.childNodes.length >= 1) {
        fieldNameElement.removeChild(fieldNameElement.firstChild);
      }
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

function httpGetUC1(url)
{
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            serverResponse = JSON.parse(xmlhttp.response);
			for (var i in serverResponse) {
				this.responsestatus = serverResponse[i];
				document.createTextNode(this.responsestatus);
			}
        }
      };
      xmlhttp.open("GET", url, true);
      xmlhttp.send();
}




function getUC1(floorName, roomName)
{   
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        serverResponse = JSON.parse(xhttp.responseText);


        ajouteLigne("detailUC1", "Alarm", serverResponse.state);
        ajouteLigne("detailUC1", "Presence", serverResponse.presence);
        ajouteLigne("detailUC1", "Date", serverResponse.date);

      }
  };
  xhttp.open("GET", "http://localhost:8081/Use_Case_1/"+floorName+"/"+roomName, true);
  xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
  xhttp.send();
}


function getUC2(floorName, roomName)
{   
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        serverResponse = JSON.parse(xhttp.responseText);

        ajouteLigne("detailUC2", "Window", serverResponse.state);
        ajouteLigne("detailUC2", "Temperature INSIDE", serverResponse.tempIN);
        ajouteLigne("detailUC2", "Temperature OUTSIDE", serverResponse.tempOUT);

      }
  };
  xhttp.open("GET", "http://localhost:8081/Use_Case_2/"+floorName+"/"+roomName, true);
  xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
  xhttp.send();
}


function getHistoric()
{   
  var xhttp = new XMLHttpRequest();
      m = [];
  xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        console.log("test"+myArr.historic);
        for (var i in myArr.historic) {
          m[i] = "<tr>"+
          "<td>" + myArr.historic[i].date + " => </td>"+
          "<td>" + myArr.historic[i].source + "; </td"+
          "<td>" + myArr.historic[i].logType + "; </td"+
          "<td>" + myArr.historic[i].logValue + "</td"+
          "</tr>";
          console.log(m[i]);
          
        }
        document.getElementById("historic").innerHTML = m ;

      }
  };
  xhttp.open("GET", "http://localhost:8000/historic", true);
  xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
  xhttp.send();
}


function manageMenuRoom(val){
    fieldNameElement = document.getElementById("informationRoom");
    while(fieldNameElement.childNodes.length >= 1) {
        fieldNameElement.removeChild(fieldNameElement.firstChild);
      }
    var el = document.createElement("p");
            el.textContent = 'Room : ' + val;
    document.getElementById("informationRoom").appendChild(el);
    

    getHistoric();
    getUC1(this.floor, val);
    getUC2(this.floor, val);
     
}