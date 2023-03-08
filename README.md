
# Athlete Event Management

Application to manage Athletes of world athletic event for their events and the results. This application should support creation update Athlete information along with their respective events and individual results, while allowing the users to search the Athletes.

Here used SpringBoot Framework,Java and MySql for the backend development.


## Prerequisite

- Java Version(1.8.0)
- Apache Maven 3.8.4
- XAMPP(mysql port:3306)

## How to Use
1. Open your terminal and then add 

###  `git clone https://github.com/Charunirathnayake/athlete-event.git`

2. Start XAMPP Control Panel and start Apache and Mysql module.

3. `cd` into the new folder and type.

### `mvn clean install -DskipTests`

4. Input next command as

### `cd target`

5. Input next command as

### `java -jar athlete-event-0.0.1-SNAPSHOT.jar`

6. Populate tables

Refer Populate Tables section.
## Populate Tables

1. Populate Country List

After starting the XAMMP Control Panel go the mysql Admin and choose the athlete-event database and select country table and process the following script in sql tab.

`INSERT INTO `country` (`id`, `country_name`, `created_date`, `created_user`, `modified_date`, `modified_user`, `status`) VALUES
(1, 'Sri Lanka', '2023-03-05 23:35:58', 'Charuni Rathnayaka', NULL, NULL, NULL),
(2, 'India', '2023-03-05 23:36:09', 'Charuni Rathnayaka', NULL, NULL, NULL),
(3, 'England', '2023-03-05 23:36:20', 'Charuni Rathnayaka', NULL, NULL, NULL),
(4, 'USA', '2023-03-05 23:36:30', 'Charuni Rathnayaka', NULL, NULL, NULL),
(5, 'Canada', '2023-03-05 23:36:41', 'Charuni Rathnayaka', NULL, NULL, NULL),
(6, 'New Zealand', '2023-03-08 21:10:25', 'Charuni Rathnayaka', NULL, NULL, NULL),
(7, 'Australia', '2023-03-08 21:10:47', 'Charuni Rathnayaka', NULL, NULL, NULL),
(8, 'Germany', '2023-03-08 21:11:01', 'Charuni Rathnayaka', NULL, NULL, NULL),
(9, 'Netherlands', '2023-03-08 21:12:00', 'Charuni Rathnayaka', NULL, NULL, NULL),
(10, 'Romania', '2023-03-08 21:12:15', 'Charuni Rathnayaka', NULL, NULL, NULL),
(11, 'Russia', '2023-03-08 21:12:25', 'Charuni Rathnayaka', NULL, NULL, NULL),
(12, 'Ukraine', '2023-03-08 21:12:38', 'Charuni Rathnayaka', NULL, NULL, NULL),
(13, 'Spain', '2023-03-08 21:12:44', 'Charuni Rathnayaka', NULL, NULL, NULL),
(14, 'Italy', '2023-03-08 21:12:50', 'Charuni Rathnayaka', NULL, NULL, NULL);`

2. Populate Event List

After starting the XAMMP Control Panel go the mysql Admin and choose the athlete-event database and select event table and process the following script in sql tab.

`INSERT INTO `event` (`id`, `created_date`, `created_user`, `modified_date`, `modified_user`, `name`, `status`) VALUES
(1, '2023-03-05 23:43:56', 'Charuni Rathnayaka', NULL, NULL, '5000m', 'ACTIVE'),
(2, '2023-03-05 23:44:09', 'Charuni Rathnayaka', NULL, NULL, '100x4m', 'ACTIVE'),
(3, '2023-03-05 23:44:14', 'Charuni Rathnayaka', NULL, NULL, '100m', 'ACTIVE'),
(4, '2023-03-08 21:21:49', 'Charuni Rathnayaka', NULL, NULL, '200m', 'ACTIVE'),
(5, '2023-03-08 21:21:59', 'Charuni Rathnayaka', NULL, NULL, '400m', 'ACTIVE'),
(6, '2023-03-08 21:22:09', 'Charuni Rathnayaka', NULL, NULL, '400x4m', 'ACTIVE'),
(7, '2023-03-08 21:22:15', 'Charuni Rathnayaka', NULL, NULL, '200x4m', 'ACTIVE'),
(8, '2023-03-08 21:22:22', 'Charuni Rathnayaka', NULL, NULL, '800m', 'ACTIVE'),
(9, '2023-03-08 21:22:42', 'Charuni Rathnayaka', NULL, NULL, '1500m', 'ACTIVE'),
(10, '2023-03-08 21:23:13', 'Charuni Rathnayaka', NULL, NULL, '3000m', 'ACTIVE');`
## Features

- Athlete Creation
- Search Athlete with respective search parameters
- MVC architecture for REST API development.



## Libraries And Tools
- Swagger API Documentation Configured
    http://localhost:8080/swagger-ui/ 

## Folder Structure
athletic-app(root)
- src /main/java
    - controller
    - core 
    - domain
    - enums
    - exception
    - repository
    - service
    - serviceImpl
- resorces
## Conclusion
Used Java Springboot framework for REST API development.Swagger API documentation configured to get API documents with the models and REST endpoints.

Please configure the athlete-event project(SpringBoot Backend) and run firstly then confiure the athletic-app project(React Front end).