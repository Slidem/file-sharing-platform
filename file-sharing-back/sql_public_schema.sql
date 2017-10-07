--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

-- Started on 2017-09-09 16:26:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 186 (class 1259 OID 17491)
-- Name: acc_sts_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE acc_sts_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 1;


ALTER TABLE acc_sts_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 17493)
-- Name: acc_stats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE acc_stats (
    id integer DEFAULT nextval('acc_sts_seq'::regclass) NOT NULL,
    type character varying(255) NOT NULL,
    status character varying(255) NOT NULL
);


ALTER TABLE acc_stats OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 17500)
-- Name: admin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE admin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE admin_seq OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17502)
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE admin (
    id integer DEFAULT nextval('admin_seq'::regclass) NOT NULL,
    username character varying(35)[] NOT NULL,
    fullname character varying(80) NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE admin OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 17509)
-- Name: directory_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE directory_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 1;


ALTER TABLE directory_seq OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 17511)
-- Name: directory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE directory (
    id integer DEFAULT nextval('directory_seq'::regclass) NOT NULL,
    name character varying NOT NULL,
    parent_id integer,
    user_id integer NOT NULL,
    path character varying NOT NULL
);


ALTER TABLE directory OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 17518)
-- Name: file_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE file_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 1;


ALTER TABLE file_seq OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 17520)
-- Name: file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE file (
    id integer DEFAULT nextval('file_seq'::regclass) NOT NULL,
    name character varying NOT NULL,
    parent_id integer NOT NULL,
    category_id integer NOT NULL,
    upload_time timestamp with time zone DEFAULT ('now'::text)::date NOT NULL,
    user_id integer NOT NULL,
    version integer NOT NULL,
    path character varying NOT NULL
);


ALTER TABLE file OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 17528)
-- Name: file_cat_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE file_cat_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999999
    CACHE 1;


ALTER TABLE file_cat_seq OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 17530)
-- Name: file_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE file_category (
    id integer DEFAULT nextval('file_cat_seq'::regclass) NOT NULL,
    extension character varying NOT NULL,
    category_name character varying NOT NULL
);


ALTER TABLE file_category OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 17537)
-- Name: file_ver_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE file_ver_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 1;


ALTER TABLE file_ver_seq OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17539)
-- Name: file_version; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE file_version (
    id integer DEFAULT nextval('file_ver_seq'::regclass) NOT NULL,
    id_file integer NOT NULL,
    name character varying NOT NULL,
    parent_id integer NOT NULL,
    category_id integer NOT NULL,
    upload_time timestamp without time zone NOT NULL,
    version integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE file_version OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17546)
-- Name: itemaction_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE itemaction_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999999
    CACHE 1;


ALTER TABLE itemaction_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17548)
-- Name: itemaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE item_action (
    id integer DEFAULT nextval('itemaction_seq'::regclass) NOT NULL,
    action_type character varying NOT NULL,
    action_time timestamp with time zone DEFAULT ('now'::text)::date NOT NULL,
    id_directory integer,
    id_file integer
);


ALTER TABLE itemaction OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17556)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE news (
    id integer NOT NULL,
    message text,
    title character varying(100)[] NOT NULL,
    picture bytea,
    id_admin integer NOT NULL
);


ALTER TABLE news OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17562)
-- Name: news_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE news_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999
    CACHE 1;


ALTER TABLE news_seq OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 17688)
-- Name: oauth_client_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE oauth_client_details (
    client_id character varying(256)[] NOT NULL,
    resource_ids character varying(256)[],
    client_secret character varying(256)[],
    scope character varying(256)[],
    authorized_grant_types character varying(256)[],
    web_server_redirect_uri character varying(256)[],
    authorities character varying(256)[],
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information character varying(256)[],
    autoaprove character varying(256)
);


ALTER TABLE oauth_client_details OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17696)
-- Name: oauth_client_details_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE oauth_client_details_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE oauth_client_details_seq OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17564)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles (
    id integer NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE roles OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17570)
-- Name: share_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE share_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999999
    CACHE 1;


ALTER TABLE share_seq OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17572)
-- Name: share; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE share (
    id integer DEFAULT nextval('share_seq'::regclass) NOT NULL,
    message character varying,
    id_file integer NOT NULL
);


ALTER TABLE share OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17579)
-- Name: user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999
    CACHE 1;


ALTER TABLE user_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17581)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "user" (
    email character varying(255) NOT NULL,
    id integer DEFAULT nextval('user_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    picture bytea,
    surname character varying(255) NOT NULL,
    id_accstats integer NOT NULL,
    password character varying(100) NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17588)
-- Name: user_share; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_share (
    id integer NOT NULL,
    id_user integer NOT NULL,
    id_share integer NOT NULL
);


ALTER TABLE user_share OWNER TO postgres;

--
-- TOC entry 2094 (class 2606 OID 17592)
-- Name: acc_stats acc_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY acc_stats
    ADD CONSTRAINT acc_stats_pkey PRIMARY KEY (id);


--
-- TOC entry 2096 (class 2606 OID 17594)
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- TOC entry 2102 (class 2606 OID 17596)
-- Name: file_category category_name_idx_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file_category
    ADD CONSTRAINT category_name_idx_unique UNIQUE (category_name);


--
-- TOC entry 2098 (class 2606 OID 17598)
-- Name: directory directory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY directory
    ADD CONSTRAINT directory_pkey PRIMARY KEY (id);


--
-- TOC entry 2104 (class 2606 OID 17600)
-- Name: file_category file_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file_category
    ADD CONSTRAINT file_category_pkey PRIMARY KEY (id);


--
-- TOC entry 2100 (class 2606 OID 17602)
-- Name: file file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file
    ADD CONSTRAINT file_pkey PRIMARY KEY (id);


--
-- TOC entry 2106 (class 2606 OID 17604)
-- Name: itemaction itemactiom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemaction
    ADD CONSTRAINT itemactiom_pkey PRIMARY KEY (id);


--
-- TOC entry 2108 (class 2606 OID 17606)
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- TOC entry 2120 (class 2606 OID 17695)
-- Name: oauth_client_details oauth_client_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY oauth_client_details
    ADD CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id);


--
-- TOC entry 2110 (class 2606 OID 17608)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2112 (class 2606 OID 17610)
-- Name: share share_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY share
    ADD CONSTRAINT share_pkey PRIMARY KEY (id);


--
-- TOC entry 2114 (class 2606 OID 17612)
-- Name: user unq_usr_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT unq_usr_email UNIQUE (email);


--
-- TOC entry 2116 (class 2606 OID 17614)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2118 (class 2606 OID 17616)
-- Name: user_share user_share_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_share
    ADD CONSTRAINT user_share_pkey PRIMARY KEY (id);


--
-- TOC entry 2122 (class 2606 OID 17617)
-- Name: file directory_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file
    ADD CONSTRAINT directory_fk FOREIGN KEY (parent) REFERENCES directory(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2123 (class 2606 OID 17622)
-- Name: file file_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file
    ADD CONSTRAINT file_cat_fk FOREIGN KEY (category) REFERENCES file_category(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2125 (class 2606 OID 17627)
-- Name: file_version file_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file_version
    ADD CONSTRAINT file_fk FOREIGN KEY (id_file) REFERENCES file(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2130 (class 2606 OID 17632)
-- Name: share file_share_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY share
    ADD CONSTRAINT file_share_fk FOREIGN KEY (id_file) REFERENCES file(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2127 (class 2606 OID 17637)
-- Name: itemaction fk_directory; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemaction
    ADD CONSTRAINT fk_directory FOREIGN KEY (id_directory) REFERENCES directory(id);


--
-- TOC entry 2128 (class 2606 OID 17642)
-- Name: itemaction fk_file; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itemaction
    ADD CONSTRAINT fk_file FOREIGN KEY (id_file) REFERENCES file(id);


--
-- TOC entry 2129 (class 2606 OID 17647)
-- Name: news news_admin_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_admin_fk FOREIGN KEY (id_admin) REFERENCES admin(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2131 (class 2606 OID 17652)
-- Name: user role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT role_fk FOREIGN KEY (id_role) REFERENCES roles(id);


--
-- TOC entry 2133 (class 2606 OID 17657)
-- Name: user_share share_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_share
    ADD CONSTRAINT share_fk FOREIGN KEY (id_share) REFERENCES share(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2132 (class 2606 OID 17662)
-- Name: user user_accsts_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_accsts_fk FOREIGN KEY (id_accstats) REFERENCES acc_stats(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2134 (class 2606 OID 17667)
-- Name: user_share user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_share
    ADD CONSTRAINT user_fk FOREIGN KEY (id_user) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2124 (class 2606 OID 17672)
-- Name: file user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE;


--
-- TOC entry 2126 (class 2606 OID 17677)
-- Name: file_version user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY file_version
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2121 (class 2606 OID 17682)
-- Name: directory user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY directory
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user"(id);


-- Completed on 2017-09-09 16:26:33

--
-- PostgreSQL database dump complete
--

