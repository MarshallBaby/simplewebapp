const DB_PATH = "http://127.0.0.1:8080/api/employee/";

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
let confirmBtn = document.getElementById('confirm-button');
let confirmMsg = document.getElementById('confirm-message');
let idField = document.getElementById('add-employee-id');


switchHandler(switchEnum.ADD);

function sendRequest(){
    switch (switchState) {
      case switchEnum.ADD:
            processSaveRequest();
        break;
      case switchEnum.EDIT:
            processEditRequest();
        break;
      case switchEnum.REMOVE:
            processRemoveRequest();
        break;
      default:

    }

}

let globalTimeout = null;
idField.addEventListener('keyup', processAutoSearchRequest)
