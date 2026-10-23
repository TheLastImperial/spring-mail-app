# Mail App

Create Thymeleaf template to send emails.

## Configuration

You need set a profile to user `Kafka` or `RabbitMQ` with the env var `spring.profiles.active`.

| Env var                                           | Description                           |
| ------------------------------------------------- | ------------------------------------- |
| `spring.profiles.active`                          | Active profiles, `kafka` or `rabbit`  |
| `com.thelastimperial.mail.server.address`         | Server URL                            |
| `com.thelastimperial.mail.mails.blockNotAllows`   | Activate block emails not allowed.    |
| Env vars for Kafka                                                                        |
| `spring.kafka.bootstrap-server`                   | Kafka server                          |
| `spring.kafka.consumer.group-id`                  | Group id for consumer                 |
| `spring.kafka.consumer.auto-offset-reset`         | To load kafka messages from beggining.|
| `spring.kafka.topic.name`                         | Topic name to send and receive data.  |
| Env vars for RabbitMQ                                                                     |
| `com.thelastimperial.mail.mq.queue`               | RabbitMQ Queue name                   |
| ----------------------------------------------------------------------------------------- |

## OAuth Client

Parameter to authenticate with OAuth Client, by default use the project [OAuthClient](https://github.com/TheLastImperial/spring-auth-module).

- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_CLIENTID
- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_CLIENTSECRET
- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_REDIRECTURI
- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_SCOPE
- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_AUTHORIZATIONGRANTTYPE
- SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_THELASTIMPERIAL_CLIENTAUTHENTICATIONMETHOD
- SPRING_SECURITY_OAUTH2_CLIENT_PROVIDER_THELASTIMPERIAL_ISSUERURI
