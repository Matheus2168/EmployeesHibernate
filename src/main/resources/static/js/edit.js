
function validate () {
    var errorMessage = "";

    var firstName = document.getElementById("1").value;
    if (firstName.length < 3) {
       errorMessage+="\nIMIE ZBYT KROTKIE"
    }
    var lastName = document.getElementById("2").value;
    if (lastName.length < 3){
        errorMessage+="\nNAZWISKO ZBYT KROTKIE";
    }
    var pesel = document.getElementById("3").value;
    if (pesel.length != 11 || pesel.match(/[a-z]/i)){
        errorMessage+="\nBLEDNY FORMAT NUMERU PESEL"
    }
    var salary = document.getElementById("4").value;
    if(salary < 0){
        errorMessage+="\nWYNAGRODZENIE MUSI BYC DODATNIE"
    }





    if(errorMessage!=""){
        alert(errorMessage);
        return false;
    }
    else {
        return true;
    }

}
