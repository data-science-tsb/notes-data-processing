# Ambari Installation
1. [Prepare the Environment](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/prepare_the_environment.html)
```
# Setup Passwordless SSH

# Enable NTP
sudo yum install -y ntp

# Ubuntu NTP
sudo apt install ntp
```
2. Download Repo File for [AMBARI](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/ambari_repositories.html) and [HDP](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/hdp_26_repositories.html)
```
#RHEL
wget http://public-repo-1.hortonworks.com/ambari/centos7/2.x/updates/2.5.0.3/ambari.repo
wget http://public-repo-1.hortonworks.com/HDP/centos7/2.x/updates/2.6.0.3/hdp.repo
sudo mv *.repo /etc/yum.repos.d/
sudo yum repolist

#Ubuntu
wget -O /etc/apt/sources.list.d/ambari.list http://public-repo-1.hortonworks.com/ambari/ubuntu16/2.x/updates/2.5.0.3/ambari.list
apt-key adv --recv-keys --keyserver keyserver.ubuntu.com B9733A7A07513CAD
apt-get update
```
3. Install and Setup Ambari Server
```
#Yum
sudo yum install ambari-server
sudo ambari-server setup

#APT
sudo apt-get install ambari-server
```
4. Start Ambari
```
sudo ambari-server start
sudo ambari-server status
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
