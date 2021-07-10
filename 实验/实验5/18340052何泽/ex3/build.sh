javac -d bin -classpath ../bin src/exceptions/*.java
cd src
javac -d ../bin -Djava.ext.dirs=../javacup *.java
cd ..
