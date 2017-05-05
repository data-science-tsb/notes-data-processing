# AMBARI Installation

1. [Determine Stack Compatibility](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.6.0/bk_support-matrices/content/ch_matrices-ambari.html#ambari_stack) / [Min System Requirements](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/meet_minimum_system_requirements.html)
1. [Prepare the Environment](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/prepare_the_environment.html)
```
# Setup Passwordless SSH
ssh-keygen
cat id_rsa.pub >> authorized_keys
chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys

# Enable NTP
yum install -y ntp
```
3. Download Repo File for [AMBARI](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/ambari_repositories.html) and [HDP](https://docs.hortonworks.com/HDPDocuments/Ambari-2.5.0.3/bk_ambari-installation/content/hdp_26_repositories.html)
```
wget http://public-repo-1.hortonworks.com/ambari/centos7/2.x/updates/2.5.0.3/ambari.repo
wget http://public-repo-1.hortonworks.com/HDP/centos7/2.x/updates/2.6.0.3/hdp.repo
sudo mv *.repo /etc/yum.repos.d/
sudo yum repolist
```
4. Install Ambari Server
```
sudo yum install ambari-server
```
