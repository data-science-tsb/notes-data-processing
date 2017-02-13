# Ansible: Hadoop
Source: https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html

## Installation Instructions:

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
./hadoop/bin/hadoop
mkdir input
cp hadoop/*.txt input
./hadoop/bin/hadoop jar hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.0.0-alpha2.jar grep input output 'dfs[a-z.]+'
cat output/*
```