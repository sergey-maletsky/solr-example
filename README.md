Java version is 1.8.0_131

Deployment

I Common information

1. Application is running on the 7070 port

2. Swagger Api Documentation is available at http://localhost:7070/swagger-ui.html

II Configuring database

Database connection settings are in the follow file:
src/main/resources/spring/database.properties

1. Install PostgreSQL 9.6
2. Execute the createdb.sql script within PostgreSQL 
console under superuser(it's postgres in here): 
psql -U postgres -a -f createdb.sql

The script will add "solr_example" database as well as 
add new user with name "snm" with access rights only to this database. 

III Build automation tool

There is Gradle as a build automation tool in this project.

IV Solr

To use Solr in this project you need:
1. go to http://lucene.apache.org/solr/mirrors-solr-latest-redir.html 
2. download 7.4.0 solr version
3. unzip Solr archive
4. go to solr-<version>/bin directory
5. execute "./solr start" command if you use Linux or "solr.cmd start" if you use Windows
6. execute "./solr create -c users" command if you use Linux or "solr.cmd create -c users" if you use Windows