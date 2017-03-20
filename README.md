#Hadoop Cluster Setup with Ansible
http://hadoop.apache.org/docs/r3.0.0-alpha2/hadoop-project-dist/hadoop-common/ClusterSetup.html


ansible-playbook playbook.yaml --private-key ~/Documents/KWL/keys/dev-lyndon.pem  --user ec2-user

## Check Machines
```
namenode:9870
resourcemanager:8088
```