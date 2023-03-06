
-- DROP TABLE IF EXISTS public.usuario;

CREATE TABLE IF NOT EXISTS public.usuario
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    ativo boolean NOT NULL,
    data_nascimento date NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    imagem_perfil character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    apelido character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT uk_5171l57faosmj8myawaucatdw UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to socialnet;


--------------------------------------------------------------

-- Table: public.permissao

-- DROP TABLE IF EXISTS public.permissao;

CREATE TABLE IF NOT EXISTS public.permissao
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    funcao character varying(255) COLLATE pg_catalog."default",
    usuario_id bigint,
    CONSTRAINT permissao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.permissao
    OWNER to socialnet;

--------------------------------------------------------------------

-- DROP TABLE IF EXISTS public.post;

CREATE TABLE IF NOT EXISTS public.post
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    titulo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    conteudo text COLLATE pg_catalog."default" NOT NULL,
    img_post_url character varying(255) COLLATE pg_catalog."default",
    usuario_id bigint NOT NULL,
    tipo character varying(25) COLLATE pg_catalog."default",
    data_inclusao date NOT NULL,
    CONSTRAINT post_pkey PRIMARY KEY (id),
    CONSTRAINT fk_post_usuario FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT tipo_check CHECK (tipo::text = ANY (ARRAY['PUBLICO'::character varying, 'PRIVADO'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.post
    OWNER to postgres;

-------------------------------------------------------------------------------------------

-- DROP TABLE IF EXISTS public.solicitacao_amizade;

CREATE TABLE IF NOT EXISTS public.solicitacao_amizade
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    remetente_id bigint NOT NULL,
    destinatario_id bigint NOT NULL,
    aceita boolean,
    CONSTRAINT solicitacao_amizade_pkey PRIMARY KEY (id),
    CONSTRAINT uk_remetente_destinatario UNIQUE (remetente_id, destinatario_id),
    CONSTRAINT uk_solicitacao_amizade UNIQUE (remetente_id, destinatario_id),
    CONSTRAINT fk_destinatario_id FOREIGN KEY (destinatario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_remetente_id FOREIGN KEY (remetente_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT chk_remetente_destinatario CHECK (remetente_id <> destinatario_id)
)

----------------------------------------------------------------------------------------

-- DROP TABLE IF EXISTS public.comentario;

CREATE TABLE IF NOT EXISTS public.comentario
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    conteudo text COLLATE pg_catalog."default" NOT NULL,
    post_id bigint NOT NULL,
    autor_comentario_id bigint NOT NULL,
    CONSTRAINT comentario_pkey PRIMARY KEY (id),
    CONSTRAINT fk_post_id FOREIGN KEY (post_id)
        REFERENCES public.post (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_usuario_id FOREIGN KEY (autor_comentario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

-----------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.amigo
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    usuario_id bigint NOT NULL,
    amigo_id bigint NOT NULL,
    CONSTRAINT amigo_pkey PRIMARY KEY (id),
    CONSTRAINT uk_usuario_amigo UNIQUE (usuario_id, amigo_id),
    CONSTRAINT fk_amigo_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_amigo_amigo_id FOREIGN KEY (amigo_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

---------------------------------------------------------------------------------------
