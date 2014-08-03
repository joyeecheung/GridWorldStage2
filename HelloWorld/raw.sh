mkdir -p build/classes

#javac -cp ./lib/apache-log4j-1.2.17/log4j-1.2.17.jar -sourcepath src -d build/classes src/odata/*
#java -cp ./lib/apache-log4j-1.2.17/log4j-1.2.17.jar:build/classes odata.HelloWorld
#jar cfm build/jar/HelloWorld.jar myManifest -C build/classes .

#java -cp build/jar/HelloWorld.jar:lib/apache-log4j-1.2.17/log4j-1.2.17.jar odata.HelloWorld
#javac -cp build/jar/HelloWorld.jar:lib/apache-log4j-1.2.17/log4j-1.2.17.jar:$ANT_HOME/lib/* test/HelloWorldTest.java -d build/classes
#java -cp build/jar/HelloWorld.jar:lib/apache-log4j-1.2.17/log4j-1.2.17.jar:$ANT_HOME/lib/*:build/classes org.junit.runner.JUnitCore HelloWorldTest

javac -sourcepath src -d build/classes src/*
java -cp build/classes HelloWorld
javac -cp lib/*:build/classes -d build/classes test/*
java -cp lib/*:build/classes org.junit.runner.JUnitCore HelloWorldTest

#javac -cp "lib/*" -d build/classes src/* test/*
#java -cp build/classes HelloWorld
#java -cp lib/*:build/classes org.junit.runner.JUnitCore HelloWorldTest
