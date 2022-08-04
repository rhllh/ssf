# ssf-workshop13

A simple address book that saves to the local filesystem.

To run:
```
mvn spring-boot:run -Dspring-boot.run.arguments="--dataDir=<data directory>"
```

Pages:
1. Index: `http://localhost:8080/`
2. Contact Form: `http://localhost:8080/form`
3. Find by ID: `http://localhost:8080/contact/<id>`