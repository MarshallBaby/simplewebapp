function switchHandler(statement){
    switchState = statement;
    clearInputs(true);
    document.querySelectorAll(".field").forEach((item, i) => {
        item.classList.add("display-none");
    });

    switch (statement) {
        case switchEnum.ADD:
        {
            confirmBtn.style.backgroundColor = "#A981FF";

            btnColorRemove();
            addBtn.classList.add("switch-add-active");

            document.querySelectorAll(".add-field").forEach((item, i) => {
                item.classList.remove("display-none");
            });
        }
            break;
        case switchEnum.EDIT:
        {
            confirmBtn.style.backgroundColor = "#F1CB00";

            btnColorRemove();
            editBtn.classList.add("switch-edit-active");

            document.querySelectorAll(".edit-field").forEach((item, i) => {
                item.classList.remove("display-none");
            });
        }
            break;
        case switchEnum.REMOVE:
        {
            confirmBtn.style.backgroundColor = "#EB5858";

            btnColorRemove();
            removeBtn.classList.add("switch-remove-active");

            document.querySelectorAll(".remove-field").forEach((item, i) => {
                item.classList.remove("display-none");
            });
        }
            break;
        case switchEnum.SEARCH:
        {
            confirmBtn.style.backgroundColor = "#1383D4";

            btnColorRemove();
            searchBtn.classList.add("switch-search-active");

            document.querySelectorAll(".search-field").forEach((item, i) => {
                item.classList.remove("display-none");
            });

            generateSearchTable();
        }
            break;
        default:

    }

    if(switchState == switchEnum.REMOVE){
        document.querySelectorAll(".field input").forEach((item) => {
            item.setAttribute("disabled", "disabled");
        });

        document.getElementById('add-employee-id').removeAttribute("disabled");
    }else{
        document.querySelectorAll(".field input").forEach((item) => {
            item.removeAttribute("disabled");
        });
    }

    if(switchState == switchEnum.SEARCH){
        document.getElementsByClassName('confirm-area')[0].classList.add('display-none');
    }else{
        document.getElementsByClassName('confirm-area')[0].classList.remove('display-none');


        try{
            document.querySelector('#search-table table').remove();
        }catch(err){
            // ...
        }

    }
}