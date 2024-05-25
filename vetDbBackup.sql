PGDMP                      |            vet    15.6    16.2 O    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    19803    vet    DATABASE     x   CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE vet;
                postgres    false            �            1259    19806    animals    TABLE     �  CREATE TABLE public.animals (
    animal_id bigint NOT NULL,
    animal_birth_date date,
    animal_breed character varying(255) NOT NULL,
    animal_color character varying(255) NOT NULL,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    19805    animals_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.animals_animal_customer_id_seq;
       public          postgres    false    216            O           0    0    animals_animal_customer_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.animals_animal_customer_id_seq OWNED BY public.animals.animal_customer_id;
          public          postgres    false    215            �            1259    19804    animals_animal_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.animals_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    216            P           0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    214            �            1259    19818    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    appointment_animal_id integer NOT NULL,
    appointment_veterinarian_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    19816 &   appointments_appointment_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_animal_id_seq;
       public          postgres    false    220            Q           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_animal_id_seq OWNED BY public.appointments.appointment_animal_id;
          public          postgres    false    218            �            1259    19815    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    220            R           0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    217            �            1259    19817 ,   appointments_appointment_veterinarian_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_veterinarian_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.appointments_appointment_veterinarian_id_seq;
       public          postgres    false    220            S           0    0 ,   appointments_appointment_veterinarian_id_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.appointments_appointment_veterinarian_id_seq OWNED BY public.appointments.appointment_veterinarian_id;
          public          postgres    false    219            �            1259    19827    available_date    TABLE     g   CREATE TABLE public.available_date (
    available_date_id bigint NOT NULL,
    available_date date
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    19826 $   available_date_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.available_date_available_date_id_seq;
       public          postgres    false    222            T           0    0 $   available_date_available_date_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.available_date_available_date_id_seq OWNED BY public.available_date.available_date_id;
          public          postgres    false    221            �            1259    19834 	   customers    TABLE     =  CREATE TABLE public.customers (
    customer_id bigint NOT NULL,
    customer_address character varying(255) NOT NULL,
    customer_city character varying(255) NOT NULL,
    customer_mail character varying(255),
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    19833    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    224            U           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    223            �            1259    19844    vaccines    TABLE     0  CREATE TABLE public.vaccines (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255) NOT NULL,
    vaccine_name character varying(255) NOT NULL,
    vaccine_protection_finish_date date NOT NULL,
    vaccine_protection_start_date date NOT NULL,
    vaccine_animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    19843    vaccines_vaccine_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.vaccines_vaccine_animal_id_seq;
       public          postgres    false    227            V           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.vaccines_vaccine_animal_id_seq OWNED BY public.vaccines.vaccine_animal_id;
          public          postgres    false    226            �            1259    19842    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    227            W           0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    225            �            1259    19855    veterinarian2available_date    TABLE     �   CREATE TABLE public.veterinarian2available_date (
    veterinarian_id integer NOT NULL,
    available_date_id integer NOT NULL
);
 /   DROP TABLE public.veterinarian2available_date;
       public         heap    postgres    false            �            1259    19854 1   veterinarian2available_date_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.veterinarian2available_date_available_date_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 H   DROP SEQUENCE public.veterinarian2available_date_available_date_id_seq;
       public          postgres    false    230            X           0    0 1   veterinarian2available_date_available_date_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.veterinarian2available_date_available_date_id_seq OWNED BY public.veterinarian2available_date.available_date_id;
          public          postgres    false    229            �            1259    19853 /   veterinarian2available_date_veterinarian_id_seq    SEQUENCE     �   CREATE SEQUENCE public.veterinarian2available_date_veterinarian_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 F   DROP SEQUENCE public.veterinarian2available_date_veterinarian_id_seq;
       public          postgres    false    230            Y           0    0 /   veterinarian2available_date_veterinarian_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.veterinarian2available_date_veterinarian_id_seq OWNED BY public.veterinarian2available_date.veterinarian_id;
          public          postgres    false    228            �            1259    19863    veterinarians    TABLE     Y  CREATE TABLE public.veterinarians (
    veterinarian_id bigint NOT NULL,
    veterinarian_address character varying(255) NOT NULL,
    veterinarian_city character varying(255) NOT NULL,
    veterinarian_mail character varying(255),
    veterinarian_name character varying(255) NOT NULL,
    veterinarian_phone character varying(255) NOT NULL
);
 !   DROP TABLE public.veterinarians;
       public         heap    postgres    false            �            1259    19862 !   veterinarians_veterinarian_id_seq    SEQUENCE     �   CREATE SEQUENCE public.veterinarians_veterinarian_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.veterinarians_veterinarian_id_seq;
       public          postgres    false    232            Z           0    0 !   veterinarians_veterinarian_id_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.veterinarians_veterinarian_id_seq OWNED BY public.veterinarians.veterinarian_id;
          public          postgres    false    231            �           2604    19809    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    214    216    216            �           2604    19810    animals animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animals ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animals_animal_customer_id_seq'::regclass);
 I   ALTER TABLE public.animals ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    19821    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    217    220    220            �           2604    19822 "   appointments appointment_animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_animal_id SET DEFAULT nextval('public.appointments_appointment_animal_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_animal_id DROP DEFAULT;
       public          postgres    false    220    218    220            �           2604    19823 (   appointments appointment_veterinarian_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_veterinarian_id SET DEFAULT nextval('public.appointments_appointment_veterinarian_id_seq'::regclass);
 W   ALTER TABLE public.appointments ALTER COLUMN appointment_veterinarian_id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    19830     available_date available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_date ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_date_available_date_id_seq'::regclass);
 O   ALTER TABLE public.available_date ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    19837    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    19847    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    227    225    227            �           2604    19848    vaccines vaccine_animal_id    DEFAULT     �   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_animal_id SET DEFAULT nextval('public.vaccines_vaccine_animal_id_seq'::regclass);
 I   ALTER TABLE public.vaccines ALTER COLUMN vaccine_animal_id DROP DEFAULT;
       public          postgres    false    226    227    227            �           2604    19858 +   veterinarian2available_date veterinarian_id    DEFAULT     �   ALTER TABLE ONLY public.veterinarian2available_date ALTER COLUMN veterinarian_id SET DEFAULT nextval('public.veterinarian2available_date_veterinarian_id_seq'::regclass);
 Z   ALTER TABLE public.veterinarian2available_date ALTER COLUMN veterinarian_id DROP DEFAULT;
       public          postgres    false    230    228    230            �           2604    19859 -   veterinarian2available_date available_date_id    DEFAULT     �   ALTER TABLE ONLY public.veterinarian2available_date ALTER COLUMN available_date_id SET DEFAULT nextval('public.veterinarian2available_date_available_date_id_seq'::regclass);
 \   ALTER TABLE public.veterinarian2available_date ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    230    229    230            �           2604    19866    veterinarians veterinarian_id    DEFAULT     �   ALTER TABLE ONLY public.veterinarians ALTER COLUMN veterinarian_id SET DEFAULT nextval('public.veterinarians_veterinarian_id_seq'::regclass);
 L   ALTER TABLE public.veterinarians ALTER COLUMN veterinarian_id DROP DEFAULT;
       public          postgres    false    231    232    232            8          0    19806    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_birth_date, animal_breed, animal_color, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    216   �f       <          0    19818    appointments 
   TABLE DATA           |   COPY public.appointments (appointment_id, appointment_date, appointment_animal_id, appointment_veterinarian_id) FROM stdin;
    public          postgres    false    220   �g       >          0    19827    available_date 
   TABLE DATA           K   COPY public.available_date (available_date_id, available_date) FROM stdin;
    public          postgres    false    222   5h       @          0    19834 	   customers 
   TABLE DATA              COPY public.customers (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    224   �h       C          0    19844    vaccines 
   TABLE DATA           �   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_finish_date, vaccine_protection_start_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    227   �i       F          0    19855    veterinarian2available_date 
   TABLE DATA           Y   COPY public.veterinarian2available_date (veterinarian_id, available_date_id) FROM stdin;
    public          postgres    false    230   �j       H          0    19863    veterinarians 
   TABLE DATA           �   COPY public.veterinarians (veterinarian_id, veterinarian_address, veterinarian_city, veterinarian_mail, veterinarian_name, veterinarian_phone) FROM stdin;
    public          postgres    false    232   k       [           0    0    animals_animal_customer_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.animals_animal_customer_id_seq', 1, false);
          public          postgres    false    215            \           0    0    animals_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animals_animal_id_seq', 9, true);
          public          postgres    false    214            ]           0    0 &   appointments_appointment_animal_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_animal_id_seq', 1, false);
          public          postgres    false    218            ^           0    0    appointments_appointment_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 7, true);
          public          postgres    false    217            _           0    0 ,   appointments_appointment_veterinarian_id_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public.appointments_appointment_veterinarian_id_seq', 1, false);
          public          postgres    false    219            `           0    0 $   available_date_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_date_available_date_id_seq', 37, true);
          public          postgres    false    221            a           0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 7, true);
          public          postgres    false    223            b           0    0    vaccines_vaccine_animal_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.vaccines_vaccine_animal_id_seq', 1, false);
          public          postgres    false    226            c           0    0    vaccines_vaccine_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 11, true);
          public          postgres    false    225            d           0    0 1   veterinarian2available_date_available_date_id_seq    SEQUENCE SET     `   SELECT pg_catalog.setval('public.veterinarian2available_date_available_date_id_seq', 1, false);
          public          postgres    false    229            e           0    0 /   veterinarian2available_date_veterinarian_id_seq    SEQUENCE SET     ^   SELECT pg_catalog.setval('public.veterinarian2available_date_veterinarian_id_seq', 1, false);
          public          postgres    false    228            f           0    0 !   veterinarians_veterinarian_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.veterinarians_veterinarian_id_seq', 5, true);
          public          postgres    false    231            �           2606    19814    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    216            �           2606    19825    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    220            �           2606    19832 "   available_date available_date_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    222            �           2606    19841    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    224            �           2606    19852    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    227            �           2606    19861 <   veterinarian2available_date veterinarian2available_date_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.veterinarian2available_date
    ADD CONSTRAINT veterinarian2available_date_pkey PRIMARY KEY (veterinarian_id, available_date_id);
 f   ALTER TABLE ONLY public.veterinarian2available_date DROP CONSTRAINT veterinarian2available_date_pkey;
       public            postgres    false    230    230            �           2606    19870     veterinarians veterinarians_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.veterinarians
    ADD CONSTRAINT veterinarians_pkey PRIMARY KEY (veterinarian_id);
 J   ALTER TABLE ONLY public.veterinarians DROP CONSTRAINT veterinarians_pkey;
       public            postgres    false    232            �           2606    19881 (   appointments fk4jw8ykff9gibc7nu65lchbppo    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk4jw8ykff9gibc7nu65lchbppo FOREIGN KEY (appointment_veterinarian_id) REFERENCES public.veterinarians(veterinarian_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk4jw8ykff9gibc7nu65lchbppo;
       public          postgres    false    232    3233    220            �           2606    19896 7   veterinarian2available_date fk9emvhf6o9l3bnhldmx5i7tdmt    FK CONSTRAINT     �   ALTER TABLE ONLY public.veterinarian2available_date
    ADD CONSTRAINT fk9emvhf6o9l3bnhldmx5i7tdmt FOREIGN KEY (veterinarian_id) REFERENCES public.veterinarians(veterinarian_id);
 a   ALTER TABLE ONLY public.veterinarian2available_date DROP CONSTRAINT fk9emvhf6o9l3bnhldmx5i7tdmt;
       public          postgres    false    232    230    3233            �           2606    19886 $   vaccines fkekhfjmpsduds8nnilqe9b467v    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkekhfjmpsduds8nnilqe9b467v FOREIGN KEY (vaccine_animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkekhfjmpsduds8nnilqe9b467v;
       public          postgres    false    3221    216    227            �           2606    19891 7   veterinarian2available_date fkga79cc4m81ki49gtppqpy4bal    FK CONSTRAINT     �   ALTER TABLE ONLY public.veterinarian2available_date
    ADD CONSTRAINT fkga79cc4m81ki49gtppqpy4bal FOREIGN KEY (available_date_id) REFERENCES public.available_date(available_date_id);
 a   ALTER TABLE ONLY public.veterinarian2available_date DROP CONSTRAINT fkga79cc4m81ki49gtppqpy4bal;
       public          postgres    false    222    230    3225            �           2606    19871 #   animals fknjsvd8kplxqmf48ybxayrx6ru    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru FOREIGN KEY (animal_customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru;
       public          postgres    false    224    3227    216            �           2606    19876 (   appointments fko4t945rb708af9ry6syof7bwt    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fko4t945rb708af9ry6syof7bwt FOREIGN KEY (appointment_animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fko4t945rb708af9ry6syof7bwt;
       public          postgres    false    220    3221    216            8     x�M�Mn�0���S��l-٦�X�HQ˦R7�2
�����p�lr��{u( u3���y����*�����҂ Ƭ'G��«�)�gj:��Z�C3y<�Bi|H(����/���XBL�#<��Pj�'`�lk�"g�Ŏ��656����K��O��2<����8t��~5E��%y���-��BT)��7j�%FXjͮ�j:ݮ?+G �٫����a�1[8���|��~Zl�~�ԏ�`?�߲������!�/��m�      <   U   x�Uα� D��7E �!�d�9�a!]�_sS�X��W5&.�-�P����xJ���EGJT��<�`bKf�w��]c      >   �   x�Eѹ1���e�_�����;�d�_i�WW�BL�ԉ�AܚOT�"���y5�&"7�dS]5䢦ܩ%j˓�����Ŗ�8
���]��O����[��[���`�,�
KJ�%]��<UX~Wa9_�sVa9o�s��-�R�W�      @   �   x�]�=N�@���S��(xl:L�RP�`��ծ��z]�r	$����J�ν�!R�L�ޛ�>ͻ!�ƃQ.$P8��Y�nTu�kP�U�6��3<�『��Z c�ihJџ�����?��Yz���b�4�6mPF�$�A��Le$�[o�����p���⏩�-h��lt��� ��\2�ɓv%��b��j����^}.&%�\d�
?;���Vн��\\��aYn�P�/�8B@~ˁg�����f}j      C   �   x���1� ��w*ۀ�3t��D�ҵj����)d`��OOv�T�=�ϴ��NH�S�\�$K��Nǻ�����W����9K��%O�M��K'��\˄<�*��\$����S�^-u@0�Y�[ȾdK0������Y�      F   �   x�%���0���0=K"�d��?G��A��!���U��h��59�;j=�� A��^ip�n�j�(�@�@�@�@�@�@�
�F�(E�h����y0#f�Ԍ���6�����e�	
0��;^��>��D�z(�      H   �   x�u�Mj�0���)� !4���k��]x�U��IPaI�)ؗ��M/P�^��@M��7o`>ޛy�6����|��M�A�W�]�G�6MГ;�LW"��jo�Z��Q�݌RN^>?��C"�D���ќ��C;�q��;�Mt_�y�d���%� U!(#϶	����E�;[�;�џ�3�z򰍣u�O$BIXe����Ϙ]�����������X35� e�RIEߖ��/��y�     