# README

Read the book "MapReduce Design Patterns" for reference.

Download the data sets here: https://archive.org/download/stackexchange

#HDFS
http://hadoop.apache.org/docs/r3.0.0-alpha2/hadoop-project-dist/hadoop-common/FileSystemShell.html
```ssh
#browse:
http://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9870

#create directory:
hadoop fs -mkdir /jars

#upload file:
hadoop fs -put /home/ec2-user/practice-hadoop-v1.3.3.jar /jars

```



## Stuck on a windows machine, deployment procedure:
```
mvn clean package
pscp -i C:\Users\KWL\Desktop\DriveSync\aws-priv.ppk C:\Users\KWL\Desktop\practice-hadoop\target\practice-hadoop-v1.3.3-jar-with-dependencies.jar ec2-user@54.191.254.130:/home/ec2-user
hadoop fs -put /home/ec2-user/practice-hadoop-v1.3.3.jar /jars
hadoop jar practice-hadoop-v1.3.3.jar App hdfs://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9000/input hdfs://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9000/output -job-conf 
hadoop jar practice-hadoop-v1.3.3-jar-with-dependencies.jar App hdfs://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9000/input hdfs://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9000/output
```

mapred-site.xml client
```xml
<property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
    <property>
      <name>mapred.job.tracker</name>
      <value>resourcemanager:8021</value>
 </property>
<property>
      <name>mapreduce.jobtracker.staging.root.dir</name>
      <value>/home/ec2-user/jobstaging</value>
 </property>
```