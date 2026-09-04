INSERT INTO mail_audit_actions(id, description, created_at, updated_at)
VALUES
('EMAIL_SEND', 'The email was send without problem.', NOW(), NOW()),
('TEMPLATE_NOT_FOUND', 'Email not send, the template id doesnt exists', NOW(), NOW()),
('TEMPLATE_ERROR', 'Email not send, the template engine cant process the content string.',
    NOW(), NOW()
),
('EMAIL_NOT_ALLOW', 'Email not send, email configured to dont send mail.', NOW(), NOW());

INSERT INTO mail_templates (
    id, created_at, description, is_html, updated_at, content, subject
)
VALUES
('ACTIVATE_ACCOUNT', NOW(), 'Email template to activate a new account.', false, NOW(),
'<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Account activation</title>
</head>
<body>
    <h1>Hello !!!</h1>
    <p>
        Use the next link to activate your account.
    </p>
    <a th:href="$url}">Activate</a>
    <p>Or copy and paste on your blowser</p>
    <p th:text="${url}"></p>
</body>
</html>', 'Activation Account.'),
---------------------------------------------------------------------------
('RECOVERY_ACCOUNT', NOW(), 'Email template to reset password.', false, NOW(),
'<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Password recovery</title>
</head>
<body>
    <h1>Hello, <span th:text="${username}">User</span>!</h1>
    <p>
        Use the next link to restart your password.
    </p>
    <a th:href="${url}">Recovery</a>
    <p>Or copy and paste on your blowser</p>
    <p th:text="${url}"></p>
</body>
</html>
', 'Recovery account.');
