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
