# README

## Copy Client JAR
```
pscp -i C:\Users\KWL\Desktop\DriveSync\aws-priv.ppk C:\Users\KWL\.m2\repository\com\lbibera\demo-wordcount\0.0.1-SNAPSHOT\demo-wordcount-0.0.1-SNAPSHOT.jar ec2-user@ec2-54-149-125-168.us-west-2.compute.amazonaws.com:/home/ec2-user
```

## Run Client Jar
```
./hadoop-2.7.3/bin/hadoop jar demo-wordcount-0.0.1-SNAPSHOT.jar input.txt output
```