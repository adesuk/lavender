/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     11/05/2014 12:29:16                          */
/*==============================================================*/


alter table HAK_AKSES
   drop constraint FK_HAK_AKSE_HAK_AKSES_ROLE;

alter table HAK_AKSES
   drop constraint FK_HAK_AKSE_HAK_AKSES_UI;

alter table UI
   drop constraint FK_UI_MEMBER_ME_MENU;

alter table USERS
   drop constraint FK_USERS_HAS_ROLE_ROLE;

drop index ROLE_HAKAKSES2_FK;

drop index ROLE_HAKAKSES_FK;

drop table HAK_AKSES cascade constraints;

drop table MENU cascade constraints;

drop table ROLE cascade constraints;

drop index MEMBER_MENU_FK;

drop table UI cascade constraints;

drop index HAS_ROLE_FK;

drop table USERS cascade constraints;

/*==============================================================*/
/* Table: HAK_AKSES                                             */
/*==============================================================*/
create table HAK_AKSES  (
   ID_ROLE              VARCHAR2(30)                    not null,
   ID_UI                CHAR(4)                         not null,
   constraint PK_HAK_AKSES primary key (ID_ROLE, ID_UI)
);

/*==============================================================*/
/* Index: ROLE_HAKAKSES_FK                                      */
/*==============================================================*/
create index ROLE_HAKAKSES_FK on HAK_AKSES (
   ID_ROLE ASC
);

/*==============================================================*/
/* Index: ROLE_HAKAKSES2_FK                                     */
/*==============================================================*/
create index ROLE_HAKAKSES2_FK on HAK_AKSES (
   ID_UI ASC
);

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
create table MENU  (
   ID_MENU              INTEGER                         not null,
   NAMA_MENU            VARCHAR2(30),
   constraint PK_MENU primary key (ID_MENU)
);

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE  (
   ID_ROLE              VARCHAR2(30)                    not null,
   NAME_ROLE            VARCHAR2(30),
   DESCRIPTION_ROLE     VARCHAR2(30),
   constraint PK_ROLE primary key (ID_ROLE)
);

/*==============================================================*/
/* Table: UI                                                    */
/*==============================================================*/
create table UI  (
   ID_UI                CHAR(4)                         not null,
   ID_MENU              INTEGER                         not null,
   NAME_UI              VARCHAR2(50),
   constraint PK_UI primary key (ID_UI)
);

/*==============================================================*/
/* Index: MEMBER_MENU_FK                                        */
/*==============================================================*/
create index MEMBER_MENU_FK on UI (
   ID_MENU ASC
);

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS  (
   USER_NAME            VARCHAR2(30)                    not null,
   ID_ROLE              VARCHAR2(30),
   PASSWORD             VARCHAR2(10),
   FIRST_NAME           VARCHAR2(30),
   LAST_NAME            VARCHAR2(30),
   constraint PK_USERS primary key (USER_NAME)
);

/*==============================================================*/
/* Index: HAS_ROLE_FK                                           */
/*==============================================================*/
create index HAS_ROLE_FK on USERS (
   ID_ROLE ASC
);

alter table HAK_AKSES
   add constraint FK_HAK_AKSE_HAK_AKSES_ROLE foreign key (ID_ROLE)
      references ROLE (ID_ROLE);

alter table HAK_AKSES
   add constraint FK_HAK_AKSE_HAK_AKSES_UI foreign key (ID_UI)
      references UI (ID_UI);

alter table UI
   add constraint FK_UI_MEMBER_ME_MENU foreign key (ID_MENU)
      references MENU (ID_MENU);

alter table USERS
   add constraint FK_USERS_HAS_ROLE_ROLE foreign key (ID_ROLE)
      references ROLE (ID_ROLE);

