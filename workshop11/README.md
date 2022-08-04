# ssf-workshop11

Create a basic SpringBoot application that serves static files.

To run locally without arguments:
1. Set PORT environment variable
2. In terminal, `mvn spring-boot:run`

To run locally with arguments:
```
mvn spring-boot:run -Dspring-boot.run.arguments="--port=<PORT NUMBER>"
```

TO-DO:
- Deploy to Heroku
- Set PORT key in config vars as DEFAULT_PORT