mvn clean install

if [ "$1" ]
then
    java -jar target/parkinglot-1.0-SNAPSHOT.jar  $1
else
    java -jar target/parkinglot-1.0-SNAPSHOT.jar
fi
