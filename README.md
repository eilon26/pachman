system  structure : 
the main class is MyFrame that is responsible for the GUI. 
by the user selections the system  build abstract stracture (GameBoard) that contain all the pachman and fruit in the game. it can built from manual addition of the user and/or by open csv file that contain pachmans and fruits.
After pressing start the gui use the class ShortestPathAlgo that responsible to calculate the ideal routes and save tham. than the GUI use the method "drawRealTime" to create a Thread "draw_thread" to each pachman. each "draw_thread" is responsible  to update on real time 2 stractures that the method MyFrame.paint() draw by tham the position of the players(fruits and pachmans). the stractures are myFrame.GameBoard and MyFrame.sol.
additionally MyFrame use sol2kml class to save the routes to kml file. 
Moreover it use GB2csv to save the starting positions to csv file. GB2csv use GB_copy_csv that saved the starting positions.

the algo for calculate pathes for each pachman:
the class ShortestPathAlgo that responsible for calculate pathes for each pachman get the object GameBoard that contain all the pachman and fruit. it calculate all the all the distance between each pachman to each fruit, then calculate for each pachman how mach time it takes to go through his distance(refer to pachman's speed and radius) that calculate before and at the end choose the shortest time. and take out the fruit that chosen from the GameBord collection and change the pachman position in accordance.

how to run the game:
click on setting to choose the pachman radius,speed,and the frequency of the shows on the screen.
then click on add. choose to add pachman or fruit. than add then manualy on the image area.
if you want you can also add a ready csv file. by click on file then load csv file.
now you can press on "start" to run the game.
you can click on file then "save to csv file" in order the positions before the running.
moreover you can save to kml file by click on file then "save to kml".
if you want to clear the board you can press on "clear".

made by:Eilon Tsadok 304950645 and Daniel Venture 318875085 
