CREATE TABLE Member
(
	userid VARCHAR2(12),
	username VARCHAR2(20)	NOT NULL,
	userage	NUMBER(2)	NOT NULL,
	usercity	VARCHAR2(20)	NOT NULL,
	CONSTRAINT member_userid_pk	PRIMARY KEY(userid),
	CONSTRAINT member_userage_ck	CHECK(userage BETWEEN 20 AND 40),
	CONSTRAINT member_username_uk	UNIQUE(username)
)