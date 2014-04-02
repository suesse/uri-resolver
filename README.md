URI Resolver REST Interface For CTS2
====================================

# Configure Tomcat Context:
<pre>
<Context>
 ...
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
 ...
</Context>
</pre>

##### To use In-memory database
An in-memory database will be utilized if the two previous options are not available.
Nothing is required to configure this database.  It will only exist during the REST connection.
