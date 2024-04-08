FROM openjdk:17
COPY ./target/Bella-Solutions-to-manage-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","Bella-Solutions-to-manage-0.0.1-SNAPSHOT.jar"]