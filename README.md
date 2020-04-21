# Slime_Sports
Based on the Flash game, Slime Soccer, Slime Sports builds of the basic concept of the game and makes it more complex.

The purpose of the project is to make a fun, competitive, and easy to play game against some friends.  It also adds some addition game modes that were not in the original Slime Soccer. 

All the Slime Games in this project are two player local co-op 2-D games. There will be 4 game modes that can be played. They are soccer, basketball, volleyball, and spleef.

# Goal of each Game
The goal in soccer is to put the ball in the opponent's goal.

The goal in basketball is to put the ball in the opponent's hoop.  However, it must go through the top of the hoop to get the point.

The goal in volleyball is to get the ball to hit the ground on the opponent's side of the screen.  There is a middle net (barrier) to stop the players from going to the other side.  The ball has to go over the net.

The goal in spleef is similar to volleyball; however, the sides will be split into sections so that if the ball lands in a section once, it changes color to red.  The section changing to red is a life line for the player, but if it hits the section now that it is red, the opponent will get the point.

There will be a timer counting down to indicate how much time is left in the game.  The person with the most points at the end wins.  If the points are the same it is a tie.

# Requirements
The game requires the user to be able to run a java file, so the game will play.  We used Eclipse to make and run the game.  You will also need to download all the files so you have all the classes and code required for the game.

Once you have all the files you will want to run the game from the SlimeGames.java file.

## Setup
1.Go to the main page of the GitHub repository

2.Make sure the Master Branch is selected

3.Click Clone or Download Button then click download zip

4.Open the zip file and get to the Slime Games folder

5.Drag the Slime Games folder to your eclipse workspace folder

6.Open Eclipse

7.The Slime Games folder should be in the package explorer if not go to File->Open Projects from File System...->Directory  and find the Slime Games folder and select it

8.If you see the red underlines being marked out, change your java enviroment to JRE-1.7 and add Junit5 for testing

9.Open the Slime Games folder in package explorer and run the SlimeGames.java file

# Controls
### Player 1 Movement 
Move Left: A

Jump: W

Move Right: D

### Player 2 Movement
Move Left: Left Arrow

Jump: Up Arrow

Move Right: Right Arrow

# Ball
The ball will have gravity properties to it, but they may be slightly altered from realistic physics.  Altering the ball physics helps the game to be more enjoyable.

The outside border on all sides of the window are boundaries for the ball and will reflect the ball as if it was a wall.
