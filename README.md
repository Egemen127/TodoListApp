# Usage

This application helps to keep track incoming tasks of the week. You can add a task using the form below the page also you can edit or delete an existing task by clicking buttons near the task. The app has user authentication, so each user has to enter their credentials to see and edit their own tasks.  

# Signup

Signup is done via SQL queries on the database(for now).

# Authentication

User authentication is done by jwt tokens. Each time a user logs in, a jwt is created an given to the user. The jwt is stored in localStorage of the browser. I consider storing the current user information using context in react application.

#React app Structure

The app has 2 main components, login page and main page where the tasks are displayed. Main page includes a component called EditForm for editing and adding tasks. Each task is displayed in an Item component.

# SpringBoot API Endpoints

All endpoints require user authentication except "/authenticate" and "/signup"

"/": returns all tasks of the current user

"/add": add a task for current user. The task is specified in the rerquest body.

"/delete/{id}": deletes the task with given id.

"/edit/{id}": edits the task with given id.

"/deleteAll": deletes every task in the database.

"/authenticate": verifies user credentials in request body. If the credentials are valid, a jwt token is handed to the user. Otherwise, an exception is thrown.

"/signup": adds a user to the db.

# Tests

(Coming Soon)
