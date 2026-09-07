CREATE TABLE public.mail_audits (
    id uuid NOT NULL,
    comment character varying(255),
    created_at timestamp(6) without time zone NOT NULL,
    is_sended boolean NOT NULL,
    mail_template_id character varying(255),
    send_by character varying(255),
    send_to character varying(255),
    updated_at timestamp(6) without time zone NOT NULL,
    action_id character varying(255)
);

CREATE TABLE public.mail_templates (
    id character varying(255) NOT NULL,
    content text,
    created_at timestamp(6) without time zone NOT NULL,
    description character varying(255),
    is_html boolean NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    subject character varying(255)
);

CREATE TABLE public.mail_audit_actions (
    id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    description character varying(255),
    updated_at timestamp(6) without time zone NOT NULL
);

CREATE TABLE public.mail_allow_audits (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    email character varying(255),
    is_allow boolean NOT NULL,
    updated_by uuid
);

CREATE TABLE public.mail_allows (
    id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    is_allow boolean NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL
);

CREATE TABLE public.mail_retries (
    id uuid NOT NULL,
    action_id character varying(255),
    comment character varying(255),
    created_at timestamp(6) without time zone NOT NULL,
    is_canceled boolean NOT NULL,
    is_retried boolean NOT NULL,
    params jsonb,
    send_to character varying(255),
    template_id character varying(255),
    updated_at timestamp(6) without time zone NOT NULL
);


ALTER TABLE ONLY public.mail_audits
    ADD CONSTRAINT mail_audits_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_templates
    ADD CONSTRAINT mail_templates_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_audit_actions
    ADD CONSTRAINT mail_audit_actions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_allow_audits
    ADD CONSTRAINT mail_allow_audits_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_allows
    ADD CONSTRAINT mail_allows_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_retries
    ADD CONSTRAINT mail_retries_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.mail_audits
    ADD CONSTRAINT fkgry581nm6w935f12bbx07v4os FOREIGN KEY (action_id)
    REFERENCES public.mail_audit_actions(id);
