# account

## Requirements
- JDK 8
- Maven 3.x

## Setup

Go inside the folder:
```bash
cd account/
```

Run the application:
```bash
mvn clean install spring-boot:run
```

Open your browser an go to http://localhost:8080/swagger-ui.html to see all operations.

## API methods

### Get Account Statement

```bash
curl -X GET --header 'Content-Type: application/json' --header 'Accept: */*' 'http://localhost:8080/accounts/4/statement'
```
H2 DB is used
