system  structure : 
the main class is MyFrame that is responsible for the GUI. 
during its initialization MyFrame using the class ariel_map that extands the class map. this class is resposible to creat the background image of the GUI and for all the convertations between pixel to global and the opposite during the program is running.

by the user selections the system  build abstract stracture (GameBoard) that contain all the pachman object and fruit object in the game. it can built from manual addition of the user and/or by open csv file that contain pachmans and fruits.
the pachman/fruit classes include the possition of the pachman/fruit and matadata about it.
After pressing start the gui use the class ShortestPathAlgo that responsible to calculate the ideal routes and save tham. than the GUI use the method "drawRealTime" to create a Thread "draw_thread" to each pachman and to create Thread "paint_thread". each "draw_thread" is responsible to update on real time 2 stractures (the stractures are myFrame.GameBoard and MyFrame.sol) about the location of its pachman and the fruit that its pachman ate. In parallel the "paint_thread" is calling the method MyFrame.paint() that draw (using those stractures) the position of the players(fruits and pachmans). 
additionally MyFrame use sol2kml class to save the routes to kml file. 
Moreover it use GB2csv to save the starting positions to csv file. GB2csv use GB_copy_csv that saved the starting positions.

the algorithem for calculate pathes for each pachman:
the class ShortestPathAlgo that responsible for calculate pathes for each pachman get the object GameBoard that contain all the pachmans and fruits. until there are no more fruits it perform the following algorithm:
it goes thruogh all the pachmans for each pachman it goes through all the fruits. then for each fruit the algorithem calculate the time it will take for the pachman to reach to the fruit(refer to pachman's speed and radius). then the algorithem add the time that the pachman went since the game start to the time that it is already calculate. let mark the result of the adding as "time_so_far".  it find the smallest "time_so_far" between all the pachmans and fruis. and take out the fruit that was chosen from the GameBord collection and change the pachman position in accordance.

how to run the game:
click on setting to choose the pachman radius,speed,and the frequency of the shows on the screen.
then click on add. choose to add pachman or fruit. than add then manualy on the image area.
if you want you can also add a ready csv file. by click on file then load csv file.
now you can press on "start" to run the game.
you can click on file then "save to csv file" in order the positions before the running.
moreover you can save to kml file by click on file then "save to kml".
if you want to clear the board you can press on "clear".

made by:Eilon Tsadok 304950645 and Daniel Venture 318875085 
