PGDMP  $    8                 |            Farm project    16.0    16.0 O               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17149    Farm project    DATABASE     �   CREATE DATABASE "Farm project" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "Farm project";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false                       0    0 
   SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    17158    crops    TABLE     �   CREATE TABLE public.crops (
    crop_id integer NOT NULL,
    crop_name character varying(50),
    planting_date date,
    harvest_date date,
    quantity integer
);
    DROP TABLE public.crops;
       public         heap    postgres    false    4            �            1259    17157    crops_crop_id_seq    SEQUENCE     �   CREATE SEQUENCE public.crops_crop_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.crops_crop_id_seq;
       public          postgres    false    218    4                       0    0    crops_crop_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.crops_crop_id_seq OWNED BY public.crops.crop_id;
          public          postgres    false    217            �            1259    17273 	   equipment    TABLE     |   CREATE TABLE public.equipment (
    equipment_id integer NOT NULL,
    role character varying(20),
    farmer_id integer
);
    DROP TABLE public.equipment;
       public         heap    postgres    false    4            �            1259    17272    equipment_equipment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.equipment_equipment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.equipment_equipment_id_seq;
       public          postgres    false    230    4                       0    0    equipment_equipment_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.equipment_equipment_id_seq OWNED BY public.equipment.equipment_id;
          public          postgres    false    229            �            1259    17237    expenses    TABLE     �   CREATE TABLE public.expenses (
    expense_id integer NOT NULL,
    farmer_id integer,
    expense_type character varying(50),
    amount numeric(10,2),
    expense_date date
);
    DROP TABLE public.expenses;
       public         heap    postgres    false    4            �            1259    17236    expenses_expense_id_seq    SEQUENCE     �   CREATE SEQUENCE public.expenses_expense_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.expenses_expense_id_seq;
       public          postgres    false    224    4                       0    0    expenses_expense_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.expenses_expense_id_seq OWNED BY public.expenses.expense_id;
          public          postgres    false    223            �            1259    17151    farmers    TABLE     +  CREATE TABLE public.farmers (
    farmer_id integer NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    contact_number character varying(15),
    address character varying(100),
    username character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public.farmers;
       public         heap    postgres    false    4            �            1259    17150    farmers_farmer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.farmers_farmer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.farmers_farmer_id_seq;
       public          postgres    false    4    216                       0    0    farmers_farmer_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.farmers_farmer_id_seq OWNED BY public.farmers.farmer_id;
          public          postgres    false    215            �            1259    17196    harvest    TABLE     �   CREATE TABLE public.harvest (
    harvest_id integer NOT NULL,
    crop_id integer,
    farmer_id integer,
    quantity_harvested integer,
    date_harvested date
);
    DROP TABLE public.harvest;
       public         heap    postgres    false    4            �            1259    17195    harvest_harvest_id_seq    SEQUENCE     �   CREATE SEQUENCE public.harvest_harvest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.harvest_harvest_id_seq;
       public          postgres    false    4    222                       0    0    harvest_harvest_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.harvest_harvest_id_seq OWNED BY public.harvest.harvest_id;
          public          postgres    false    221            �            1259    17249    income    TABLE     �   CREATE TABLE public.income (
    income_id integer NOT NULL,
    farmer_id integer,
    income_type character varying(50),
    amount numeric(10,2),
    income_date date
);
    DROP TABLE public.income;
       public         heap    postgres    false    4            �            1259    17248    income_income_id_seq    SEQUENCE     �   CREATE SEQUENCE public.income_income_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.income_income_id_seq;
       public          postgres    false    226    4                       0    0    income_income_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.income_income_id_seq OWNED BY public.income.income_id;
          public          postgres    false    225            �            1259    17261 	   livestock    TABLE     �  CREATE TABLE public.livestock (
    livestock_id integer NOT NULL,
    livestock_name character varying(50),
    type character varying(50),
    quantity integer,
    farmer_id integer,
    CONSTRAINT check_livestock_type CHECK (((type)::text = ANY ((ARRAY['livestock'::character varying, 'Livestock'::character varying, 'Poultry'::character varying, 'poultry'::character varying, 'Equines'::character varying, 'equines'::character varying, 'Misc'::character varying, 'misc'::character varying])::text[])))
);
    DROP TABLE public.livestock;
       public         heap    postgres    false    4            �            1259    17260    livestock_livestock_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livestock_livestock_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.livestock_livestock_id_seq;
       public          postgres    false    228    4                       0    0    livestock_livestock_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.livestock_livestock_id_seq OWNED BY public.livestock.livestock_id;
          public          postgres    false    227            �            1259    17284    manual_equipment    TABLE     s   CREATE TABLE public.manual_equipment (
    manual_id integer NOT NULL,
    equipment_name character varying(50)
);
 $   DROP TABLE public.manual_equipment;
       public         heap    postgres    false    4            �            1259    17295    mechanized_equipment    TABLE     �   CREATE TABLE public.mechanized_equipment (
    mechanized_id integer NOT NULL,
    equipment_name character varying(50),
    fuel_consumption numeric(5,2),
    fuel_type character varying(20)
);
 (   DROP TABLE public.mechanized_equipment;
       public         heap    postgres    false    4            �            1259    17179    planting    TABLE     �   CREATE TABLE public.planting (
    planting_id integer NOT NULL,
    crop_id integer,
    farmer_id integer,
    acreage integer,
    date_planted date
);
    DROP TABLE public.planting;
       public         heap    postgres    false    4            �            1259    17178    planting_planting_id_seq    SEQUENCE     �   CREATE SEQUENCE public.planting_planting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.planting_planting_id_seq;
       public          postgres    false    220    4                       0    0    planting_planting_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.planting_planting_id_seq OWNED BY public.planting.planting_id;
          public          postgres    false    219            F           2604    17161 
   crops crop_id    DEFAULT     n   ALTER TABLE ONLY public.crops ALTER COLUMN crop_id SET DEFAULT nextval('public.crops_crop_id_seq'::regclass);
 <   ALTER TABLE public.crops ALTER COLUMN crop_id DROP DEFAULT;
       public          postgres    false    218    217    218            L           2604    17276    equipment equipment_id    DEFAULT     �   ALTER TABLE ONLY public.equipment ALTER COLUMN equipment_id SET DEFAULT nextval('public.equipment_equipment_id_seq'::regclass);
 E   ALTER TABLE public.equipment ALTER COLUMN equipment_id DROP DEFAULT;
       public          postgres    false    230    229    230            I           2604    17240    expenses expense_id    DEFAULT     z   ALTER TABLE ONLY public.expenses ALTER COLUMN expense_id SET DEFAULT nextval('public.expenses_expense_id_seq'::regclass);
 B   ALTER TABLE public.expenses ALTER COLUMN expense_id DROP DEFAULT;
       public          postgres    false    223    224    224            E           2604    17154    farmers farmer_id    DEFAULT     v   ALTER TABLE ONLY public.farmers ALTER COLUMN farmer_id SET DEFAULT nextval('public.farmers_farmer_id_seq'::regclass);
 @   ALTER TABLE public.farmers ALTER COLUMN farmer_id DROP DEFAULT;
       public          postgres    false    215    216    216            H           2604    17199    harvest harvest_id    DEFAULT     x   ALTER TABLE ONLY public.harvest ALTER COLUMN harvest_id SET DEFAULT nextval('public.harvest_harvest_id_seq'::regclass);
 A   ALTER TABLE public.harvest ALTER COLUMN harvest_id DROP DEFAULT;
       public          postgres    false    222    221    222            J           2604    17252    income income_id    DEFAULT     t   ALTER TABLE ONLY public.income ALTER COLUMN income_id SET DEFAULT nextval('public.income_income_id_seq'::regclass);
 ?   ALTER TABLE public.income ALTER COLUMN income_id DROP DEFAULT;
       public          postgres    false    226    225    226            K           2604    17264    livestock livestock_id    DEFAULT     �   ALTER TABLE ONLY public.livestock ALTER COLUMN livestock_id SET DEFAULT nextval('public.livestock_livestock_id_seq'::regclass);
 E   ALTER TABLE public.livestock ALTER COLUMN livestock_id DROP DEFAULT;
       public          postgres    false    227    228    228            G           2604    17182    planting planting_id    DEFAULT     |   ALTER TABLE ONLY public.planting ALTER COLUMN planting_id SET DEFAULT nextval('public.planting_planting_id_seq'::regclass);
 C   ALTER TABLE public.planting ALTER COLUMN planting_id DROP DEFAULT;
       public          postgres    false    220    219    220                       0    17158    crops 
   TABLE DATA           Z   COPY public.crops (crop_id, crop_name, planting_date, harvest_date, quantity) FROM stdin;
    public          postgres    false    218   �^                 0    17273 	   equipment 
   TABLE DATA           B   COPY public.equipment (equipment_id, role, farmer_id) FROM stdin;
    public          postgres    false    230   �^                 0    17237    expenses 
   TABLE DATA           ]   COPY public.expenses (expense_id, farmer_id, expense_type, amount, expense_date) FROM stdin;
    public          postgres    false    224   �^       �          0    17151    farmers 
   TABLE DATA           p   COPY public.farmers (farmer_id, first_name, last_name, contact_number, address, username, password) FROM stdin;
    public          postgres    false    216   _                 0    17196    harvest 
   TABLE DATA           e   COPY public.harvest (harvest_id, crop_id, farmer_id, quantity_harvested, date_harvested) FROM stdin;
    public          postgres    false    222   �_                 0    17249    income 
   TABLE DATA           X   COPY public.income (income_id, farmer_id, income_type, amount, income_date) FROM stdin;
    public          postgres    false    226   �_       
          0    17261 	   livestock 
   TABLE DATA           \   COPY public.livestock (livestock_id, livestock_name, type, quantity, farmer_id) FROM stdin;
    public          postgres    false    228   �_       
          0    17284    manual_equipment 
   TABLE DATA           E   COPY public.manual_equipment (manual_id, equipment_name) FROM stdin;
    public          postgres    false    231   ^`                 0    17295    mechanized_equipment 
   TABLE DATA           j   COPY public.mechanized_equipment (mechanized_id, equipment_name, fuel_consumption, fuel_type) FROM stdin;
    public          postgres    false    232   {`                 0    17179    planting 
   TABLE DATA           Z   COPY public.planting (planting_id, crop_id, farmer_id, acreage, date_planted) FROM stdin;
    public          postgres    false    220   �`                  0    0    crops_crop_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.crops_crop_id_seq', 1, false);
          public          postgres    false    217                       0    0    equipment_equipment_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.equipment_equipment_id_seq', 1, false);
          public          postgres    false    229                        0    0    expenses_expense_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.expenses_expense_id_seq', 1, false);
          public          postgres    false    223            !           0    0    farmers_farmer_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.farmers_farmer_id_seq', 4, true);
          public          postgres    false    215            "           0    0    harvest_harvest_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.harvest_harvest_id_seq', 1, false);
          public          postgres    false    221            #           0    0    income_income_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.income_income_id_seq', 1, false);
          public          postgres    false    225            $           0    0    livestock_livestock_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.livestock_livestock_id_seq', 12, true);
          public          postgres    false    227            %           0    0    planting_planting_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.planting_planting_id_seq', 1, false);
          public          postgres    false    219            S           2606    17163    crops crops_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.crops
    ADD CONSTRAINT crops_pkey PRIMARY KEY (crop_id);
 :   ALTER TABLE ONLY public.crops DROP CONSTRAINT crops_pkey;
       public            postgres    false    218            _           2606    17278    equipment equipment_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_pkey PRIMARY KEY (equipment_id);
 B   ALTER TABLE ONLY public.equipment DROP CONSTRAINT equipment_pkey;
       public            postgres    false    230            Y           2606    17242    expenses expenses_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.expenses
    ADD CONSTRAINT expenses_pkey PRIMARY KEY (expense_id);
 @   ALTER TABLE ONLY public.expenses DROP CONSTRAINT expenses_pkey;
       public            postgres    false    224            O           2606    17156    farmers farmers_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.farmers
    ADD CONSTRAINT farmers_pkey PRIMARY KEY (farmer_id);
 >   ALTER TABLE ONLY public.farmers DROP CONSTRAINT farmers_pkey;
       public            postgres    false    216            Q           2606    17309    farmers farmers_un 
   CONSTRAINT     Q   ALTER TABLE ONLY public.farmers
    ADD CONSTRAINT farmers_un UNIQUE (username);
 <   ALTER TABLE ONLY public.farmers DROP CONSTRAINT farmers_un;
       public            postgres    false    216            W           2606    17201    harvest harvest_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_pkey PRIMARY KEY (harvest_id);
 >   ALTER TABLE ONLY public.harvest DROP CONSTRAINT harvest_pkey;
       public            postgres    false    222            [           2606    17254    income income_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (income_id);
 <   ALTER TABLE ONLY public.income DROP CONSTRAINT income_pkey;
       public            postgres    false    226            ]           2606    17266    livestock livestock_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.livestock
    ADD CONSTRAINT livestock_pkey PRIMARY KEY (livestock_id);
 B   ALTER TABLE ONLY public.livestock DROP CONSTRAINT livestock_pkey;
       public            postgres    false    228            a           2606    17288 &   manual_equipment manual_equipment_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.manual_equipment
    ADD CONSTRAINT manual_equipment_pkey PRIMARY KEY (manual_id);
 P   ALTER TABLE ONLY public.manual_equipment DROP CONSTRAINT manual_equipment_pkey;
       public            postgres    false    231            c           2606    17299 .   mechanized_equipment mechanized_equipment_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.mechanized_equipment
    ADD CONSTRAINT mechanized_equipment_pkey PRIMARY KEY (mechanized_id);
 X   ALTER TABLE ONLY public.mechanized_equipment DROP CONSTRAINT mechanized_equipment_pkey;
       public            postgres    false    232            U           2606    17184    planting planting_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_pkey PRIMARY KEY (planting_id);
 @   ALTER TABLE ONLY public.planting DROP CONSTRAINT planting_pkey;
       public            postgres    false    220            k           2606    17279 "   equipment equipment_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.equipment
    ADD CONSTRAINT equipment_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 L   ALTER TABLE ONLY public.equipment DROP CONSTRAINT equipment_farmer_id_fkey;
       public          postgres    false    216    230    4687            h           2606    17243     expenses expenses_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.expenses
    ADD CONSTRAINT expenses_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 J   ALTER TABLE ONLY public.expenses DROP CONSTRAINT expenses_farmer_id_fkey;
       public          postgres    false    224    4687    216            f           2606    17202    harvest harvest_crop_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_crop_id_fkey FOREIGN KEY (crop_id) REFERENCES public.crops(crop_id);
 F   ALTER TABLE ONLY public.harvest DROP CONSTRAINT harvest_crop_id_fkey;
       public          postgres    false    222    218    4691            g           2606    17207    harvest harvest_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.harvest
    ADD CONSTRAINT harvest_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 H   ALTER TABLE ONLY public.harvest DROP CONSTRAINT harvest_farmer_id_fkey;
       public          postgres    false    216    4687    222            i           2606    17255    income income_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 F   ALTER TABLE ONLY public.income DROP CONSTRAINT income_farmer_id_fkey;
       public          postgres    false    226    4687    216            j           2606    17267 "   livestock livestock_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.livestock
    ADD CONSTRAINT livestock_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 L   ALTER TABLE ONLY public.livestock DROP CONSTRAINT livestock_farmer_id_fkey;
       public          postgres    false    4687    216    228            l           2606    17289 0   manual_equipment manual_equipment_manual_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.manual_equipment
    ADD CONSTRAINT manual_equipment_manual_id_fkey FOREIGN KEY (manual_id) REFERENCES public.equipment(equipment_id);
 Z   ALTER TABLE ONLY public.manual_equipment DROP CONSTRAINT manual_equipment_manual_id_fkey;
       public          postgres    false    230    231    4703            m           2606    17300 <   mechanized_equipment mechanized_equipment_mechanized_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.mechanized_equipment
    ADD CONSTRAINT mechanized_equipment_mechanized_id_fkey FOREIGN KEY (mechanized_id) REFERENCES public.equipment(equipment_id);
 f   ALTER TABLE ONLY public.mechanized_equipment DROP CONSTRAINT mechanized_equipment_mechanized_id_fkey;
       public          postgres    false    4703    230    232            d           2606    17185    planting planting_crop_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_crop_id_fkey FOREIGN KEY (crop_id) REFERENCES public.crops(crop_id);
 H   ALTER TABLE ONLY public.planting DROP CONSTRAINT planting_crop_id_fkey;
       public          postgres    false    4691    220    218            e           2606    17190     planting planting_farmer_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.planting
    ADD CONSTRAINT planting_farmer_id_fkey FOREIGN KEY (farmer_id) REFERENCES public.farmers(farmer_id);
 J   ALTER TABLE ONLY public.planting DROP CONSTRAINT planting_farmer_id_fkey;
       public          postgres    false    220    4687    216                
   x������ � �         
   x������ � �         
   x������ � �      �   �   x�U��
�0����H�4�׊T=A��b������s��m�'�&�-h�*�a�� V%�N�x����8�:�'���I�����a�t\������I�a��0�9�[o0$V��*�H�� V7.B         
   x������ � �         
   x������ � �      
   r   x����L/���,K-.�O��450�4�24�t�O,A�02�4�2�t�/G��H*u-,��K-�psr:gd&g��!dM��
�8����d���
H�ZEc���� ��*�      
   
   x������ � �         
   x������ � �         
   x������ � �     