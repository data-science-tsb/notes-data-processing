# README

Read the book "MapReduce Design Patterns" for reference.

Download the data sets here: https://archive.org/download/stackexchange

#HDFS
http://hadoop.apache.org/docs/r3.0.0-alpha2/hadoop-project-dist/hadoop-common/FileSystemShell.html
```ssh
#browse:
http://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9870

#create directory:
hadoop fs -mkdir /user/lbibera/jars

#upload file:
hadoop fs -put /home/ec2-user/practice-hadoop-v1.3.3.jar /jars

mvn clean package
hadoop fs -put target/design-patterns-v1.3.3-jar-with-dependencies.jar /user/lbibera/jars

```

## Start Local Pseudo Cluster
```ssh
hdfs namenode -format
start-dfs.sh
start-yarn.sh
```

## Validate Local Cluster
```
http://localhost:9870/dfshealth.html#tab-overview
http://localhost:8088/cluster
```

## Make the HDFS Directories required to Execute MR Jobs:
```ssh
# user directories
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/dr.who
hdfs dfs -mkdir /user/$USER
```

## Copy the Input files into HDFS
```ssh
hdfs dfs -mkdir input
hdfs dfs -put $HADOOP_INSTALL/etc/hadoop/*.xml input
```

## Test Local Cluster
```ssh
hadoop jar $HADOOP_INSTALL/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.0.0-alpha1.jar grep input output 'dfs[a-z.]+'
```

## Mac Deployment: Local Pseudo Cluster
```ssh
mvn clean package
hdfs dfs -rm -r -f output
hadoop jar target/design-patterns-v1.3.3.jar com.lbibera.hadoop.designpatterns.MainDriver average input output

notes: 
- on Macs, the ssh daemon is not activated by default, go to Preferences > Sharing > Enable/Check Remote Login
```

mapred-site.xml client
```xml
<property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
    <property>
      <name>mapred.job.tracker</name>
      <value>localhost:8021</value>
 </property>
<property>
      <name>mapreduce.jobtracker.staging.root.dir</name>
      <value>/home/$USER/jobstaging</value>
 </property>
```