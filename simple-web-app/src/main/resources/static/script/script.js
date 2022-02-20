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

function clearInputs(value){

    document.querySelectorAll("input").forEach((item, i) => {
      item.value = "";
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

function switchHandler(statement){
  switchState = statement;
  clearInputs();
  document.querySelectorAll(".field").forEach((item, i) => {
    item.classList.add("display-none");
  });

  switch (statement) {
    case switchEnum.ADD:
        {
            confimBtn.style.backgroundColor = "#A981FF";


            document.querySelectorAll(".add-field").forEach((item, i) => {
              item.classList.remove("display-none");
            });
        }
      break;
      case switchEnum.EDIT:
          {
            confimBtn.style.backgroundColor = "#F1CB00";


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
        "gender" : "MALE"
    }

    $.ajax({
        "url": "http://192.168.1.120:8080/api/employee",
        "method": "POST",
        "data": JSON.stringify(formData),
        "headers": {
            "Content-Type": "application/json"
        },
        "complete": function(xhr, textStatus) {
        console.log(xhr);

        clearInputs();

        confirmMsg.classList.remove("display-none")

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
    }
      });


}


formData = {
    "firstName" : "test jquery",
    "lastName" : "pliz rabotay",
    "departmentId" : 27,
    "jobTitle" : "JS DEV",
    "gender" : "MALE"
}

var settings = {
    "url": "http://192.168.1.120:8080/api/employee",
    "method": "POST",
    "data": JSON.stringify(formData),
    "headers": {
        "Content-Type": "application/json"
    }
}

// $.ajax(settings).done(function (response) {
//     console.log(response);
// });
