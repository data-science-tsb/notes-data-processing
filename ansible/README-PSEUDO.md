# Ansible: Hadoop
Source: https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html

## Installation Instructions:

### AWS Security Group Consideration
ports:
22 - ssh
9000 - namenode
9864 - datanode web interface
9870 - namenode web interface
8088 - yarn web interface
8050 - yarn port

### Common
- install java 8
```
sudo yum install java-1.8.0
sudo yum remove java-1.7.0-openjdk
```

- set JAVA_HOME in .bashrc
```
export JAVA_HOME=/usr/lib/jvm/jre-1.8.0-openjdk
```

- SSH self
https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/
```
ssh-keygen -t rsa -b 4096 -C "hadoop@hadoop.com"
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
ssh localhost
exit
```

- download hadoop distribution
```
wget http://mirror.rise.ph/apache/hadoop/common/hadoop-3.0.0-alpha2/hadoop-3.0.0-alpha2.tar.gz
```

-checksum
https://help.ubuntu.com/community/HowToSHA256SUM
```
sha512sum hadoop-3.0.0-alpha2.tar.gz
SHA512 = 8DE2B10F F2BCA8BC 60A16504 D8A3BE28 43C03096 BBCE7C46 D283345E 677C28B7
         B2E61239 05FA243E 895926A5 729E0EF6 5517D208 6C7C6D1A 99D7B7F7 0228A3AB
```

- extract
https://www.cyberciti.biz/faq/linux-unix-bsd-extract-targz-file/
```
tar -zxvf hadoop-3.0.0-alpha2.tar.gz hadoop
mv hadoop-3.0.0-alpha2 hadoop
```


### Single Node:
- test installation
```
mkdir input
hadoop jar $HADOOP_INSTALL/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.0.0-alpha2.jar wordcount input output
cat output/*
```

### Pseudo-Distributed Operation
- hadoop/etc/hadoop/core-site.xml
```
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

- hadoop/etc/hadoop/hdfs-site.xml
```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/Users/lbibera/Documents/Apps/hadoop-3.0.0-alpha2/data/datanode</value>
    </property>
        <property>
        <name>dfs.namenode.data.dir</name>
        <value>/Users/lbibera/Documents/Apps/hadoop-3.0.0-alpha2/data/namenode</value>
    </property>
</configuration>
```

- format the filesystem
```
hdfs namenode -format
```

- start the NameNode, SecondaryNameNode and DataNode daemon
```
./hadoop/sbin/start-dfs.sh
```

- check HDFS dashboard
```
http://localhost:9870/
```

- create the HDFS directories and copy some files
```
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/$USER
hdfs dfs -mkdir input
hdfs dfs -put $HADOOP_INSTALL/etc/hadoop/*.xml input
```

- run the wordcount example
```
hdfs dfs -rm -r -f output
hadoop jar $HADOOP_INSTALL/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.0.0-alpha2.jar wordcount input output
```

- check the output
```
hdfs dfs -get output output
```

- optional: stop the NameNode, SecondaryNameNode and DataNode daemon
```
./hadoop/sbin/stop-dfs.sh
```

- YARN pseudo-distributed mode

- hadoop/etc/hadoop/mapred-site.xml
```
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```

- hadoop/etc/hadoop/yarn-site.xml
```
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.nodemanager.env-whitelist</name>
        <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
    </property>
</configuration>
```

- start the YARN daemon
```
start-yarn.sh
```

- check YARN dashboard
```
http://localhost:8088/
```

- optional: stop YARN
```
stop-yarn.sh
```