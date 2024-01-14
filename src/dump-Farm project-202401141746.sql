--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-01-14 17:46:22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4884 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 17158)
-- Name: crops; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.crops (
    crop_id integer NOT NULL,
    crop_name character varying(50),
    planting_date date,
    harvest_date date,
    quantity integer
);


ALTER TABLE public.crops OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17157)
-- Name: crops_crop_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.crops_crop_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.crops_crop_id_seq OWNER TO postgres;

--
-- TOC entry 4885 (class 0 OID 0)
-- Dependencies: 217
-- Name: crops_crop_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.crops_crop_id_seq OWNED BY public.crops.crop_id;


--
-- TOC entry 230 (class 1259 OID 17273)
-- Name: equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipment (
    equipment_id integer NOT NULL,
    role character varying(20),
    farmer_id integer
);


ALTER TABLE public.equipment OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17272)
-- Name: equipment_equipment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipment_equipment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.equipment_equipment_id_seq OWNER TO postgres;

--
-- TOC entry 4886 (class 0 OID 0)
-- Dependencies: 229
-- Name: equipment_equipment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.equipment_equipment_id_seq OWNED BY public.equipment.equipment_id;


--
-- TOC entry 224 (class 1259 OID 17237)
-- Name: expenses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.expenses (
    expense_id integer NOT NULL,
    farmer_id integer,
    expense_type character varying(50),
    amount numeric(10,2),
    expense_date date
);


ALTER TABLE public.expenses OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17236)
-- Name: expenses_expense_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.expenses_expense_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.expenses_expense_id_seq OWNER TO postgres;

--
-- TOC entry 4887 (class 0 OID 0)
-- Dependencies: 223
-- Name: expenses_expense_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.expenses_expense_id_seq OWNED BY public.expenses.expense_id;


--
-- TOC entry 216 (class 1259 OID 17151)
-- Name: farmers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.farmers (
    farmer_id integer NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    contact_number character varying(15),
    address character varying(100),
    username character varying NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE public.farmers OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17150)
-- Name: farmers_farmer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.farmers_farmer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.farmers_farmer_id_seq OWNER TO postgres;

--
-- TOC entry 4888 (class 0 OID 0)
-- Dependencies: 215
-- Name: farmers_farmer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.farmers_farmer_id_seq OWNED BY public.farmers.farmer_id;


--
-- TOC entry 222 (class 1259 OID 17196)
-- Name: harvest; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.harvest (
    harvest_id integer NOT NULL,
    crop_id integer,
    farmer_id integer,
    quantity_harvested integer,
    date_harvested date
);


ALTER TABLE public.harvest OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17195)
-- Name: harvest_harvest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.harvest_harvest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.harvest_harvest_id_seq OWNER TO postgres;

--
-- TOC entry 4889 (class 0 OID 0)
-- Dependencies: 221
-- Name: harvest_harvest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.harvest_harvest_id_seq OWNED BY public.harvest.harvest_id;


--
-- TOC entry 226 (class 1259 OID 17249)
-- Name: income; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.income (
    income_id integer NOT NULL,
    farmer_id integer,
    income_type character varying(50),
    amount numeric(10,2),
    income_date date
);


ALTER TABLE public.income OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17248)
-- Name: income_income_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.income_income_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.income_income_id_seq OWNER TO postgres;

--
-- TOC entry 4890 (class 0 OID 0)
-- Dependencies: 225
-- Name: income_income_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.income_income_id_seq OWNED BY public.income.income_id;


--
-- TOC entry 228 (class 1259 OID 17261)
-- Name: livestock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livestock (
    livestock_id integer NOT NULL,
    livestock_name character varying(50),
    type character varying(50),
    quantity integer,
    farmer_id integer,
    CONSTRAINT check_livestock_type CHECK (((type)::text = ANY ((ARRAY['livestock'::character varying, 'Livestock'::character varying, 'Poultry'::character varying, 'poultry'::character varying, 'Equines'::character varying, 'equines'::character varying, 'Misc'::character varying, 'misc'::character varying])::text[])))
);


ALTER TABLE public.livestock OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17260)
-- Name: livestock_livestock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livestock_livestock_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livestock_livestock_id_seq OWNER TO postgres;

--
-- TOC entry 4891 (class 0 OID 0)
-- Dependencies: 227
-- Name: livestock_livestock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livestock_livestock_id_seq OWNED BY public.livestock.livestock_id;


--
-- TOC entry 231 (class 1259 OID 17284)
-- Name: manual_equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.manual_equipment (
    manual_id integer NOT NULL,
    equipment_name character varying(50)
);


ALTER TABLE public.manual_equipment OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17295)
-- Name: mechanized_equipment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mechanized_equipment (
    mechanized_id integer NOT NULL,
    equipment_name character varying(50),
    fuel_consumption numeric(5,2),
    fuel_type character varying(20)
);


ALTER TABLE public.mechanized_equipment OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17179)
-- Name: planting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planting (
    planting_id integer NOT NULL,
    crop_id integer,
    farmer_id integer,
    acreage integer,
    date_planted date
);


ALTER TABLE public.planting OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17178)
-- Name: planting_planting_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planting_planting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.planting_planting_id_seq OWNER TO postgres;

--
-- TOC entry 4892 (class 0 OID 0)
-- Dependencies: 219
-- Name: planting_planting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planting_planting_id_seq OWNED BY public.planting.planting_id;


--
-- TOC entry 4678 (class 2604 OID 17161)
-- Name: crops crop_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.crops ALTER COLUMN crop_id SET DEFAULT nextval('public.crops_crop_id_seq'::regclass);


--
-- TOC entry 4684 (class 2604 OID 17276)
-- Name: equipment equipment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment ALTER COLUMN equipment_id SET DEFAULT nextval('public.equipment_equipment_id_seq'::regclass);


--
-- TOC entry 4681 (class 2604 OID 17240)
-- Name: expenses expense_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.expenses ALTER COLUMN expense_id SET DEFAULT nextval('public.expenses_expense_id_seq'::regclass);


--
-- TOC entry 4677 (class 2604 OID 17154)
-- Name: farmers farmer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.farmers ALTER COLUMN farmer_id SET DEFAULT nextval('public.farmers_farmer_id_seq'::regclass);


--
-- TOC entry 4680 (class 2604 OID 17199)
-- Name: harvest harvest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest ALTER COLUMN harvest_id SET DEFAULT nextval('public.harvest_harvest_id_seq'::regclass);


--
-- TOC entry 4682 (class 2604 OID 17252)
-- Name: income income_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.income ALTER COLUMN income_id SET DEFAULT nextval('public.income_income_id_seq'::regclass);


--
-- TOC entry 4683 (class 2604 OID 17264)
-- Name: livestock livestock_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livestock ALTER COLUMN livestock_id SET DEFAULT nextval('public.livestock_livestock_id_seq'::regclass);


--
-- TOC entry 4679 (class 2604 OID 17182)
-- Name: planting planting_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planting ALTER COLUMN planting_id SET DEFAULT nextval('public.planting_planting_id_seq'::regclass);


--
-- TOC entry 4864 (class 0 OID 17158)
-- Dependencies: 218
-- Data for Name: crops; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.crops (crop_id, crop_name, planting_date, harvest_date, quantity) FROM stdin;
\.


--
-- TOC entry 4876 (class 0 OID 17273)
-- Dependencies: 230
-- Data for Name: equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipment (equipment_id, role, farmer_id) FROM stdin;
\.


--
-- TOC entry 4870 (class 0 OID 17237)
-- Dependencies: 224
-- Data for Name: expenses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.expenses (expense_id, farmer_id, expense_type, amount, expense_date) FROM stdin;
\.


--
-- TOC entry 4862 (class 0 OID 17151)
-- Dependencies: 216
-- Data for Name: farmers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.farmers (farmer_id, first_name, last_name, contact_number, address, username, password) FROM stdin;
1	Gheorghe	Marius	076666666	No	Ghe	orghe
2	Bob	Vasile	072132435	Yes	Bob	Bob
3	Codrin	Bradea	076969696	Hell	Codrin	Bradea
4	Echo	Tudor	023325450	There	Echo	Jordan
\.


--
-- TOC entry 4868 (class 0 OID 17196)
-- Dependencies: 222
-- Data for Name: harvest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.harvest (harvest_id, crop_id, farmer_id, quantity_harvested, date_harvested) FROM stdin;
\.


--
-- TOC entry 4872 (class 0 OID 17249)
-- Dependencies: 226
-- Data for Name: income; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.income (income_id, farmer_id, income_type, amount, income_date) FROM stdin;
\.


--
-- TOC entry 4874 (class 0 OID 17261)
-- Dependencies: 228
-- Data for Name: livestock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livestock (livestock_id, livestock_name, type, quantity, farmer_id) FROM stdin;
8	Pigs	Livestock	500	4
10	Goats	Livestock	20	1
7	Cow	Livestock	20	1
9	Pets	Equines	200	3
11	Chickens	Equines	15	1
12	Apes	Misc	15	1
6	Pig	Livestock	191	1
\.


--
-- TOC entry 4877 (class 0 OID 17284)
-- Dependencies: 231
-- Data for Name: manual_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.manual_equipment (manual_id, equipment_name) FROM stdin;
\.


--
-- TOC entry 4878 (class 0 OID 17295)
-- Dependencies: 232
-- Data for Name: mechanized_equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mechanized_equipment (mechanized_id, equipment_name, fuel_consumption, fuel_type) FROM stdin;
\.


--
-- TOC entry 4866 (class 0 OID 17179)
-- Dependencies: 220
-- Data for Name: planting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planting (planting_id, crop_id, farmer_id, acreage, date_planted) FROM stdin;
\.


--
-- TOC entry 4893 (class 0 OID 0)
-- Dependencies: 217
-- Name: crops_crop_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.crops_crop_id_seq', 1, false);


--
-- TOC entry 4894 (class 0 OID 0)
-- Dependencies: 229
-- Name: equipment_equipment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipment_equipment_id_seq', 1, false);


--
-- TOC entry 4895 (class 0 OID 0)
-- Dependencies: 223
-- Name: expenses_expense_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.expenses_expense_id_seq', 1, false);


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 215
-- Name: farmers_farmer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.farmers_farmer_id_seq', 4, true);


--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 221
-- Name: harvest_harvest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.harvest_harvest_id_seq', 1, false);


--
-- TOC entry 4898 (class 0 OID 0)
-- Dependencies: 225
-- Name: income_income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.income_income_id_seq', 1, false);


--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 227
-- Name: livestock_livestock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livestock_livestock_id_seq', 12, true);


--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 219
-- Name: planting_planting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planting_planting_id_seq', 1, false);


--
-- TOC entry 4691 (class 2606 OID 17163)
-- Name: crops crops_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.crops
    ADD CONSTRAINT crops_pkey PRIMARY KEY (crop_id);


--
-- TOC entry 4703 (class 2606 OID 17278)
-- Name: equipment equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (equipment_id);


--
-- TOC entry 4697 (class 2606 OID 17242)
-- Name: expenses expenses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.expenses
    ADD CONSTRAINT expenses_pkey PRIMARY KEY (expense_id);


--
-- TOC entry 4687 (class 2606 OID 17156)
-- Name: farmers farmers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.farmers
    ADD CONSTRAINT farmers_pkey PRIMARY KEY (farmer_id);


--
-- TOC entry 4689 (class 2606 OID 17309)
-- Name: farmers farmers_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.farmers
    ADD CONSTRAINT farmers_un UNIQUE (username);


--
-- TOC entry 4695 (class 2606 OID 17201)
-- Name: harvest harvest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_pkey PRIMARY KEY (harvest_id);


--
-- TOC entry 4699 (class 2606 OID 17254)
-- Name: income income_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (income_id);


--
-- TOC entry 4701 (class 2606 OID 17266)
-- Name: livestock livestock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livestock
    ADD CONSTRAINT livestock_pkey PRIMARY KEY (livestock_id);


--
-- TOC entry 4705 (class 2606 OID 17288)
-- Name: manual_equipment manual_equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manual_equipment
    ADD CONSTRAINT manual_equipment_pkey PRIMARY KEY (manual_id);


--
-- TOC entry 4707 (class 2606 OID 17299)
-- Name: mechanized_equipment mechanized_equipment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mechanized_equipment
    ADD CONSTRAINT mechanized_equipment_pkey PRIMARY KEY (mechanized_id);


--
-- TOC entry 4693 (class 2606 OID 17184)
-- Name: planting planting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_pkey PRIMARY KEY (planting_id);


--
-- TOC entry 4715 (class 2606 OID 17279)
-- Name: equipment equipment_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


--
-- TOC entry 4712 (class 2606 OID 17243)
-- Name: expenses expenses_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.expenses
    ADD CONSTRAINT expenses_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


--
-- TOC entry 4710 (class 2606 OID 17202)
-- Name: harvest harvest_crop_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_crop_id_fkey FOREIGN KEY (crop_id) REFERENCES public.crops(crop_id);


--
-- TOC entry 4711 (class 2606 OID 17207)
-- Name: harvest harvest_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


--
-- TOC entry 4713 (class 2606 OID 17255)
-- Name: income income_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


--
-- TOC entry 4714 (class 2606 OID 17267)
-- Name: livestock livestock_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livestock
    ADD CONSTRAINT livestock_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


--
-- TOC entry 4716 (class 2606 OID 17289)
-- Name: manual_equipment manual_equipment_manual_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manual_equipment
    ADD CONSTRAINT manual_equipment_manual_id_fkey FOREIGN KEY (manual_id) REFERENCES public.equipment(equipment_id);


--
-- TOC entry 4717 (class 2606 OID 17300)
-- Name: mechanized_equipment mechanized_equipment_mechanized_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mechanized_equipment
    ADD CONSTRAINT mechanized_equipment_mechanized_id_fkey FOREIGN KEY (mechanized_id) REFERENCES public.equipment(equipment_id);


--
-- TOC entry 4708 (class 2606 OID 17185)
-- Name: planting planting_crop_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_crop_id_fkey FOREIGN KEY (crop_id) REFERENCES public.crops(crop_id);


--
-- TOC entry 4709 (class 2606 OID 17190)
-- Name: planting planting_farmer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);


-- Completed on 2024-01-14 17:46:22

--
-- PostgreSQL database dump complete
--

