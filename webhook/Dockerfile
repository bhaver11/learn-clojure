FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/webhook.jar /webhook/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/webhook/app.jar"]
