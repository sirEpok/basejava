CREATE TABLE public.resume
(
    uuid character(36) COLLATE pg_catalog."default" NOT NULL,
    full_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT resume_pkey PRIMARY KEY (uuid)
)

    TABLESPACE pg_default;

ALTER TABLE public.resume
    OWNER to postgres;

CREATE TABLE public.contact
(
    id integer NOT NULL DEFAULT nextval('contact_id_seq'::regclass),
    resume_uuid character(36) COLLATE pg_catalog."default" NOT NULL,
    type text COLLATE pg_catalog."default" NOT NULL,
    value text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT contact_resume_uuid_fkey FOREIGN KEY (resume_uuid)
        REFERENCES public.resume (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE public.contact
    OWNER to postgres;
-- Index: contact_uuid_type_index

-- DROP INDEX public.contact_uuid_type_index;

CREATE UNIQUE INDEX contact_uuid_type_index
    ON public.contact USING btree
    (resume_uuid COLLATE pg_catalog."default" ASC NULLS LAST, type COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;