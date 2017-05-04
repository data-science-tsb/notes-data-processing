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
