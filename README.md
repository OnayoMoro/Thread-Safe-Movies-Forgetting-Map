# Thread-Safe-Movies-Forgetting-Map
## Case Study 
The forgetting map is a featured movies list that holds associations between a movie's details and a key being the movie title. Whenever a movie is searched for, it's 
search is recorded. However, as there can only be `(x)` associations (featured movies) at a time, when a new movie is added, the least searched 
for, or lowest rated, is removed from the forgetting map, removing its association. 

## How To use 
This project was developed in Intellij. The console application has 4 commands, `add, find, print, exit` and each command is case-sensitive. This is also the case when looking up
movie titles using the `find` command.

### Add
Allows you to add new associations between movie objects and their titles.

### Find
A case-sensitive search to look up and return a movie object.

### Print
Prints all movies.

### Exit 
Closes the program.
