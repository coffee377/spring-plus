CREATE TABLE oauth2_account
(
  ID         VARCHAR(32) NOT NULL COMMENT '用户 ID',
  USERNAME   VARCHAR(64) NOT NULL COMMENT '用户名',
  PASSWORD   VARCHAR(128) COMMENT '密码',
  JOB_NUMBER VARCHAR(32) COMMENT '工号',
  REAL_NAME  VARCHAR(32) COMMENT '真实姓名',
  MOBILE     VARCHAR(11) NOT NULL COMMENT '手机',
  EMAIL      VARCHAR(64) COMMENT '邮箱',
  AVATAR     VARCHAR(128) COMMENT '头像',
  STATUS     INT(2) COMMENT '状态',
  CREATED_BY VARCHAR(32) COMMENT '创建人',
  CREATED_AT DATETIME COMMENT '创建时间',
  UPDATED_BY VARCHAR(32) COMMENT '更新人',
  UPDATED_AT DATETIME COMMENT '更新时间',
  PRIMARY KEY (ID)
) COMMENT = '账号';


CREATE UNIQUE INDEX UK_USERNAME ON oauth2_account (USERNAME);
CREATE UNIQUE INDEX UK_JOB_NUMBER ON oauth2_account (JOB_NUMBER);
CREATE UNIQUE INDEX UK_MOBILE ON oauth2_account (MOBILE);
CREATE INDEX UK_EMAIL ON oauth2_account (EMAIL);

CREATE TABLE oauth2_account_bind
(
  ID         VARCHAR(32) NOT NULL COMMENT '主键',
  ACCOUNT_ID VARCHAR(32) NOT NULL COMMENT '账号 ID',
  PROVIDER   VARCHAR(64) NOT NULL COMMENT '第三方认证提供商',
  UNION_ID   VARCHAR(64) NOT NULL COMMENT '用户在第三方平台唯一 ID;通常指第三方账号体系下用户的唯一ID',
  OPEN_ID    VARCHAR(64) NOT NULL COMMENT '用户在第三方平台某个开放应用 ID;通常指第三方账号体系下某应用中用户的唯一ID',
  PRIMARY KEY (ID)
) COMMENT = '账号绑定';

CREATE UNIQUE INDEX UK_TUO ON oauth2_account_bind (PROVIDER, UNION_ID, OPEN_ID);



CREATE TABLE oauth2_client
(
  ID                            VARCHAR(32) NOT NULL COMMENT '主键',
  CLIENT_ID                     VARCHAR(32) NOT NULL COMMENT '客户端 ID',
  CLIENT_ID_ISSUED_AT           DATETIME    NOT NULL COMMENT '客户端注册时间',
  CLIENT_REGISTRATION_ID        VARCHAR(64) COMMENT '注册 ID',
  CLIENT_SECRET                 VARCHAR(64) COMMENT '客户端密钥',
  CLIENT_SECRET_EXPIRES_AT      DATETIME COMMENT '客户端密钥过期时间',
  CLIENT_NAME                   VARCHAR(32) NOT NULL COMMENT '客户端名称',
  CLIENT_AUTHENTICATION_METHODS INT         NOT NULL COMMENT '客户端认证方法',
  AUTHORIZATION_GRANT_TYPES     INT         NOT NULL COMMENT '授权类型',
  REDIRECT_URIS                 VARCHAR(128) COMMENT '重定向地址',
  SCOPES                        TEXT        NOT NULL COMMENT '允许授予的权限',
  CLIENT_SETTINGS               TEXT        NOT NULL COMMENT '客户端设置',
  TOKEN_SETTINGS                TEXT        NOT NULL COMMENT '令牌设置',
  PRIMARY KEY (ID)
) COMMENT = '客户端';


CREATE UNIQUE INDEX UK_CID ON oauth2_client (CLIENT_ID);
CREATE UNIQUE INDEX UK_CRID ON oauth2_client (CLIENT_REGISTRATION_ID);

CREATE TABLE oauth2_authorized_client
(
  ID                       VARCHAR(32) NOT NULL COMMENT '主键',
  REGISTERED_CLIENT_ID     VARCHAR(32) NOT NULL COMMENT '注册的客户端 ID',
  PRINCIPAL_NAME           VARCHAR(64) NOT NULL COMMENT '授权主体名称',
  AUTHORIZATION_GRANT_TYPE VARCHAR(64) NOT NULL COMMENT '授权类型',
  AUTHORITIES              VARCHAR(64) NOT NULL COMMENT '实际授予的权限',
  PRIMARY KEY (ID)
) COMMENT = '客户端授权';


CREATE UNIQUE INDEX UK_RCI_PN ON oauth2_authorized_client (REGISTERED_CLIENT_ID, PRINCIPAL_NAME);

CREATE TABLE oauth2_authorization_code
(
  ID            VARCHAR(32) NOT NULL COMMENT '主键',
  AUTHORIZED_ID VARCHAR(32) NOT NULL COMMENT '授权 ID',
  CODE          VARCHAR(64) NOT NULL COMMENT '授权码',
  STATE         VARCHAR(64) COMMENT '防止重放攻击参数',
  ISSUED_AT     DATETIME COMMENT '签发时间',
  EXPIRES_AT    DATETIME COMMENT '过期时间',
  METADATA      TEXT COMMENT '元数据',
  PRIMARY KEY (ID)
) COMMENT = '授权码';

CREATE TABLE oauth2_access_token
(
  ID            VARCHAR(32) NOT NULL COMMENT '主键',
  AUTHORIZED_ID VARCHAR(32) NOT NULL COMMENT '授权 ID',
  VALUE         TEXT        NOT NULL COMMENT '令牌',
  TYPE          VARCHAR(64) COMMENT '令牌类型',
  ISSUED_AT     DATETIME COMMENT '签发时间',
  EXPIRES_AT    DATETIME COMMENT '过期时间',
  METADATA      TEXT COMMENT '元数据',
  PRIMARY KEY (ID)
) COMMENT = '访问令牌';

CREATE TABLE oauth2_refresh_token
(
  ID            VARCHAR(32) NOT NULL COMMENT '主键',
  AUTHORIZED_ID VARCHAR(32) NOT NULL COMMENT '授权 ID',
  VALUE         TEXT        NOT NULL COMMENT '令牌',
  ISSUED_AT     DATETIME COMMENT '签发时间',
  EXPIRES_AT    DATETIME COMMENT '过期时间',
  METADATA      TEXT COMMENT '元数据',
  PRIMARY KEY (ID)
) COMMENT = '刷新令牌';

CREATE TABLE oauth2_oidc
(
  ID                  VARCHAR(32) NOT NULL COMMENT '主键',
  AUTHORIZED_ID       VARCHAR(32) NOT NULL COMMENT '授权 ID',
  ID_TOKEN            TEXT        NOT NULL COMMENT '令牌',
  ID_TOKEN_ISSUED_AT  DATETIME COMMENT '签发时间',
  ID_TOKEN_EXPIRES_AT DATETIME COMMENT '过期时间',
  ID_TOKEN_METADATA   TEXT COMMENT '元数据',
  PRIMARY KEY (ID)
) COMMENT = '开放授权';
