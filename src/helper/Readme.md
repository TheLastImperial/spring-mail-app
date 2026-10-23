# Helper moduler

This module is used to be reused on multiples modules that need to send mails with the mail app.

## Requests

- MailRequest: Data to send a mail.
- MailRequestWrapper: Wrapper to set extra data to MailRequest.

## Services

- ConsumeMailService: Interface to consume a MailRequest.
- ProduceMailService: Interface to produce a MailRequest.
