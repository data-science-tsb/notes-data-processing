# Hadoop: Cluster Setup

- configure hadoop/etc/hadoop/core-site.xml (ALL)
```
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9000</value>
    </property>
</configuration>
```

- configure hadoop/etc/hadoop/hdfs-site.xml (NameNode)
```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.blocksize</name>
        <value>268435456</value>
    </property>
    <property>
        <name>dfs.namenode.handler.count</name>
        <value>10</value>
    </property>
</configuration>
```

- configure hadoop/etc/hadoop/yarn-site.xml (NameNode)
```
<configuration>
    <property>
        <name>yarn.acl.enable</name>
        <value>false</value>
    </property>
    <property>
        <name>yarn.log-aggregation-enable</name>
        <value>true</value>
    </property>
</configuration>
```

- configure hadoop/etc/hadoop/yarn-site.xml (ResourceManager)
```
<configuration>
    <property>
        <name>yarn.acl.enable</name>
        <value>false</value>
    </property>
    <property>
        <name>yarn.log-aggregation-enable</name>
        <value>true</value>
    </property>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>ec2-54-191-254-130.us-west-2.compute.amazonaws.com</value>
    </property>
</configuration>
```

- configure hadoop/etc/hadoop/workers (NameNode and ResourceManager)
```
ec2-54-202-211-108.us-west-2.compute.amazonaws.com
ec2-54-187-55-192.us-west-2.compute.amazonaws.com
```

- format hdfs (NameNode)
```
./hadoop/bin/hdfs namenode -format kwlcluster
```

- start hdfs (NameNode)
```
./hadoop/bin/hdfs --daemon start namenode
```

- check if namenode is running
```
http://ec2-54-187-57-163.us-west-2.compute.amazonaws.com:9870
```