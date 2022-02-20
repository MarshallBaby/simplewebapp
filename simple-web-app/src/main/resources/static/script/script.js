const switchEnum = Object.freeze({
  ADD: 0,
  EDIT: 1,
  REMOVE: 2,
  SEARCH: 3,
});

let switchState = switchEnum.ADD;

let addBtn = document.getElementById('addBtn');
let editBtn = document.getElementById('editBtn');
let removeBtn = document.getElementById('removeBtn');
let searchBtn = document.getElementById('searchBtn');

let confimBtn = document.getElementById('confirm-button');
let confirmIcon = document.getElementById('confirm-icon');
let confirmMsg = document.getElementById('confirm-message');

let idField = document.getElementById('add-employee-id');

function clearInputs(clearId = false){

    document.querySelectorAll("input").forEach((item, i) => {
      if(item.getAttribute('id') != 'add-employee-id' || clearId == true){
          item.value = "";
      }
    });

}


function processAjaxReply(currentCode, expectedCode, xhr){
  clearInputs();

  confirmMsg.classList.remove("display-none")

  if(currentCode === expectedCode){
    confirmMsg.textContent = "OK";
    confirmMsg.style.color = "#128300";
  }else if(currentCode === 0){
    confirmMsg.textContent = "Connection error";
    confirmMsg.style.color = "#F11D00";
  }else{
    confirmMsg.textContent = xhr.statusText;
    confirmMsg.style.color = "#F1CB00";
  }
}

function btnColorRemove(){
  addBtn.classList.remove("switch-add-active");
  editBtn.classList.remove("switch-edit-active");
  removeBtn.classList.remove("switch-remove-active");
  searchBtn.classList.remove("switch-search-active");
}

function switchHandler(statement){
  switchState = statement;
  clearInputs(true);
  document.querySelectorAll(".field").forEach((item, i) => {
    item.classList.add("display-none");
  });

  switch (statement) {
    case switchEnum.ADD:
        {
            confimBtn.style.backgroundColor = "#A981FF";

            btnColorRemove();
            addBtn.classList.add("switch-add-active");

            document.querySelectorAll(".add-field").forEach((item, i) => {
              item.classList.remove("display-none");
            });
        }
      break;
      case switchEnum.EDIT:
          {
            confimBtn.style.backgroundColor = "#F1CB00";

            btnColorRemove();
            editBtn.classList.add("switch-edit-active");

            document.querySelectorAll(".edit-field").forEach((item, i) => {
              item.classList.remove("display-none");
            });
          }
        break;
        case switchEnum.REMOVE:
            {

            }
          break;
          case switchEnum.SEARCH:
              {

              }
            break;
    default:

  }
}


switchHandler(switchEnum.ADD);


function sendRequest(){
    confirmIcon.style.display = "flex";
    switch (switchState) {
      case switchEnum.ADD:
            processSaveRequest();
        break;
      case switchEnum.EDIT:
            processEditRequest();
      default:

    }


    confirmIcon.style.display = "none";
}

function processSaveRequest(){
    let formData = {
        "firstName" : document.getElementById('add-first-name').value,
        "lastName" : document.getElementById('add-last-name').value,
        "departmentId" : document.getElementById('add-dep-id').value,
        "jobTitle" : document.getElementById('add-job-tag').value,
        "gender" : document.getElementById('add-gender').value
    }

    $.ajax({
        "url": "http://192.168.1.120:8080/api/employee",
        "method": "POST",
        "data": JSON.stringify(formData),
        "headers": {
            "Content-Type": "application/json"
        },
        "complete": function(xhr, textStatus) {

        clearInputs();

        confirmMsg.classList.remove("display-none");

        if(xhr.status === 201){
          confirmMsg.textContent = "OK";
          confirmMsg.style.color = "#128300";
        }else if(xhr.status === 0){
          confirmMsg.textContent = "Connection error";
          confirmMsg.style.color = "#F11D00";
        }else{
          confirmMsg.textContent = xhr.statusText;
          confirmMsg.style.color = "#F1CB00";
        }

        setTimeout(function(){
          confirmMsg.classList.add("display-none");
        }, 3000)
    }
      });


}

function processEditRequest(){
  if(idField.value == ""){
    clearInputs(true);
    idField.classList.add('border-red');

    setTimeout(function() {
      idField.classList.remove('border-red');
    }, 1000)

    return;

  }

  let formData = {
      "firstName" : document.getElementById('add-first-name').value,
      "lastName" : document.getElementById('add-last-name').value,
      "departmentId" : document.getElementById('add-dep-id').value,
      "jobTitle" : document.getElementById('add-job-tag').value,
      "gender" : document.getElementById('add-gender').value
  }

  $.ajax({
    "url": "http://192.168.1.120:8080/api/employee/" + idField.value,
    "method": "PUT",
    "data": JSON.stringify(formData),
    "headers": {
        "Content-Type": "application/json"
    },
    "complete": function(xhr, textStatus) {

    clearInputs(true);

    console.log(xhr);

    confirmMsg.classList.remove("display-none");

    if(xhr.status === 200){
      confirmMsg.textContent = "OK";
      confirmMsg.style.color = "#128300";
    }else if(xhr.status === 0){
      confirmMsg.textContent = "Connection error";
      confirmMsg.style.color = "#F11D00";
    }else{
      confirmMsg.textContent = xhr.statusText;
      confirmMsg.style.color = "#F1CB00";
    }

    setTimeout(function(){
      confirmMsg.classList.add("display-none");
    }, 3000)
}
  })
}

let globalTimeout = null;

function processEditSearchRequest(){
    let searchValue = idField.value;

    if(globalTimeout != null){
      clearTimeout(globalTimeout);
    }

    globalTimeout = setTimeout(
      function() {

        if(idField.value == ""){
          return;
        }

        $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://192.168.1.120:8080/api/employee/" + idField.value,
        complete: function(data) {
            if(data.status != 200){
              clearInputs();
              return;
            }

            let result = data.responseJSON;

            document.getElementById('add-first-name').value = result.firstName;
            document.getElementById('add-last-name').value = result.lastName;
            document.getElementById('add-dep-id').value = result.departmentId;
            document.getElementById('add-job-tag').value = result.jobTitle;
            document.getElementById('add-gender').value = result.gender;




        }
    });

      }
    , 700)
}


idField.addEventListener('keyup', processEditSearchRequest)
