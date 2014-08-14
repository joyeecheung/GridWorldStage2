##Dependencies
For each project, create a directory named `lib` under the project root directory and put required jars under it.

For example, to build Part3:

Before adding libraries:

    Part3/
    ├── asset
    │   └── Jumper.gif
    ├── build.xml
    ├── src
    │   ├── Jumper.java
    │   └── JumperRunner.java
    └── test
        └── JumperTest.java


After adding libraries:

    Part3/
    ├── asset
    │   └── Jumper.gif
    ├── build.xml
    ├── lib
    │   ├── gridworld.jar
    │   ├── hamcrest-core-1.3.jar
    │   └── junit-4.11.jar
    ├── src
    │   ├── Jumper.java
    │   └── JumperRunner.java
    └── test
        └── JumperTest.java

###Ant Targets
* `ant`: compile
* `ant run`: run the program
* `ant test`: run JUnit tests
* `ant report`: generate JUnit test reports

##About
Author: Joyee Cheung
Time: 2014 Summer
