Josh Edwards
cs430 lab 5 spring 2018

all src files exist in a package called lab5

extract file into a presumably empty directory, using unzip
the jdbc connector jar is included to ease in making this README
I used javafx for the GUI, will be included in the compile instructions below

unzip lab5.zip
javac -cp mysql-connector-java-5.1.46.jar:/usr/local/jdk1.7.0_79/jre/lib/jfxrt.jar ./src/lab5/*.java


this works for compiling, but since this is a gui, it makes more sense to do this in eclipse
if that is possible, the javafx jar is /usr/local/jdk1.7.0_79/jre/lib/jfxrt.jar, or at least the most recent one i could find on the school machines.

if doing in eclipse... https://github.com/duratothemaxx/cs430lab5.git