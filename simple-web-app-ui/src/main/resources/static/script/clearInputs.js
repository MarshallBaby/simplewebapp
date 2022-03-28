// Clear input fields while complete DB transaction or tab switching

function clearInputs(clearId = false){

    document.querySelectorAll("input").forEach((item, i) => {
        if(item.getAttribute('id') != 'add-employee-id' || clearId == true){
            item.value = "";
        }
    });

}