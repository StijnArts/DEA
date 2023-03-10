/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2014                    */
/* Created on:     3/3/2023 1:59:42 PM                          */


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST_SAVED_BY') and o.name = 'FK_PLAYLIST_PLAYLIST__USER')
alter table PLAYLIST_SAVED_BY
   drop constraint FK_PLAYLIST_PLAYLIST__USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST_SAVED_BY') and o.name = 'FK_PLAYLIST_PLAYLIST__PLAYLIST')
alter table PLAYLIST_SAVED_BY
   drop constraint FK_PLAYLIST_PLAYLIST__PLAYLIST
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TOKEN') and o.name = 'FK_TOKEN_REFERENCE_USER')
alter table TOKEN
   drop constraint FK_TOKEN_REFERENCE_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TRACK_ON_PLAYLIST') and o.name = 'FK_TRACK_ON_TRACK_ON__PLAYLIST')
alter table TRACK_ON_PLAYLIST
   drop constraint FK_TRACK_ON_TRACK_ON__PLAYLIST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PLAYLIST')
            and   type = 'U')
   drop table PLAYLIST
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST_SAVED_BY')
            and   name  = 'PLAYLIST_SAVED_BY2_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST_SAVED_BY.PLAYLIST_SAVED_BY2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST_SAVED_BY')
            and   name  = 'PLAYLIST_SAVED_BY_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST_SAVED_BY.PLAYLIST_SAVED_BY_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PLAYLIST_SAVED_BY')
            and   type = 'U')
   drop table PLAYLIST_SAVED_BY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TOKEN')
            and   type = 'U')
   drop table TOKEN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TRACK')
            and   type = 'U')
   drop table TRACK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TRACK_ON_PLAYLIST')
            and   name  = 'TRACK_ON_PLAYLIST_FK'
            and   indid > 0
            and   indid < 255)
   drop index TRACK_ON_PLAYLIST.TRACK_ON_PLAYLIST_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TRACK_ON_PLAYLIST')
            and   type = 'U')
   drop table TRACK_ON_PLAYLIST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"USER"')
            and   type = 'U')
   drop table "USER"
go

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST (
   PLAYLISTID           int                  not null,
   NAME                 varchar(100)         not null,
   OWNER                varchar(50)          not null,
   constraint PK_PLAYLIST primary key (PLAYLISTID)
)
go

/*==============================================================*/
/* Table: PLAYLIST_SAVED_BY                                     */
/*==============================================================*/
create table PLAYLIST_SAVED_BY (
   USERNAME             varchar(50)          not null,
   PLAYLISTID           int                  not null,
   constraint PK_PLAYLIST_SAVED_BY primary key (USERNAME, PLAYLISTID)
)
go

/*==============================================================*/
/* Index: PLAYLIST_SAVED_BY_FK                                  */
/*==============================================================*/




create nonclustered index PLAYLIST_SAVED_BY_FK on PLAYLIST_SAVED_BY (USERNAME ASC)
go

/*==============================================================*/
/* Index: PLAYLIST_SAVED_BY2_FK                                 */
/*==============================================================*/




create nonclustered index PLAYLIST_SAVED_BY2_FK on PLAYLIST_SAVED_BY (PLAYLISTID ASC)
go

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK (
   TRACKID              int                  not null,
   TRACKTITLE           varchar(100)         not null,
   PERFORMER            varchar(100)         not null,
   TRACKDURATION        int                  not null,
   ALBUM                varchar(100)         not null,
   PLAYCOUNT            int                  null,
   PUBLICATIONDATE      Date                 null,
   TRACKDESCRIPTION     varchar(100)         null,
   OFFLINEAVAILABLE     bit                  not null,
   constraint PK_TRACK primary key (TRACKID)
)
go

/*==============================================================*/
/* Table: TRACK_ON_PLAYLIST                                     */
/*==============================================================*/
create table TRACK_ON_PLAYLIST (
   PLAYLISTID           int                  not null,
   TRACKID              int                  not null,
   constraint PK_TRACK_ON_PLAYLIST primary key (PLAYLISTID, TRACKID)
)
go

/*==============================================================*/
/* Index: TRACK_ON_PLAYLIST_FK                                  */
/*==============================================================*/




create nonclustered index TRACK_ON_PLAYLIST_FK on TRACK_ON_PLAYLIST (PLAYLISTID ASC)
go

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USER" (
   USERNAME             varchar(50)          not null,
   FIRSTNAMELASTNAME    varchar(100)         not null,
   PASSWORD             varchar(max)          not null,
   TOKEN                varchar(max)		 null
   constraint PK_USER primary key (USERNAME)
)
go

alter table PLAYLIST_SAVED_BY
   add constraint FK_PLAYLIST_PLAYLIST__USER foreign key (USERNAME)
      references "USER" (USERNAME) on update cascade on delete cascade
go

alter table PLAYLIST_SAVED_BY
   add constraint FK_PLAYLIST_PLAYLIST__PLAYLIST foreign key (PLAYLISTID)
      references PLAYLIST (PLAYLISTID) on update cascade on delete cascade
go

alter table TOKEN
   add constraint FK_TOKEN_REFERENCE_USER foreign key (USERNAME)
      references "USER" (USERNAME)
         on update cascade on delete cascade
go

alter table TRACK_ON_PLAYLIST
   add constraint FK_TRACK_ON_TRACK_ON__PLAYLIST foreign key (PLAYLISTID)
      references PLAYLIST (PLAYLISTID) on update cascade on delete cascade
go

