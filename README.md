Hockey-Stats
============

A simple Java FX application that I created for a database class I took in Fall 2014.

The database was generated from another project I made that scrapes the NHL.com website.
For legal reasons, the source code for that project is not publicly available.

### Requirements

- Java 8 JDK

To find out which version you have installed open a terminal and run `javac -version`

You should get an output similar to this `javac 1.8.0_25`

If you don't have version 1.8 you can download it from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

### Compiling - Linux

1. Open a terminal and navigate to the project root. 
2. `mkdir out`
2. `javac -sourcepath src -classpath lib/sqlite-jdbc-3.7.2.jar src/pool/Main.java -d out`

### Compiling - Windows

_Use the same commands as above but change the forward slashes to back slashes and 
replace with colons in the classpath with semicolons.  The same applies for running the application._

### Running

`java -classpath lib/sqlite-jdbc-3.7.2.jar:out:src pool.Main`

