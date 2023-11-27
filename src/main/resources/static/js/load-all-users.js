let usersManagementBtn = document.getElementById('user-management-btn');
// let usersBlacklistedBtn = document.getElementById('user-black-list-btn');
// let usersUserRoleBtn = document.getElementById('user-userRole-btn');
// let usersModeratorRoleBtn = document.getElementById('user-moderatorRole-btn');
// let usersAminRoleBtn = document.getElementById('user-adminRole-btn');

let baseUrl = "http://localhost:8080/api/users";


usersManagementBtn.addEventListener('click', loadAllUsers(baseUrl));
// usersBlacklistedBtn.addEventListener('click', loadAllUsers(baseUrl + "/blacklisted"));
// usersUserRoleBtn.addEventListener('click', loadAllUsers(baseUrl + "/users"));
// usersModeratorRoleBtn.addEventListener('click', loadAllUsers(baseUrl + "/moderators"));
// usersAminRoleBtn.addEventListener('click', loadAllUsers(baseUrl + "/admins"));

function loadAllUsers(url) {
    let usersContainer = document.getElementById('users-container');
    usersContainer.innerHTML = '';

    fetch(url)
        .then(response => response.json())
        .then(json =>
            json.forEach(user => {

                let userRow = document.createElement('tr');

                let userId = document.createElement('td');
                let userUsername = document.createElement('td');
                let userEmail = document.createElement('td');
                let userRole = document.createElement('td');
                let userLevel = document.createElement('td');
                let action = document.createElement('td');
                action.classList.add("d-flex", "justify-content-between", "align-items-center");

                userId.textContent = user.id;
                userUsername.textContent = user.username;
                userEmail.textContent = user.email;
                userRole.textContent = user.role.name;
                userLevel.textContent = user.level;

                let blacklistedBtn = document.createElement('button');
                let makeMeUserBtn = document.createElement('button');
                let makeMeModeratorBtn = document.createElement('button');
                let makeMeAdminBtn = document.createElement('button');

                blacklistedBtn.textContent = 'BLACKLISTED';
                blacklistedBtn.classList.add("btn", "btn-danger", "btn-sm", "mr-2");

                makeMeUserBtn.textContent = 'Make me USER';
                makeMeUserBtn.classList.add("btn", "btn-warning", "btn-sm")

                makeMeModeratorBtn.textContent = 'Make me MODERATOR';
                makeMeModeratorBtn.classList.add("btn", "btn-primary", "btn-sm")

                makeMeAdminBtn.textContent = 'Make me ADMIN';
                makeMeAdminBtn.classList.add("btn", "btn-danger", "btn-sm")

                blacklistedBtn.dataset.id = user.id;
                blacklistedBtn.dataset.role = 'BLACKLISTED';

                makeMeUserBtn.dataset.id = user.id;
                makeMeUserBtn.dataset.role = 'USER';

                makeMeModeratorBtn.dataset.id = user.id;
                makeMeModeratorBtn.dataset.role = 'MODERATOR';

                makeMeAdminBtn.dataset.id = user.id;
                makeMeAdminBtn.dataset.role = 'ADMIN';

                blacklistedBtn.addEventListener('click', changeMyRole)
                makeMeUserBtn.addEventListener('click', changeMyRole);
                makeMeModeratorBtn.addEventListener('click', changeMyRole);
                makeMeAdminBtn.addEventListener('click', changeMyRole);

                action.appendChild(blacklistedBtn);
                action.appendChild(makeMeUserBtn);
                action.appendChild(makeMeModeratorBtn);
                action.appendChild(makeMeAdminBtn);

                userRow.appendChild(userId);
                userRow.appendChild(userUsername);
                userRow.appendChild(userEmail);
                userRow.appendChild(userRole);
                userRow.appendChild(userLevel);
                userRow.appendChild(action);

                usersContainer.append(userRow);

            }))

    function changeMyRole(event) {
        let userId = event.target.dataset.id;
        let userRole = event.target.dataset.role;
        let requestOption = {
            method: 'PATCH',
            body: userRole
        }

        fetch(`http://localhost:8080/api/users/${userId}`, requestOption)
            .then(_ => loadAllUsers())
            .catch(error => console.log(error))
    }

    // function editButtonClicked(event){
    //     let trContent = event.target.parentNode.parentNode.childNodes[1];
    //     let inputField = document.createElement('input');
    //
    //     console.log(trContent);
    //
    //
    //     trContent.textContent = event.target.dataset.textContent;
    //     let questionId = event.target.dataset.id;
    //     let requestOption = {
    //         method: 'PUT'
    //     }
    //     fetch(`http://localhost:8080/api/questions/${questionId}`,requestOption)
    //         .then(response => response.json())
    //         .then(json => console.log(json))
    //         .catch(err => console.log(err))
    // }
}
