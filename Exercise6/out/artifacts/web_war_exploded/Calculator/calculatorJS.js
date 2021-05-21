function calcNumbers(result){
    if(result === "+" || result === "-" || result === "*" || result === "/") {
        form.displayResult.value = form.displayResult.value+" " +result + " ";
    }
    else
        form.displayResult.value = form.displayResult.value+result;
}

function calculatingResult(result) {
    fetch("http://localhost:8080/calculator?calculatingString="+ encodeURIComponent(result))
        .then(response => response.json())
        .then((data) =>{
            form.displayResult.value = data;
        })
}