/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     13/04/2014 14:08:08                          */
/*==============================================================*/


alter table JADWAL
   drop constraint FK_JADWAL_BERDASARK_KARYA_AK;

alter table JADWAL
   drop constraint FK_JADWAL_DILAKSANA_RUANGAN;

alter table JADWAL
   drop constraint FK_JADWAL_MEMILIKI_PERIODE;

alter table JADWAL
   drop constraint FK_JADWAL_MEMPUNYAI_SLOT_WAK;

alter table JADWAL_KULIAH
   drop constraint FK_JADWAL_K_MEMAKAI_R_RUANGAN;

alter table JADWAL_KULIAH
   drop constraint FK_JADWAL_K_MEMILIKI__PERIODE_;

alter table KARYA_AKHIR
   drop constraint FK_KARYA_AK_MELAKUKAN_MAHASISW;

alter table KARYA_AKHIR
   drop constraint FK_KARYA_AK_TERDIRI_D_TOPIK;

alter table KETERSEDIAAN_WAKTU_DOSEN
   drop constraint FK_KETERSED_RELATIONS_DOSEN;

alter table KETERSEDIAAN_WAKTU_DOSEN
   drop constraint FK_KETERSED_RELATIONS_SLOT_WAK;

alter table MEMBIMBING
   drop constraint FK_MEMBIMBI_MEMBIMBIN_DOSEN;

alter table MEMBIMBING
   drop constraint FK_MEMBIMBI_MEMBIMBIN_KARYA_AK;

alter table MENGAJAR
   drop constraint FK_MENGAJAR_MENGAJAR_JADWAL_K;

alter table MENGAJAR
   drop constraint FK_MENGAJAR_MENGAJAR2_DOSEN;

alter table MENGUJI
   drop constraint FK_MENGUJI_MENGUJI_JADWAL;

alter table MENGUJI
   drop constraint FK_MENGUJI_MENGUJI2_DOSEN;

alter table REFERENCE
   drop constraint FK_REFERENC_REFERENCE_TOPIK;

alter table REFERENCE
   drop constraint FK_REFERENC_REFERENCE_DOSEN;

drop table DOSEN cascade constraints;

drop table JADWAL cascade constraints;

drop index MEMAKAI_RUANGAN_FK;

drop table JADWAL_KULIAH cascade constraints;

drop table KARYA_AKHIR cascade constraints;

drop table KETERSEDIAAN_WAKTU_DOSEN cascade constraints;

drop table MAHASISWA cascade constraints;

drop table MEMBIMBING cascade constraints;

drop table MENGAJAR cascade constraints;

drop table PERIODE cascade constraints;

drop table PERIODE_KULIAH cascade constraints;

drop table REFERENCE cascade constraints;

drop table RUANGAN cascade constraints;

drop table SLOT_WAKTU cascade constraints;

drop table TOPIK cascade constraints;

/*==============================================================*/
/* Table: DOSEN                                                 */
/*==============================================================*/
create table DOSEN  (
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   NAMA_DOSEN           VARCHAR2(50),
   STATUS               VARCHAR2(10),
   GELAR_DEPAN          VARCHAR2(30),
   GELAR_BELAKANG       VARCHAR2(30),
   constraint PK_DOSEN primary key (INISIAL_DOSEN)
);

/*==============================================================*/
/* Table: JADWAL                                                */
/*==============================================================*/
create table JADWAL  (
   ID_JADWAL            NUMBER(5)                       not null,
   KD_RUANGAN           CHAR(4),
   ID_SLOT              NUMBER(5)                       not null,
   ID_PERIODE           NUMBER(5)                       not null,
   ID_KA                NUMBER(5)                       not null,
   TANGGAL              DATE,
   STATUS_PELAKSANAAN   SMALLINT						DEFAULT 0,
   STATUS_HASIL_PELAKSANAAN SMALLINT					DEFAULT 0,
   GENERATE_DATE		TIMESTAMP						not null,
   constraint PK_JADWAL primary key (ID_JADWAL)
);

/*==============================================================*/
/* Table: JADWAL_KULIAH                                         */
/*==============================================================*/
create table JADWAL_KULIAH  (
   ID_JADWAL_KULIAH     NUMBER(5)                       not null,
   KD_RUANGAN           CHAR(4)                         not null,
   ID_PERIODE_KULIAH    NUMBER(5)                       not null,
   KODE_MATA_KULIAH     CHAR(6)                         not null,
   HARI                 CHAR(3),
   WAKTU_MASUK          TIMESTAMP,
   WAKTU_KELUAR         TIMESTAMP,
   constraint PK_JADWAL_KULIAH primary key (ID_JADWAL_KULIAH)
);

/*==============================================================*/
/* Index: MEMAKAI_RUANGAN_FK                                    */
/*==============================================================*/
create index MEMAKAI_RUANGAN_FK on JADWAL_KULIAH (
   KD_RUANGAN ASC
);

/*==============================================================*/
/* Table: KARYA_AKHIR                                           */
/*==============================================================*/
create table KARYA_AKHIR  (
   ID_KA                NUMBER(5)                       not null,
   ID_TOPIK             NUMBER(8),
   NIM                  CHAR(8)                         not null,
   JUDUL_KA             VARCHAR2(150)                   not null,
   STATUS_KA            CHAR(1),
   constraint PK_KARYA_AKHIR primary key (ID_KA)
);

/*==============================================================*/
/* Table: KETERSEDIAAN_WAKTU_DOSEN                              */
/*==============================================================*/
create table KETERSEDIAAN_WAKTU_DOSEN  (
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   ID_SLOT              NUMBER(5)                       not null,
   TANGGAL_DSN_SEDIA    DATE,
   constraint PK_KETERSEDIAAN_WAKTU_DOSEN primary key (INISIAL_DOSEN, ID_SLOT)
);

/*==============================================================*/
/* Table: MAHASISWA                                             */
/*==============================================================*/
create table MAHASISWA  (
   NIM                  CHAR(8)                         not null,
   NAMA_MHS             VARCHAR2(50),
   JENJANG              CHAR(2),
   constraint PK_MAHASISWA primary key (NIM)
);

/*==============================================================*/
/* Table: MEMBIMBING                                            */
/*==============================================================*/
create table MEMBIMBING  (
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   ID_KA                NUMBER(5)                       not null,
   constraint PK_MEMBIMBING primary key (INISIAL_DOSEN, ID_KA)
);

/*==============================================================*/
/* Table: MENGAJAR                                              */
/*==============================================================*/
create table MENGAJAR  (
   ID_JADWAL_KULIAH     NUMBER(5)                       not null,
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   constraint PK_MENGAJAR primary key (ID_JADWAL_KULIAH, INISIAL_DOSEN)
);

/*==============================================================*/
/* Table: MENGUJI                                               */
/*==============================================================*/
create table MENGUJI  (
   ID_JADWAL            NUMBER(5)                       not null,
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   constraint PK_MENGUJI primary key (ID_JADWAL, INISIAL_DOSEN)
);

/*==============================================================*/
/* Table: PERIODE                                               */
/*==============================================================*/
create table PERIODE  (
   ID_PERIODE           NUMBER(5)                       not null,
   NAMA_PERIODE         VARCHAR2(50)                    not null,
   PERIODE_AWAL         DATE                            not null,
   PERIODE_AKHIR        DATE                            not null,
   TIPE_JADWAL          CHAR(1),
   STATUS_VERIFIKASI    SMALLINT                       DEFAULT 0,
   STATUS_RILIS         SMALLINT                       DEFAULT 0,
   STATUS_JADWAL        CHAR(1)                        DEFAULT '0',
   constraint PK_PERIODE primary key (ID_PERIODE)
);

/*==============================================================*/
/* Table: PERIODE_KULIAH                                        */
/*==============================================================*/
create table PERIODE_KULIAH  (
   ID_PERIODE_KULIAH    NUMBER(5)                       not null,
   WAKTU_MULAI_KULIAH   DATE,
   WAKTU_AKHIR_KULIAH   DATE,
   VERSI                DATE,
   TAHUN_AKADEMIK       CHAR(9),
   SEMESTER             NUMBER(1),
   constraint PK_PERIODE_KULIAH primary key (ID_PERIODE_KULIAH)
);

/*==============================================================*/
/* Table: REFERENCE                                             */
/*==============================================================*/
create table REFERENCE  (
   ID_TOPIK             NUMBER(8)                       not null,
   INISIAL_DOSEN        VARCHAR2(3)                     not null,
   constraint PK_REFERENCE primary key (ID_TOPIK, INISIAL_DOSEN)
);

/*==============================================================*/
/* Table: RUANGAN                                               */
/*==============================================================*/
create table RUANGAN  (
   KD_RUANGAN           CHAR(4)                         not null,
   NAMA_RUANGAN         VARCHAR2(5),
   constraint PK_RUANGAN primary key (KD_RUANGAN)
);

/*==============================================================*/
/* Table: SLOT_WAKTU                                            */
/*==============================================================*/
create table SLOT_WAKTU  (
   ID_SLOT              NUMBER(5)                       not null,
   SESI                 NUMBER(1),
   JAM_AWAL             TIMESTAMP,
   JAM_AKHIR            TIMESTAMP,
   constraint PK_SLOT_WAKTU primary key (ID_SLOT)
);

/*==============================================================*/
/* Table: TOPIK                                                 */
/*==============================================================*/
create table TOPIK  (
   ID_TOPIK             NUMBER(8)                       not null,
   NAMA_TOPIK           VARCHAR2(30),
   BIDANG               CHAR(10),
   constraint PK_TOPIK primary key (ID_TOPIK)
);

alter table JADWAL
   add constraint FK_JADWAL_BERDASARK_KARYA_AK foreign key (ID_KA)
      references KARYA_AKHIR (ID_KA);

alter table JADWAL
   add constraint FK_JADWAL_DILAKSANA_RUANGAN foreign key (KD_RUANGAN)
      references RUANGAN (KD_RUANGAN);

alter table JADWAL
   add constraint FK_JADWAL_MEMILIKI_PERIODE foreign key (ID_PERIODE)
      references PERIODE (ID_PERIODE);

alter table JADWAL
   add constraint FK_JADWAL_MEMPUNYAI_SLOT_WAK foreign key (ID_SLOT)
      references SLOT_WAKTU (ID_SLOT);

alter table JADWAL_KULIAH
   add constraint FK_JADWAL_K_MEMAKAI_R_RUANGAN foreign key (KD_RUANGAN)
      references RUANGAN (KD_RUANGAN);

alter table JADWAL_KULIAH
   add constraint FK_JADWAL_K_MEMILIKI__PERIODE_ foreign key (ID_PERIODE_KULIAH)
      references PERIODE_KULIAH (ID_PERIODE_KULIAH);

alter table KARYA_AKHIR
   add constraint FK_KARYA_AK_MELAKUKAN_MAHASISW foreign key (NIM)
      references MAHASISWA (NIM);

alter table KARYA_AKHIR
   add constraint FK_KARYA_AK_TERDIRI_D_TOPIK foreign key (ID_TOPIK)
      references TOPIK (ID_TOPIK);

alter table KETERSEDIAAN_WAKTU_DOSEN
   add constraint FK_KETERSED_RELATIONS_DOSEN foreign key (INISIAL_DOSEN)
      references DOSEN (INISIAL_DOSEN);

alter table KETERSEDIAAN_WAKTU_DOSEN
   add constraint FK_KETERSED_RELATIONS_SLOT_WAK foreign key (ID_SLOT)
      references SLOT_WAKTU (ID_SLOT);

alter table MEMBIMBING
   add constraint FK_MEMBIMBI_MEMBIMBIN_DOSEN foreign key (INISIAL_DOSEN)
      references DOSEN (INISIAL_DOSEN);

alter table MEMBIMBING
   add constraint FK_MEMBIMBI_MEMBIMBIN_KARYA_AK foreign key (ID_KA)
      references KARYA_AKHIR (ID_KA);

alter table MENGAJAR
   add constraint FK_MENGAJAR_MENGAJAR_JADWAL_K foreign key (ID_JADWAL_KULIAH)
      references JADWAL_KULIAH (ID_JADWAL_KULIAH);

alter table MENGAJAR
   add constraint FK_MENGAJAR_MENGAJAR2_DOSEN foreign key (INISIAL_DOSEN)
      references DOSEN (INISIAL_DOSEN);

alter table MENGUJI
   add constraint FK_MENGUJI_MENGUJI_JADWAL foreign key (ID_JADWAL)
      references JADWAL (ID_JADWAL);

alter table MENGUJI
   add constraint FK_MENGUJI_MENGUJI2_DOSEN foreign key (INISIAL_DOSEN)
      references DOSEN (INISIAL_DOSEN);

alter table REFERENCE
   add constraint FK_REFERENC_REFERENCE_TOPIK foreign key (ID_TOPIK)
      references TOPIK (ID_TOPIK);

alter table REFERENCE
   add constraint FK_REFERENC_REFERENCE_DOSEN foreign key (INISIAL_DOSEN)
      references DOSEN (INISIAL_DOSEN);

