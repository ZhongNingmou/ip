# User Guide

##Welcome to Duke

Duke is a personal task manager to help users manage their tasks, including events, deadlines, and todo tasks.
## Features 

### Add tasks

You can add a `todo`, `event`, or `deadline` task to your task list.   

### List all tasks

You can print out your task list.

### Mark as done

You can mark a task as done.

### Delete tasks

You can delete tasks from your list.

### Find tasks 

You can find tasks with certain keywords from your task list.

### Load tasks

When you open Duke, the tasks stored in a local file `duke.txt` will be loaded automatically. If the file does not exist, Duke will create a new local file `duke.txt`.
  
### Save tasks

The changes will be saved into a local file `duke.txt` automatically if you make any to your task list.

### Exit

You can use `bye` to exit Duke. 

## Usage

### `todo` - Add a todo task

Enter `todo` with a description to add a todo task.

Example of usage: 

`todo borrow book`

Expected outcome:

```
-------------------------------------------
Got it. I've added this task: 
[T][✘]borrow book
Now you have 2 tasks in the list
-------------------------------------------
```

### `event` - Add an event task 

Enter `event` with a description and its date/time to add an event task.

Example of usage: 

`event project meeting /at Mon 2-4 pm`

Expected outcome:

```
 -------------------------------------------
 Got it. I've added this task: 
 [E][✘]project meeting (at: Mon 2-4 pm)
 Now you have 1 tasks in the list
 -------------------------------------------
```

### `deadline` - Add a deadline task

Enter a `deadline` with a description and its due date/time to add a deadline event.

Example of usage: 

`deadline return book /by Sunday`

Expected outcome:

```
-------------------------------------------
 Got it. I've added this task: 
 [D][✘]return book (by: Sunday)
 Now you have 3 tasks in the list
 -------------------------------------------
 ```

### `done` - Mark a task as done

Enter `done` with an index number to mark your task as done.

Example of usage: 

`done 1`

Expected outcome:

```
-------------------------------------------
Nice! I've marked this task as done:
[E][✓]project meeting (at: Mon 2-4 pm)

-------------------------------------------
```

### `list` - List all the tasks

Enter `list` to see your task list.

Example of usage: 

`list`

Expected outcome:

```
-------------------------------------------
1. [E][✓]project meeting (at: Mon 2-4 pm)
2. [T][✘]borrow book
3. [D][✘]return book (by: Sunday)

-------------------------------------------
```

### `delete` - Delete a task

Enter `delete` with an index number to delete your task.

Example of usage: 

`delete 2`

Expected outcome:

```
-------------------------------------------
Noted. I've removed this task: 
[T][✘]borrow book
Now you have 2 items in the list.

-------------------------------------------
```

### `find` - Find the tasks with a keyword

Enter `find` with a keyword to find out all your tasks with this keyword.

Example of usage: 

`find book`

Expected outcome:

```
-------------------------------------------
Here are the matching tasks in your list:
1.[D][✘]return book (by: Sunday)

-------------------------------------------
```

### `bye` - Exit Duke

Enter `bye` to exit Duke.

Example of usage: 

`bye`

Expected outcome:

```
-------------------------------------------
Bye. Hope to see you again soon!

-------------------------------------------
```