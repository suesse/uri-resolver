URI Resolver REST Interface For CTS2
====================================

# Configure Tomcat Context
Configure and add the following resources to your context.xml:
```xml
<Resource name="jdbc/uriResolverDataSource"
          auth="Container"
          type="javax.sql.DataSource"
          driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
          username="user"
          password="secret"
          url="jdbc:sqlserver://ROPFDN812Q\FDNDEV18:4900;databaseName=lcd_uriresolver" />

<Environment name="uriResolverDatabaseEditable"
             override="false"
             type="java.lang.Boolean"
             value="true" />

<Environment name="uriResolverDatabaseCredentials"
             override="false"
             type="java.lang.String"
             value="user password" />
```
