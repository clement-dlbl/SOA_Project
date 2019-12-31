# SOA_Project
5ISS - SOA Project

    Clément Delobel
    Mathilde Cornille
    Vincent Laurens


# OneM2M Architecture

An overview of services implemented
![OneM2M Architecture](img/SOAArchitecture1.png "OneM2M Architecture")

The oneM2M Architecture:
![OneM2M Architecture](img/SOAArchitecture2.png "OneM2M Architecture")

# Files project Hierarchy
```
SOA_Project
│   README.md
│   SOA_Requests_Collection.postman_collection.json
│
└───Alarm (idem for other microservices)
│   │
│   └───libs
|   |   obix.jar
│   │   
│   └───src
│   │
│   └───targets
|   |   Pom.xml
|       
│   
└───generateOneM2MArchi
    │   runOnIN.sh
    │   testArchi.sh
```
 


# How to deploy our infrastructure and application


- First Start the in-cse node of oneM2M
- Then git clone the project repository on your local machine
- Add right to script "runOnIN.sh" which is generateOneM2MArchi folder.
- Now you can check on https://localhost:8080/webpage and login with admin user

  You should see the same result as below
  
![OneM2M Architecture](img/resultatoneM2M.png "OneM2M Architecture")


=> Then open project on eclipse

- Now you can go on each directory on the workspace and execute "mvn package" in order to check dependencies

If you have an obix error then go on eclipse project add obix as external librairy. (the lib is located in folder libs "/SOA_project/{service}/libs")


Finally run a browser on your computer:
and open index.html file in UI folder to see front Application.

![Dashboards](img/SOAAPI.png "Dashbaords")
![Dashboards](img/APISOA1.png "Dashbaords2")

Here you go ! OUF 
You are ready to use our app !! 

Thank you and enjoy.


