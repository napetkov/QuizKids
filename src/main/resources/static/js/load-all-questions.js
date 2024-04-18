let questionManagerBtn = document.getElementById("question-manager-btn");

questionManagerBtn.addEventListener("click", reloadAllQuestions);

function reloadAllQuestions() {
    let questionContainer = document.getElementById('question-container');
    questionContainer.innerHTML = '';

    fetch("http://localhost:8080/api/questions")
        .then(response => response.json())
        .then(json =>
            json.forEach(question => {

                let questionRow = document.createElement('tr');

                let idQuestion = document.createElement('td');
                let contentQuestion = document.createElement('td');
                let category = document.createElement('td');
                let createdOn = document.createElement('td');
                let author = document.createElement('td');
                let action = document.createElement('td');

                idQuestion.textContent = question.id;
                contentQuestion.textContent = question.content;
                createdOn.textContent = question.createdOn;
                author.textContent = question.authorUsername;
                category.textContent = question.categoryName;

                let deleteButton = document.createElement('button');
                let editButton = document.createElement('button');

                deleteButton.textContent = 'DELETE';
                deleteButton.classList.add("btn","btn-danger", "btn-sm", "mr-2");

                deleteButton.dataset.id = question.id;
                deleteButton.addEventListener('click', deleteBtnClicked)

                action.appendChild(deleteButton);

                questionRow.appendChild(idQuestion);
                questionRow.appendChild(contentQuestion);
                questionRow.appendChild(category);
                questionRow.appendChild(createdOn);
                questionRow.appendChild(author);
                questionRow.appendChild(action);

                questionContainer.appendChild(questionRow);

            }))

    function deleteBtnClicked(event){
        let questionId = event.target.dataset.id;
        let requestOption = {
            method: 'DELETE'
        }

        fetch(`http://localhost:8080/api/questions/${questionId}`,requestOption)
            .then(_=>reloadAllQuestions())
            .catch(error => console.log(error))
    }
}
