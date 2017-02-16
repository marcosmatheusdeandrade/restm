SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX FK_g9nplocpvxocy2tnbksiwr4sn ON diretor_dispositivo_acesso;
DROP INDEX FK_34crc4eg9f89hn6uhj5aofoh6 ON diretor_rede;
DROP INDEX FK_sl8ar8721ktywc6qmrik52563 ON portal_rede;
DROP INDEX FK_gh88l7kkdfi6quqnao8l2ux68 ON vendedor_dispositivo_acesso;



/* Drop Tables */

DROP TABLE IF EXISTS diretor_dispositivo_acesso;
DROP TABLE IF EXISTS diretor_rede;
DROP TABLE IF EXISTS diretor_diretor;
DROP TABLE IF EXISTS portal_administrador;
DROP TABLE IF EXISTS portal_rede;
DROP TABLE IF EXISTS portal_responsavel;
DROP TABLE IF EXISTS sequence_diretor_id;
DROP TABLE IF EXISTS sequence_rede_id;
DROP TABLE IF EXISTS sequencia_id_administrador;
DROP TABLE IF EXISTS sequencia_id_rede;
DROP TABLE IF EXISTS sequencia_id_responsavel;
DROP TABLE IF EXISTS seq_dispositivo_diretor;
DROP TABLE IF EXISTS seq_dispositivo_vendedor;
DROP TABLE IF EXISTS vendedor_dispositivo_acesso;




/* Create Tables */

CREATE TABLE diretor_diretor
(
	d_dir_id int NOT NULL,
	d_dir_cpf varchar(255) NOT NULL,
	d_dir_nome varchar(255) NOT NULL,
	d_dir_tipo_rede int NOT NULL,
	PRIMARY KEY (d_dir_id),
	UNIQUE (d_dir_cpf)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE diretor_dispositivo_acesso
(
	d_disp_id int NOT NULL,
	d_disp_id_diretor int,
	d_disp_chave_dispositivo varchar(255),
	d_disp_codigo_acesso varchar(255) NOT NULL,
	d_disp_data_cadastro datetime NOT NULL,
	d_disp_data_liberacao datetime,
	d_disp_modelo varchar(255),
	d_disp_nome varchar(255),
	d_disp_sistema_operacional varchar(255),
	d_disp_versao_so varchar(255),
	PRIMARY KEY (d_disp_id),
	UNIQUE (d_disp_codigo_acesso)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE diretor_rede
(
	d_rede_id int NOT NULL,
	d_rede_id_diretor int NOT NULL,
	d_rede_id_erp varchar(255),
	PRIMARY KEY (d_rede_id)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE portal_administrador
(
	p_adm_id int NOT NULL,
	p_adm_data_cadastro datetime NOT NULL,
	p_adm_email varchar(255) NOT NULL,
	p_adm_login varchar(255) NOT NULL,
	p_adm_nome varchar(255),
	p_adm_senha varchar(255) NOT NULL,
	p_adm_nivel_acesso int NOT NULL,
	PRIMARY KEY (p_adm_id)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE portal_rede
(
	p_rede_id int NOT NULL,
	p_rede_descricao_rede varchar(255) NOT NULL,
	p_rede_erp varchar(255) NOT NULL,
	p_rede_id_responsavel int NOT NULL,
	PRIMARY KEY (p_rede_id),
	UNIQUE (p_rede_erp)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE portal_responsavel
(
	p_resp_id int NOT NULL,
	p_resp_data_cadastro datetime NOT NULL,
	p_resp_email varchar(255) NOT NULL,
	p_resp_login varchar(255) NOT NULL,
	p_resp_nome varchar(255),
	p_resp_senha varchar(255) NOT NULL,
	p_resp_cpf varchar(255) NOT NULL,
	p_resp_quantidade_codigos_adquiridos_diretor int NOT NULL,
	p_resp_quantidade_codigos_adquiridos_vendedor int NOT NULL,
	PRIMARY KEY (p_resp_id),
	UNIQUE (p_resp_cpf)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE sequence_diretor_id
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE sequence_rede_id
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE sequencia_id_administrador
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE sequencia_id_rede
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE sequencia_id_responsavel
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE seq_dispositivo_diretor
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE seq_dispositivo_vendedor
(
	next_val bigint(20)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


CREATE TABLE vendedor_dispositivo_acesso
(
	v_disp_id int NOT NULL,
	v_disp_id_colaborador int NOT NULL,
	v_disp_id_erp varchar(255) NOT NULL,
	v_disp_chave_dispositivo varchar(255),
	v_disp_codigo_acesso varchar(255),
	v_disp_data_cadastro datetime,
	v_disp_data_liberacao date,
	v_disp_modelo varchar(255),
	v_disp_nome varchar(255),
	v_disp_sistema_operacional varchar(255),
	v_disp_versao_so varchar(255),
	PRIMARY KEY (v_disp_id)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;



/* Create Foreign Keys */

ALTER TABLE diretor_dispositivo_acesso
	ADD CONSTRAINT FK_g9nplocpvxocy2tnbksiwr4sn FOREIGN KEY (d_disp_id_diretor)
	REFERENCES diretor_diretor (d_dir_id)
;


ALTER TABLE diretor_rede
	ADD CONSTRAINT FK_34crc4eg9f89hn6uhj5aofoh6 FOREIGN KEY (d_rede_id_diretor)
	REFERENCES diretor_diretor (d_dir_id)
;


ALTER TABLE portal_rede
	ADD CONSTRAINT FK_sl8ar8721ktywc6qmrik52563 FOREIGN KEY (p_rede_id_responsavel)
	REFERENCES portal_responsavel (p_resp_id)
;



/* Create Indexes */

CREATE INDEX FK_g9nplocpvxocy2tnbksiwr4sn USING BTREE ON diretor_dispositivo_acesso (d_disp_id_diretor ASC);
CREATE INDEX FK_34crc4eg9f89hn6uhj5aofoh6 USING BTREE ON diretor_rede (d_rede_id_diretor ASC);
CREATE INDEX FK_sl8ar8721ktywc6qmrik52563 USING BTREE ON portal_rede (p_rede_id_responsavel ASC);
CREATE INDEX FK_gh88l7kkdfi6quqnao8l2ux68 USING BTREE ON vendedor_dispositivo_acesso (v_disp_id ASC);



