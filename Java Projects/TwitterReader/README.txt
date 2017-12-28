Info:

* I wrote this program in Java with Jetbrains Intellij on a windows 10 machine.

* To run the program in Intellij you need to set the command line arguments.
  To do this go to run > Edit Configurations... > Application > and under program arguements put @gofooda 10

*You must include your own consumer key, consumer secret, access token, and access secret for the program to work.

* To run the program on the command line navigate to the src folder of this project. Then, make sure
  that the twitter4j-core-4.0.4.jar file is in the src (It should be, but if not, I included all of the
  twitter4j jar files with this project when you open the zip file). Then, create a class file from the java file
  (class file should be made though). Finally, run the following command to run the program
  java -cp twitter4j-core-4.0.4.jar;. TwitterReader @gofooda 10