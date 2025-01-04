# CourtVision

The NBA regular season is spans across 7 months and over 1200 games. For NBA Fantasy team owners, this means staying on top of your game day-in and day-out. CourtVision gives you an all-in-one place to keep up-to-date with players on your teams.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Dependencies](#dependencies)

## Features

- See all players available in NBA Fantasy
- Search for specific players
- Add players to your watchlist
- Create and manage custom fantasy teams

## Installation
### Prerequisites
- Java 21
- Maven
- PostgreSQL

### Local Setup
1. Clone the repository:
```
git clone https://github.com/fullstackkg/courtvision-java-server.git
```

2. Create a PostgreSQL database named "courtvision_db"

3. Configure application.properties in `src/main/resources`:
```
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/courtvision_db
spring.r2dbc.username=your_username
spring.r2dbc.password=your_password
```

## Dependencies

The project uses Spring Boot 3.4 and is built with Maven. Key dependencies include:

### Core Dependencies
- Spring Boot WebFlux: Reactive web framework
- Spring Data R2DBC: Reactive database connectivity
- Spring Data Redis (Access+Driver): Advanced and thread-safe Java Redis client for synchronous, asynchronous, and reactive usage.
- PostgreSQL & R2DBC PostgreSQL: Database and its reactive driver
- Project Lombok: Reduces boilerplate code
- Validation: Bean Validation with Hibernate validator

### Development Dependencies
- Spring Boot DevTools: Development-time features
- Spring Boot Test: Testing framework
- Spring Boot Actuator: Supports built in endpoints that let you monitor and manage your application
- Reactor Test: Testing reactive streams

To install all dependencies, simply run:
```
mvn clean install
```

## Running the Application
### Development Mode
Ensure PostgreSQL service is running and from your project directory run:
```
mvn spring-boot:run
```
Application runs on http://localhost:8080

### Production Build
Create JAR
```
mvn clean package
```
Run JAR
```
java -jar target/courtvision-server-0.0.1-SNAPSHOT.jar
```

## API Documentation
The API follows RESTful conventions. Base URL: http://localhost:8080/api/v1

#### Players
- GET /players?page={page}&players_per_page={players_per_page} - List all players
- GET /players/categories?number_of_players={number_of_players}&category={category} - Show players by category leaders
- GET /players/search?keyword={keyword} - Search by player name 
- GET /players/{playerId} - Get single player 
  details

#### Teams
- GET /teams - List all teams
- GET /teams/{teamId}/players - Get all players on a team

