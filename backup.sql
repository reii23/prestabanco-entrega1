--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0 (Debian 17.0-1.pgdg120+1)
-- Dumped by pg_dump version 17.0 (Debian 17.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id bigint NOT NULL,
    age integer NOT NULL,
    email character varying(255),
    name character varying(255),
    rut character varying(255),
    salary bigint
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clients_id_seq OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: credit_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit_request (
    id_credit_request bigint NOT NULL,
    business_plan_pdf oid,
    client_id bigint NOT NULL,
    credit_history_pdf oid,
    expenses bigint,
    financial_state_business_pdf oid,
    first_property_deed_pdf oid,
    income_proof_pdf oid,
    interest_rate double precision,
    loan_type character varying(255),
    loan_type_id bigint NOT NULL,
    property_valuation_pdf oid,
    r1payment_to_income boolean,
    r2credit_history boolean,
    r3employment_stability boolean,
    r4debt_to_income boolean,
    r5max_financing boolean,
    r6age_restriction boolean,
    r71minimum_balance boolean,
    r72consistent_savings_history boolean,
    r73periodic_deposits boolean,
    r74balance_years_ratio boolean,
    r75recent_withdrawals boolean,
    r7savings_capacity character varying(255),
    renovation_budget_pdf oid,
    requested_amount double precision,
    status character varying(255),
    term_years integer NOT NULL
);


ALTER TABLE public.credit_request OWNER TO postgres;

--
-- Name: credit_request_id_credit_request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.credit_request_id_credit_request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.credit_request_id_credit_request_seq OWNER TO postgres;

--
-- Name: credit_request_id_credit_request_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.credit_request_id_credit_request_seq OWNED BY public.credit_request.id_credit_request;


--
-- Name: credit_simulation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit_simulation (
    id_credit_simulation bigint NOT NULL,
    interest_rate real NOT NULL,
    loan_amount bigint,
    loan_type integer NOT NULL,
    term_years integer NOT NULL
);


ALTER TABLE public.credit_simulation OWNER TO postgres;

--
-- Name: credit_simulation_id_credit_simulation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.credit_simulation_id_credit_simulation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.credit_simulation_id_credit_simulation_seq OWNER TO postgres;

--
-- Name: credit_simulation_id_credit_simulation_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.credit_simulation_id_credit_simulation_seq OWNED BY public.credit_simulation.id_credit_simulation;


--
-- Name: loan_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loan_type (
    id bigint NOT NULL,
    max_financing_percentage double precision NOT NULL,
    max_interest_rate double precision NOT NULL,
    max_term_years integer NOT NULL,
    min_interest_rate double precision NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.loan_type OWNER TO postgres;

--
-- Name: loan_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.loan_type_id_seq OWNER TO postgres;

--
-- Name: loan_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.loan_type_id_seq OWNED BY public.loan_type.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: credit_request id_credit_request; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_request ALTER COLUMN id_credit_request SET DEFAULT nextval('public.credit_request_id_credit_request_seq'::regclass);


--
-- Name: credit_simulation id_credit_simulation; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_simulation ALTER COLUMN id_credit_simulation SET DEFAULT nextval('public.credit_simulation_id_credit_simulation_seq'::regclass);


--
-- Name: loan_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_type ALTER COLUMN id SET DEFAULT nextval('public.loan_type_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, age, email, name, rut, salary) FROM stdin;
1	41	alan.turing@usach.cl	Alan Turing	12345678-9	123456
\.


--
-- Data for Name: credit_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit_request (id_credit_request, business_plan_pdf, client_id, credit_history_pdf, expenses, financial_state_business_pdf, first_property_deed_pdf, income_proof_pdf, interest_rate, loan_type, loan_type_id, property_valuation_pdf, r1payment_to_income, r2credit_history, r3employment_stability, r4debt_to_income, r5max_financing, r6age_restriction, r71minimum_balance, r72consistent_savings_history, r73periodic_deposits, r74balance_years_ratio, r75recent_withdrawals, r7savings_capacity, renovation_budget_pdf, requested_amount, status, term_years) FROM stdin;
\.


--
-- Data for Name: credit_simulation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit_simulation (id_credit_simulation, interest_rate, loan_amount, loan_type, term_years) FROM stdin;
\.


--
-- Data for Name: loan_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.loan_type (id, max_financing_percentage, max_interest_rate, max_term_years, min_interest_rate, name) FROM stdin;
1	80	5	30	3.5	Primera Vivienda
2	70	6	20	4	Segunda Vivienda
3	60	7	25	5	Propiedades Comerciales
4	50	6	15	4.5	Remodelación
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_seq', 1, true);


--
-- Name: credit_request_id_credit_request_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_request_id_credit_request_seq', 1, false);


--
-- Name: credit_simulation_id_credit_simulation_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_simulation_id_credit_simulation_seq', 1, false);


--
-- Name: loan_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_type_id_seq', 4, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: credit_request credit_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_request
    ADD CONSTRAINT credit_request_pkey PRIMARY KEY (id_credit_request);


--
-- Name: credit_simulation credit_simulation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_simulation
    ADD CONSTRAINT credit_simulation_pkey PRIMARY KEY (id_credit_simulation);


--
-- Name: loan_type loan_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_type
    ADD CONSTRAINT loan_type_pkey PRIMARY KEY (id);


--
-- Name: loan_type uk_7f2c7vastpb0le3mb3i6i1dlg; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan_type
    ADD CONSTRAINT uk_7f2c7vastpb0le3mb3i6i1dlg UNIQUE (name);


--
-- PostgreSQL database dump complete
--

