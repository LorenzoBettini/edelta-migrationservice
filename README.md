# Edelta Migration Service
A rest API to migrate models using [Edelta framework](https://github.com/LorenzoBettini/edelta)

## Build from Source
The software requirements can be summarized as:
* Latest [OpenJDK 17](https://openjdk.org/) (or other Java distribution like [Eclipse Adoptium](https://adoptium.net/)) is recommended. 
* Latest [Apache Maven](https://maven.apache.org/) installed.

### Get the Source Code
```
git clone https://github.com/gssi/edelta-migrationservice.git
cd edelta-migrationservice
```

### Build from the Command Line
Before building the source code, please download the person migration example from the following GitHub repository [https://github.com/LorenzoBettini/edelta-example-migration-personlist/tree/ci](https://github.com/LorenzoBettini/edelta-example-migration-personlist/tree/ci)

Then, run the following Maven command:

```
cd edelta-example-migration-personlist-ci
mvn clean install
```

Modify the `migration:modelfolder` property within the `src/main/resources/config/application-dev.yml`. The property is used to define the folder where the REST service stores the input models to be migrated and migrated ones.

To compile, test, and build all jars, distribution zips, and docs use:

```
cd edelta-migrationservice
mvn clean package
cd target
java -jar edelta-migrationservice.jar
```

