csv2kml
this project let you to convert many csv files into one kml file;
inorder to do so put all your csv files inside a folder(folder inside folder it is ok).
and run this comment - new MultiCSV("your folder path").project2kml("the path of thr kml file that you want to make");

more over you can use this project to convert csv file into a data structure that organize that by their feature.
the data structure base is an element that represent a point.  it has the geom fild with all the geometric information on this point
and metadata fild with more information on the point such as location name, color, time and more.
the next level in the data structure is layer. it is a colection of many points that together represent one route record.
the next level is project. it is a colection of layers and represent many routes records.

this  data structure will make it easy for you to convert csv files into other types of files.
in order to do so run this order new MultiCSV("your folder path").getPr()
