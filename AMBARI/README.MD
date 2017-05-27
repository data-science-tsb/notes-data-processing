# Ambari Installation
1. Prepare the Ambari Server
```
sudo apt install ntp
sudo wget -O /etc/apt/sources.list.d/ambari.list http://public-repo-1.hortonworks.com/ambari/ubuntu16/2.x/updates/2.5.0.3/ambari.list
sudo apt-key adv --recv-keys --keyserver keyserver.ubuntu.com B9733A7A07513CAD
sudo apt-get update
sudo yum install ambari-server
sudo apt-get install ambari-server
sudo ambari-server setup
sudo ambari-server start
```

2. Prepare the Ambari Slaves
```
sudo apt install ntp
sudo apg install python-minimal
sudo wget -O /etc/apt/sources.list.d/ambari.list http://public-repo-1.hortonworks.com/ambari/ubuntu16/2.x/updates/2.5.0.3/ambari.list
sudo apt-key adv --recv-keys --keyserver keyserver.ubuntu.com B9733A7A07513CAD
sudo apt-get update
```

3. Launch the Ambari Web Client
```
http://ec2-xxx-xxx-xxx-xxx.us-west-2.compute.amazonaws.com:8080/#/installer/step2
```

4. Configure Services
```
xxx
```

5. Prepare an external Postgres Server
```sql
CREATE ROLE hive PASSWORD 'bosschix123' VALID UNTIL 'infinity';
ALTER ROLE hive WITH LOGIN;

CREATE DATABASE hive
  WITH ENCODING='UTF8'
       OWNER=hive
       CONNECTION LIMIT=-1
       TABLESPACE=pg_default;

CREATE ROLE oozie PASSWORD 'bosschix123' VALID UNTIL 'infinity';
ALTER ROLE oozie WITH LOGIN;

CREATE DATABASE oozie
  WITH ENCODING='UTF8'
       OWNER=oozie
       CONNECTION LIMIT=-1
       TABLESPACE=pg_default;
```

6. [Log In to Apache Ambari](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/log_in_to_apache_ambari.html)
```
http://<your.ambari.server>:8080
credentials: 
admin/admin
admin/bosschix123
```
