# Greg: Your Personal Task Tracking Assistant

Greetings! I’m **Greg**, a lightweight, desktop-based chatbot designed to help you stay organized through a simple Command Line Interface (CLI).

---

##  Quick Start
1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest `greg.jar` file.
3. Open your terminal, navigate to the folder, and run:  
   `java -jar greg.jar`
4. Once the logo appears, start typing commands!

---

##  Features

### 1. Adding Tasks
I can track three different types of tasks. Use the following formats below:

* **To-do**: A basic task without any date.  
  `todo [description]`  
  *Example:* `todo read CS2113 Textbook ;)`
* **Deadline**: For tasks that need to be done by a specific time.  
  `deadline [description] /by [time]`  
  *Example:* `deadline submit Coursemology Exercises /by Thursday 2pm`
* **Event**: For tasks that happen during a specific duration.  
  `event [description] /from [start] /to [end]`  
  *Example:* `event Lock in & Complete Week 7 IP /from 7pm /to 9pm`

### 2. Managing the List
Keep your list tidy with these management commands:

* **List**: See all your current tasks.  
  `list`
* **Mark**: Check off completed tasks.  
  `mark [index]`
  *Example:* `mark 1`
* **Delete**: Remove a task permanently.  
  `delete [index]`  
  *Example:* `delete 2`

### 3. Finding Tasks
Use this command to find a specific task by name.  
`find [keyword]`  
*Example:* `find book` — *This will show all tasks containing the word "book".*

---

##  Data Saving
You will also not need to worry about losing your progress! I have made it so that your tasks are
automatically saved to a hard disk file (`data/greg.txt`) every time you make a change.
Your list of tasks will then be reloaded the next time you open your chatbot!

---

## Exit
When you're finished for the day, just let me know:  
`bye`

---

> **Tip:** If you provide an invalid command or forget a description, I'll let you know with an error message so do not worry about writing incorrect commands!