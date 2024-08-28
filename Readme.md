# Account balance
[Design document](https://docs.google.com/document/d/1Az7GhaMJqP4Qaodi8x3GuKyGeSWOH1sXHMtdczRwlYQ/edit)

### Build steps
- Install maven builder for windows, from [link](https://phoenixnap.com/kb/install-maven-windows).
- Run command `mvn clean package` which will run all the unit tests followed by creating .jar file in path ```/target/account_balance-0.0.1-SNAPSHOT.jar```.

### Running build
After following above build steps you will have .jar file for said service.
To run .jar file you must have ```java>=17``` installed in your system.
- Navigate to ```/target```.
- Run command `java -jar account_balance-0.0.1-SNAPSHOT.jar`.

### Docker setup reference
- [Media link](https://medium.com/lseg-developer-community/how-to-deploy-and-run-real-time-java-application-with-maven-in-docker-d89c3a3053a9)