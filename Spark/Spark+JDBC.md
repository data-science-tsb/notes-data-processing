# Spark + JDBC Connector

## Add The Driver
```
org.postgresql:postgresql:42.1.1	
```

## Prepare the Connection
```scala
val jdbcUsername = "user"
val jdbcPassword = "pass"
val jdbcHostname = "host.com"
val jdbcPort = 5432
val jdbcDatabase ="myDB"
val jdbcUrl = s"jdbc:postgresql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}?user=${jdbcUsername}&password=${jdbcPassword}"
val connectionProperties = new java.util.Properties()
```

## Connect to Table
```scala
val dimArtist = spark.read.jdbc(jdbcUrl, "dim_artist", connectionProperties)

dimArtist.show()
```
