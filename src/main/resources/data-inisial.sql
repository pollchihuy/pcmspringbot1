/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_SQLEXPRESS
 Source Server Type    : SQL Server
 Source Server Version : 15002130
 Source Host           : localhost:1433
 Source Catalog        : DBPROJECT
 Source Schema         : batch23

 Target Server Type    : SQL Server
 Target Server Version : 15002130
 File Encoding         : 65001

 Date: 17/01/2025 18:51:11
*/


-- ----------------------------
-- Table structure for LogGroupMenu
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[LogGroupMenu]') AND type IN ('U'))
DROP TABLE [batch23].[LogGroupMenu]
    GO

CREATE TABLE [batch23].[LogGroupMenu] (
    [ID] bigint  IDENTITY(1,1) NOT NULL,
    [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedDate] datetime2(6)  NOT NULL,
    [Flag] char(1) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [IDGroupMenu] bigint  NULL,
    [NamaGroupMenu] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
    )
    GO

ALTER TABLE [batch23].[LogGroupMenu] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of LogGroupMenu
-- ----------------------------
    SET IDENTITY_INSERT [batch23].[LogGroupMenu] ON
    GO

    SET IDENTITY_INSERT [batch23].[LogGroupMenu] OFF
    GO


    -- ----------------------------
-- Table structure for MapAksesMenu
-- ----------------------------
    IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[MapAksesMenu]') AND type IN ('U'))
DROP TABLE [batch23].[MapAksesMenu]
    GO

CREATE TABLE [batch23].[MapAksesMenu] (
    [IDAkses] bigint  NOT NULL,
[IDMenu] bigint  NOT NULL
)
    GO

ALTER TABLE [batch23].[MapAksesMenu] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of MapAksesMenu
-- ----------------------------
    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'1')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'2')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'3')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'4')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'5')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'1', N'6')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'5')
    GO

    INSERT INTO [batch23].[MapAksesMenu] ([IDAkses], [IDMenu]) VALUES (N'2', N'6')
    GO


    -- ----------------------------
-- Table structure for MstAkses
-- ----------------------------
    IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[MstAkses]') AND type IN ('U'))
DROP TABLE [batch23].[MstAkses]
    GO

CREATE TABLE [batch23].[MstAkses] (
    [IDAkses] bigint  IDENTITY(1,1) NOT NULL,
    [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedDate] datetime2(6)  NOT NULL,
    [NamaAkses] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [UpdatedDate] datetime2(6)  NULL
    )
    GO

ALTER TABLE [batch23].[MstAkses] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of MstAkses
-- ----------------------------
    SET IDENTITY_INSERT [batch23].[MstAkses] ON
    GO

    INSERT INTO [batch23].[MstAkses] ([IDAkses], [CreatedBy], [CreatedDate], [NamaAkses], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'Paul', N'2025-01-17 18:33:53.000000', N'Admin', NULL, NULL)
    GO

    INSERT INTO [batch23].[MstAkses] ([IDAkses], [CreatedBy], [CreatedDate], [NamaAkses], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'Paul', N'2025-01-17 18:33:53.000000', N'Member', NULL, NULL)
    GO

    SET IDENTITY_INSERT [batch23].[MstAkses] OFF
    GO


    -- ----------------------------
-- Table structure for MstGroupMenu
-- ----------------------------
    IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[MstGroupMenu]') AND type IN ('U'))
DROP TABLE [batch23].[MstGroupMenu]
    GO

CREATE TABLE [batch23].[MstGroupMenu] (
    [IDGroupMenu] bigint  IDENTITY(1,1) NOT NULL,
    [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedDate] datetime2(6)  NOT NULL,
    [NamaGroupMenu] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [UpdatedDate] datetime2(6)  NULL
    )
    GO

ALTER TABLE [batch23].[MstGroupMenu] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of MstGroupMenu
-- ----------------------------
    SET IDENTITY_INSERT [batch23].[MstGroupMenu] ON
    GO

    INSERT INTO [batch23].[MstGroupMenu] ([IDGroupMenu], [CreatedBy], [CreatedDate], [NamaGroupMenu], [UpdatedBy], [UpdatedDate]) VALUES (N'1', N'Paul', N'2025-01-17 18:33:04.000000', N'User Management', NULL, NULL)
    GO

    INSERT INTO [batch23].[MstGroupMenu] ([IDGroupMenu], [CreatedBy], [CreatedDate], [NamaGroupMenu], [UpdatedBy], [UpdatedDate]) VALUES (N'2', N'Paul', N'2025-01-17 18:33:53.000000', N'Artikel', NULL, NULL)
    GO

    SET IDENTITY_INSERT [batch23].[MstGroupMenu] OFF
    GO


    -- ----------------------------
-- Table structure for MstMenu
-- ----------------------------
    IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[MstMenu]') AND type IN ('U'))
DROP TABLE [batch23].[MstMenu]
    GO

CREATE TABLE [batch23].[MstMenu] (
    [IDMenu] bigint  IDENTITY(1,1) NOT NULL,
    [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedDate] datetime2(6)  NOT NULL,
    [NamaMenu] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [Path] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [UpdatedDate] datetime2(6)  NULL,
    [IDGroupMenu] bigint  NULL
    )
    GO

ALTER TABLE [batch23].[MstMenu] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of MstMenu
-- ----------------------------
    SET IDENTITY_INSERT [batch23].[MstMenu] ON
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'1', N'Paul', N'2025-01-17 18:33:32.000000', N'Group-Menu', N'/group-menu', NULL, NULL, N'1')
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'2', N'Paul', N'2025-01-17 18:33:53.000000', N'Menu', N'/menu', NULL, NULL, N'1')
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'3', N'Paul', N'2025-01-17 18:33:53.000000', N'Akses', N'/akses', NULL, NULL, N'1')
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'4', N'Paul', N'2025-01-17 18:33:53.000000', N'User', N'/user', NULL, NULL, N'1')
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'5', N'Paul', N'2025-01-17 18:33:53.000000', N'Artikel 1', N'/artikel-1', NULL, NULL, N'1')
    GO

    INSERT INTO [batch23].[MstMenu] ([IDMenu], [CreatedBy], [CreatedDate], [NamaMenu], [Path], [UpdatedBy], [UpdatedDate], [IDGroupMenu]) VALUES (N'6', N'Paul', N'2025-01-17 18:33:53.000000', N'Artikel 2', N'/artikel-2', NULL, NULL, N'1')
    GO

    SET IDENTITY_INSERT [batch23].[MstMenu] OFF
    GO


    -- ----------------------------
-- Table structure for MstUser
-- ----------------------------
    IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[batch23].[MstUser]') AND type IN ('U'))
DROP TABLE [batch23].[MstUser]
    GO

CREATE TABLE [batch23].[MstUser] (
    [IDUser] bigint  IDENTITY(1,1) NOT NULL,
    [Alamat] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [CreatedDate] datetime2(6)  NOT NULL,
    [Email] varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [Nama] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [NoHp] varchar(16) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [OTP] varchar(60) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [Password] varchar(60) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [TanggalLahir] date  NULL,
    [UpdatedBy] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
    [UpdatedDate] datetime2(6)  NULL,
    [username] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
    [IDAkses] bigint  NULL
    )
    GO

ALTER TABLE [batch23].[MstUser] SET (LOCK_ESCALATION = TABLE)
    GO


    -- ----------------------------
-- Records of MstUser
-- ----------------------------
    SET IDENTITY_INSERT [batch23].[MstUser] ON
    GO

    INSERT INTO [batch23].[MstUser] ([IDUser], [Alamat], [CreatedBy], [CreatedDate], [Email], [Nama], [NoHp], [OTP], [Password], [TanggalLahir], [UpdatedBy], [UpdatedDate], [username], [IDAkses]) VALUES (N'1', N'Bogor', N'Paul', N'2025-01-17 18:48:20.000000', N'poll.chihuy@gmail.com', N'Paul', N'0812081301', NULL, N'$2a$11$udu1/4a6//OCjiDxNrrwY.HJb4N.mC/zTCKbmlYbHkgB53q5Y/uMi', N'1995-01-17', NULL, NULL, N'paul123', N'1')
    GO

    SET IDENTITY_INSERT [batch23].[MstUser] OFF
    GO


    -- ----------------------------
-- Auto increment value for LogGroupMenu
-- ----------------------------
    DBCC CHECKIDENT ('[batch23].[LogGroupMenu]', RESEED, 1)
    GO


-- ----------------------------
-- Primary Key structure for table LogGroupMenu
-- ----------------------------
ALTER TABLE [batch23].[LogGroupMenu] ADD CONSTRAINT [PK__LogGroup__3214EC279C1DCC84] PRIMARY KEY CLUSTERED ([ID])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Uniques structure for table MapAksesMenu
-- ----------------------------
ALTER TABLE [batch23].[MapAksesMenu] ADD CONSTRAINT [unique-akses-menu] UNIQUE NONCLUSTERED ([IDAkses] ASC, [IDMenu] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


    -- ----------------------------
-- Auto increment value for MstAkses
-- ----------------------------
    DBCC CHECKIDENT ('[batch23].[MstAkses]', RESEED, 2)
    GO


-- ----------------------------
-- Uniques structure for table MstAkses
-- ----------------------------
ALTER TABLE [batch23].[MstAkses] ADD CONSTRAINT [UKng7cd4jbvlld3y6hhc78k3k1n] UNIQUE NONCLUSTERED ([NamaAkses] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Primary Key structure for table MstAkses
-- ----------------------------
ALTER TABLE [batch23].[MstAkses] ADD CONSTRAINT [PK__MstAkses__54B7D338F7D6D28E] PRIMARY KEY CLUSTERED ([IDAkses])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


    -- ----------------------------
-- Auto increment value for MstGroupMenu
-- ----------------------------
    DBCC CHECKIDENT ('[batch23].[MstGroupMenu]', RESEED, 2)
    GO


-- ----------------------------
-- Uniques structure for table MstGroupMenu
-- ----------------------------
ALTER TABLE [batch23].[MstGroupMenu] ADD CONSTRAINT [UKf5gq2i1lb3engd0u1bgqau14o] UNIQUE NONCLUSTERED ([NamaGroupMenu] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Primary Key structure for table MstGroupMenu
-- ----------------------------
ALTER TABLE [batch23].[MstGroupMenu] ADD CONSTRAINT [PK__MstGroup__D512201708038E06] PRIMARY KEY CLUSTERED ([IDGroupMenu])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


    -- ----------------------------
-- Auto increment value for MstMenu
-- ----------------------------
    DBCC CHECKIDENT ('[batch23].[MstMenu]', RESEED, 6)
    GO


-- ----------------------------
-- Uniques structure for table MstMenu
-- ----------------------------
ALTER TABLE [batch23].[MstMenu] ADD CONSTRAINT [UK1pmqjgtk7t17sjit03fkmtsxn] UNIQUE NONCLUSTERED ([Path] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO

ALTER TABLE [batch23].[MstMenu] ADD CONSTRAINT [UKqfuwr0ehsyv6rwbuhur9gt19m] UNIQUE NONCLUSTERED ([NamaMenu] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Primary Key structure for table MstMenu
-- ----------------------------
ALTER TABLE [batch23].[MstMenu] ADD CONSTRAINT [PK__MstMenu__089D3C22CB47C608] PRIMARY KEY CLUSTERED ([IDMenu])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


    -- ----------------------------
-- Auto increment value for MstUser
-- ----------------------------
    DBCC CHECKIDENT ('[batch23].[MstUser]', RESEED, 1)
    GO


-- ----------------------------
-- Uniques structure for table MstUser
-- ----------------------------
ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [UK5rm45ec1bxtxt1uadsx2ulfgk] UNIQUE NONCLUSTERED ([username] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO

ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [UKftqfpu08362mh0w1ccyoqa4x3] UNIQUE NONCLUSTERED ([NoHp] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO

ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [UKgyqdnp71ewj9g77l2v7kc47g2] UNIQUE NONCLUSTERED ([Password] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO

ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [UKoboap7j0f37yn6as1f4bdg8ge] UNIQUE NONCLUSTERED ([Email] ASC)
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Primary Key structure for table MstUser
-- ----------------------------
ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [PK__MstUser__EAE6D9DFAA16369A] PRIMARY KEY CLUSTERED ([IDUser])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
    GO


-- ----------------------------
-- Foreign Keys structure for table MapAksesMenu
-- ----------------------------
ALTER TABLE [batch23].[MapAksesMenu] ADD CONSTRAINT [fk-to-map-menu] FOREIGN KEY ([IDMenu]) REFERENCES [batch23].[MstMenu] ([IDMenu]) ON DELETE NO ACTION ON UPDATE NO ACTION
    GO

ALTER TABLE [batch23].[MapAksesMenu] ADD CONSTRAINT [fk-to-map-akses] FOREIGN KEY ([IDAkses]) REFERENCES [batch23].[MstAkses] ([IDAkses]) ON DELETE NO ACTION ON UPDATE NO ACTION
    GO


-- ----------------------------
-- Foreign Keys structure for table MstMenu
-- ----------------------------
ALTER TABLE [batch23].[MstMenu] ADD CONSTRAINT [fk-to-group-menu] FOREIGN KEY ([IDGroupMenu]) REFERENCES [batch23].[MstGroupMenu] ([IDGroupMenu]) ON DELETE NO ACTION ON UPDATE NO ACTION
    GO


-- ----------------------------
-- Foreign Keys structure for table MstUser
-- ----------------------------
ALTER TABLE [batch23].[MstUser] ADD CONSTRAINT [fk-user-to-akses] FOREIGN KEY ([IDAkses]) REFERENCES [batch23].[MstAkses] ([IDAkses]) ON DELETE NO ACTION ON UPDATE NO ACTION
    GO

