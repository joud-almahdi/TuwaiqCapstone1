![v](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/d99b49776bb383f621965c23fb5f162d72c84366/v.png)


# TuwaiqCapstone1

This is the Individual Project for Tuwaiq CapStone 1 for the Kotlin Track. It is a ToDo List App


# List of Technologies

This is ToDo List app develped using Android Studio and the Kotlin Programming language. It also uses some built-in Java libraries for some date-related functionalities

# Wireframes 


Wireframes were made using Figma:https://www.figma.com/file/UFkCwHmVxI0AArtwtOxFfU/Capstone-1-mockup?node-id=0%3A1




![splash](https://user-images.githubusercontent.com/75100342/139945861-9f2ba85a-d0b5-43c3-89ff-67024b997450.png)



This is a prototype of the Splash screen,the first screen of the app.It shows up for a limited time before moving to the main menu





![mainmenu](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/788ffb0451c44cc379790df64234c894271e8e2e/main%20menu.png)





This is first screen that shows to the user. It contains all the tasks found in the database. A checkmark image is displayed on the completed ones, and those that are overdue are marked as such.The user can filter the tasks shown with the button above according to their completion status

![complete](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/d99b49776bb383f621965c23fb5f162d72c84366/MainMenu.png)







![add](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/d99b49776bb383f621965c23fb5f162d72c84366/add.png)




This is the add view where the user can add new tasks to their list.With the exception of the Task name, every other field is optional



![detail](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/2cb3d6e4901b58717e8ab52b33c7f78ab4419ad1/detail.png)

When the user clicks on a task in the main menu, they can view all the details related to said task.From there,they can either edit its detail or delete the task from the database




![edit](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/d99b49776bb383f621965c23fb5f162d72c84366/edit.png)


                                                                        
                                                                        
                                                                        
                                                                                                  
                                                                                                  
                                                                                                  
![delete](https://github.com/joud-almahdi/TuwaiqCapstone1/blob/2cb3d6e4901b58717e8ab52b33c7f78ab4419ad1/detail%20with%20delete.png)



# User Story


*As someone with so much to do everyday around the clock, I want to keep track of my tasks and when to do them so that I can get them done on time and as efficiently as possible ithout forgetting any of them<br />

*As someone who is uncertain about their capabilities,I want to change certain aspects of my tasks so that they accomedate my circumstances as I see fit<br />

*As someone who likes to keep their workspace organized, I want to get rid of completed tasks so that I can keep my list tidy<br />

*As someone who is forgetful, I want to be reminded of tasks so that I get them done before their due date

# Planning and Development Process

The outline for my development plan was similar to this:<br />
1-Design wireframe<br />
2-Design XML<br />
3-Code requirements<br />
4-Code Any possible Extras<br />
5-Cleanup and testing<br />


And starting with step 2, everything related to this app had been backed up here on this Github repository. The general theme that I decided to use for this app was that of an actual, hand-written list,hence the sketchy looking checkmarks and notification icon,among other things.

One issue I faced while implementing my design as XML code was how inconsistent the spacing would often be depending on how much information a single card containd. This was solved by a combination of giving every view a fixed size as well as adding a code for limiting character count on textviews such as the one for task name

For coding the requirements,most of the challenge stemmed from functionalities related to dates. The initial solution used was a calendar object, but that contained the time,day,and date. So the solution was using a LocalDate library instead,which would be formatted to String and back depending on the usage. Calneder objects,however, were still used for datepickers 
One of the extras implemented was notifications for incompleted tasks that are approaching their due date,and while it was a new idea the implementation was rather easy.All that was needed was to have a notification sent for every tasks whose due date is a day away,all of which were stored in a list.
For minor implementations such as crossing out complete tasks,help from online resources such as the official Android documentation was used.
# Unsolved Problems

As of now, notifications can only be sent when the user opens the application. I would like to implement them in such a way that notifications could be sent even when the app isn't open<br />

The RecyclerView can get buggy when it hosts 6 or more items. This could possibly be solved using Diffutil

# Favorite Functioanlity

One of my two favorite funtions was the DatePicker inside of a dialog box. When the button for setting a due date is pressed,the default date  will be the current one using a calneder object as mentioned,and the user cannot go back to any day before it.Then the chosen date will show in an edit text in a localdate format that has been converted to a string .

Another favorite is the checkboxes in the main menu,these are images,and with an OnClickListner, the user can change the state of the task(which includes the image) right then and there without having to go to the edit view.


