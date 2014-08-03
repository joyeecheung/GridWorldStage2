mkdir -p build/classes

#javac -sourcepath src -d build/classes src/calculator/*
#javac -cp "lib/*:build/classes" -d build/classes test/*

javac -cp "lib/*" -d build/classes test/* -sourcepath src src/calculator/* test/*


java -cp "lib/*:build/classes" org.junit.runner.JUnitCore CalculatorTest
