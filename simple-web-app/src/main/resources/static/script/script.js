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

let pages = [
    document.getElementsByClassName('add-form')[0],
    document.getElementsByClassName('edit-form')[0],
    document.getElementsByClassName('remove-form')[0],
    document.getElementsByClassName('search-form')[0],
]

function switchPages(value){
    for(let i = 0; i < pages.length; i++){
      if(i == value){
        pages[i].style.display = "flex";
      }else{
        pages[i].style.display = "none";
      }
    }

    document.querySelectorAll("input").forEach((item, i) => {
      item.value = "";
    });

}

function switchHandler(statement){
  switch (statement) {
    case switchEnum.ADD:
        {
          switchState = switchEnum.ADD;
          addBtn.classList.add('switch-add-active');

          editBtn.classList.remove('switch-edit-active');
          removeBtn.classList.remove('switch-remove-active');
          searchBtn.classList.remove('switch-search-active');

          confimBtn.style.backgroundColor = "#A981FF";
        }
      break;
      case switchEnum.EDIT:
          {
            switchState = switchEnum.EDIT;
            editBtn.classList.add('switch-edit-active');

            addBtn.classList.remove('switch-add-active');
            removeBtn.classList.remove('switch-remove-active');
            searchBtn.classList.remove('switch-search-active');
          }
        break;
        case switchEnum.REMOVE:
            {
              switchState = switchEnum.REMOVE;
              removeBtn.classList.add('switch-remove-active');

              editBtn.classList.remove('switch-edit-active');
              addBtn.classList.remove('switch-add-active');
              searchBtn.classList.remove('switch-search-active');
            }
          break;
          case switchEnum.SEARCH:
              {
                switchState = switchEnum.SEARCH;
                searchBtn.classList.add('switch-search-active');

                editBtn.classList.remove('switch-edit-active');
                removeBtn.classList.remove('switch-remove-active');
                addBtn.classList.remove('switch-add-active');

              }
            break;
    default:

  }

  switchPages(statement);
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

    let settings = {
        "url": "http://192.168.1.120:8080/api/employee",
        "method": "POST",
        "data": JSON.stringify(formData),
        "headers": {
            "Content-Type": "application/json"
        }
    }

    $.ajax(settings).done(function (response) {
        confirmMsg.textContent = response;
        switchPages(switchState);
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
