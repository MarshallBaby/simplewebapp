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

function switchHandler(statement){
  switch (statement) {
    case switchEnum.ADD:
        {
          switchState = switchEnum.ADD;
          addBtn.classList.add('switch-add-active');

          editBtn.classList.remove('switch-edit-active');
          removeBtn.classList.remove('switch-remove-active');
          searchBtn.classList.remove('switch-search-active');
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
                switchState = switchEnum.REMOVE;
                searchBtn.classList.add('switch-search-active');

                editBtn.classList.remove('switch-edit-active');
                removeBtn.classList.remove('switch-remove-active');
                addBtn.classList.remove('switch-add-active');
              }
            break;
    default:

  }
}


switchHandler(switchEnum.ADD);


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
