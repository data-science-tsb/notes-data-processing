# AWS EMR

## Executing a Spark Step
```
aws emr create-cluster \
--name "Add Spark Step Cluster" \
--release-label emr-5.30.0 \
--applications Name=Spark \
--instance-type m5.xlarge \
--log-uri s3://hh-poc-glue-oregon/logs/ \
--ec2-attributes SubnetIds=subnet-038c60767604ec5d2 \
--instance-count 3 \
--auto-terminate \
--steps Type=Spark,Name="Spark Program",ActionOnFailure=TERMINATE_CLUSTER,Args=[--class,io.headhuntr.candidate.CandidateLoader,s3://hh-poc-glue-oregon/java-lib/data-processor_2.11-0.1.0.jar:s3://hh-poc-glue-oregon/java-lib/elasticsearch-hadoop-7.1.1.jar] \
--use-default-roles
```

#### Resources
- [AWS CLI Docs](https://docs.aws.amazon.com/cli/latest/reference/emr/create-cluster.html)
