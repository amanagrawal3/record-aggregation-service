# Record Aggregation Rervice
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Record Aggregation Service is a micro-service to address some functionality which is useful to derive simplified summary statistics (mean, min, max) on a dataset.

## Features
- Add a new record to the dataset.
- Delete a new record from the dataset.
- fetch Summary Statistics(min, max, mean) for salary
    - Entire dataset
    - GroupBy department
    - GroupBy department and subGroup by subDepartment
## Running the sample app
##### **_Step 1 : With Docker_**
###### Prerequisite
- Install docker on windows or mac or linux
```
Windows : https://docs.docker.com/desktop/install/windows-install/
Mac : https://docs.docker.com/desktop/install/mac-install/
Ubuntu : https://docs.docker.com/engine/install/ubuntu/
```
- Start docker engine

Steps to build a Docker image:
- Unzip zip file or clone repo
```
unzip aman-agrawal.zip
git clone https://github.com/amanagrawal3/record-aggregation-service.git
```
- Go to record-aggregation-service and build the image
```
docker build -t record-service .
```
This will take a few minutes.

- Run the image's default command, which should start everything up. The -p option forwards the container's port 8080 on the host. (Note that the host will actually be a guest if you are using boot2docker, so you may need to re-forward the port in VirtualBox.)
```
docker run -d -p 8080:8080 record-service
```

- Check if tomcat process is started/running
``` docker ps
  C:\Users\91800\Documents\projects\microservice> docker ps
  CONTAINER ID   IMAGE            COMMAND                  CREATED          STATUS          PORTS                    NAMES
  9a67896a970b   record-service   "java -jar app.jar -…"   46 seconds ago   Up 45 seconds   0.0.0.0:8080->8080/tcp   laughing_shannon
  ```

- Tail logs for debugging
```aidl
docker logs 9a67896a970b(<container-id>) --follow
```

- Kill process
```aidl
docker kill 9a67896a970b(<container-id>)
```

- Restart process
```aidl
docker restart 9a67896a970b(<container-id>)
```
_____________________________________________________
##### Step 2 : Without Docker

###### Prerequisite
- Install Java17 on local system and set JAVA_HOME variable
- Install intellij or run throuh command line

Multiple steps:

-   Execute the main method in the **com.aman.Application** class from your IDE.
- Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.maven "Spring Boot Maven plugin") like so:
  ```mvn spring-boot:run```
  ​

____________________________________________________________________
## API Operations
_____________________________________________________________________
### Authentication API's

- ### Registering a User:
This API is used to register a new user who have admin access to perform CRUD on records.

##### HappyPath - Request
```
curl --location --request POST 'http://localhost:8080/api/users/register' \
--header 'accept: */*' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "amantest",
    "password": "test",
    "device": "web",
    "email": "aman@gmail.com"
}'
```
##### HappyPath - Response
```
{
    "id": 12,
    "username": "amantest",
    "password": "$2a$10$.3hGrCtMxe5r1uTvzvqjIOG3ahT6gAOH.4tAGXNc2bpxv8doL9K1m",
    "email": "aman@gmail.com",
    "lastPasswordReset": "2022-11-01T08:39:39.107+00:00",
    "authorities": "ADMIN"
}
```

##### Other Request Collections

[RegisterUser](https://github.com/amanagrawal3/record-aggregation-service/blob/main/collections/RegisterUser.postman_collection.json)


- ### Fetch a user token:
This API is used to fetch a valid token for a user to perform API operations.
The response token need to be passed in X-AUTH-TOKEN header for all subsequent API's

##### HappyPath - Request
```
curl --location --request POST 'http://localhost:8080/api/auth' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "aman",
    "password": "test",
    "device": "web"
}
```
##### HappyPath - Response
```
200 OK
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjMzMjc2NTEzLCJleHAiOjE2Njc4MzgwNzZ9.-cfLvPrbDK1sxpJRCwQP0mk3LccZR4d6GY32FnvKhGvnHxsU4Kbg6iy42Idui68ywwdtnIHaz240a2EScwcJbA"
}
```
______________________________________________________________________________________
### Record API's

- ### Add new Record:
This API is used to add a new record in the system.

#### Prerequsite
Fetch token for valid user mentioned above

##### HappyPath - Request
```
curl --location --request POST 'http://localhost:8080/api/v1/records' \
--header 'X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjMzMjc2NTEzLCJleHAiOjE2Njc4MzgwNzZ9.-cfLvPrbDK1sxpJRCwQP0mk3LccZR4d6GY32FnvKhGvnHxsU4Kbg6iy42Idui68ywwdtnIHaz240a2EScwcJbA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "aman",
    "salary": "12300",
    "currency": "USD",
    "department": "ENGINEERING",
    "sub_department": "PLATFORM"
}'

```
##### HappyPath - Response
```
201 Created
{
    "id": 12,
    "name": "aman",
    "salary": "12300",
    "currency": null,
    "department": "ENGINEERING",
    "subDepartment": "PLATFORM"
}
```
##### Other Request Collections

[AddRecord](https://github.com/amanagrawal3/record-aggregation-service/blob/main/collections/AddRecord.postman_collection.json)

- ### Delete Record:
This API is used to delete a record from the system.

#### Prerequsite
Fetch token for valid user mentioned above

##### HappyPath - Request
```
curl --location --request DELETE 'http://localhost:8080/api/v1/records/8' \
--header 'X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjQxMTc3MDU2LCJleHAiOjE2Njc4NDU5Nzd9.30YS8K0BysfiL3Ww4JetALDCOZIfDpkRHfrnQcsssEvxo0naxzyUPi54YLybjca03bGouLmk9VV-LMpyKaigAg' \
--data-raw ''
```
##### HappyPath - Response
```
200 OK
```

##### Other Request Collections

[DeleteRecord](https://github.com/amanagrawal3/record-aggregation-service/blob/main/collections/DeleteRecord.postman_collection.json)

____________________________________________
- ### Fetch Record Summary Statistics:
This set of API's is used to fetch summary statistics of records.

#### **Prerequsite**
Fetch token for valid user mentioned above

- #### Fetch SS for all records
  This API return summary statistics for all records

  ##### HappyPath - Request
```
curl --location --request GET 'http://localhost:8080/api/v1/records/summary' \
--header 'X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjMzMjc2NTEzLCJleHAiOjE2Njc4MzgwNzZ9.-cfLvPrbDK1sxpJRCwQP0mk3LccZR4d6GY32FnvKhGvnHxsU4Kbg6iy42Idui68ywwdtnIHaz240a2EScwcJbA'
```
  ##### HappyPath - Response
```
200 OK
{
    "summaryStatistics": [
        {
            "department": "ALL",
            "subDepartment": "ALL",
            "min": 30,
            "max": 200000000,
            "mean": 20066739,
            "totalElements": 10
        }
    ]
}
```

- #### Fetch SS for all records group by department
  This API return summary statistics for all records group by department

  ##### HappyPath - Request
```
curl --location --request GET 'http://localhost:8080/api/v1/records/summary/dept' \
--header 'X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjMzMjc2NTEzLCJleHAiOjE2Njc4MzgwNzZ9.-cfLvPrbDK1sxpJRCwQP0mk3LccZR4d6GY32FnvKhGvnHxsU4Kbg6iy42Idui68ywwdtnIHaz240a2EScwcJbA'
```
  ##### HappyPath - Response
```
200 OK
{{
    "summaryStatistics": [
        {
            "department": "OPERATIONS",
            "subDepartment": "ALL",
            "min": 30,
            "max": 70000,
            "mean": 35015,
            "totalElements": 2
        },
        {
            "department": "ENGINEERING",
            "subDepartment": "ALL",
            "min": 30,
            "max": 200000000,
            "mean": 40053466,
            "totalElements": 5
        },
        {
            "department": "ADMINISTRATION",
            "subDepartment": "ALL",
            "min": 30,
            "max": 30,
            "mean": 30,
            "totalElements": 1
        },
        {
            "department": "BANKING",
            "subDepartment": "ALL",
            "min": 90000,
            "max": 240000,
            "mean": 165000,
            "totalElements": 2
        }
    ]
}
```

- #### Fetch SS for all records group by department and subgroup by subDepartment
  This API return summary statistics for all records group by department and subgroup by subDepartment

  ##### HappyPath - Request
```
curl --location --request GET 'http://localhost:8080/api/v1/records/summary/deptSubdept' \
--header 'X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNjY3MjMzMjc2NTEzLCJleHAiOjE2Njc4MzgwNzZ9.-cfLvPrbDK1sxpJRCwQP0mk3LccZR4d6GY32FnvKhGvnHxsU4Kbg6iy42Idui68ywwdtnIHaz240a2EScwcJbA'
```
  ##### HappyPath - Response
```
200 OK
{
    "summaryStatistics": [
        {
            "department": "OPERATIONS",
            "subDepartment": "CUSTOMER_ONBOARDING",
            "min": 30,
            "max": 70000,
            "mean": 35015,
            "totalElements": 2
        },
        {
            "department": "ENGINEERING",
            "subDepartment": "PLATFORM",
            "min": 30,
            "max": 200000000,
            "mean": 40053466,
            "totalElements": 5
        },
        {
            "department": "ADMINISTRATION",
            "subDepartment": "AGRICULTURE",
            "min": 30,
            "max": 30,
            "mean": 30,
            "totalElements": 1
        },
        {
            "department": "BANKING",
            "subDepartment": "LOAN",
            "min": 90000,
            "max": 90000,
            "mean": 90000,
            "totalElements": 1
        },
        {
            "department": "BANKING",
            "subDepartment": "PLATFORM",
            "min": 240000,
            "max": 240000,
            "mean": 240000,
            "totalElements": 1
        }
    ]
}
```

##### Other Request Collections

[FetchRecord](https://github.com/amanagrawal3/record-aggregation-service/blob/main/collections/RecordSummaryStatistics.postman_collection.json)
