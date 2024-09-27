const addButton = document.getElementById("addTaskBtn");

addButton.addEventListener("click", addTask);

function addTask() {
    const taskContainer = document.createElement("div");
    taskContainer.classList.add('task-item');

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.classList.add('checkbox');

    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.classList.add('task-btn');

    const editButton = document.createElement("button");
    editButton.innerText = "Edit";
    editButton.classList.add('task-btn');

    const taskText = document.createElement("span");
    taskText.innerText = document.getElementById("taskInput").value;

    if (taskText.textContent === "") {
        return;
    }

    taskContainer.append(checkbox);
    taskContainer.append(taskText);
    taskContainer.append(editButton);
    taskContainer.append(deleteButton);
    taskContainer.classList.add('task-item');

    deleteButton.addEventListener("click", (e) => {
        e.target.parentElement.remove(taskContainer);
    });

    editButton.addEventListener("click", (e) => {
        const text = taskText.textContent;

        const newText = document.createElement("input");
        newText.type = "text";
        newText.value = text;

        taskContainer.replaceChild(newText, taskText);
        newText.addEventListener("keyup", (e) => {
            if (e.key === "Enter") {
                taskText.textContent = newText.value;
                taskContainer.replaceChild(taskText, newText);
            }
        });
    })

    const parentDiv = document.getElementById("taskList");
    parentDiv.appendChild(taskContainer);
}
