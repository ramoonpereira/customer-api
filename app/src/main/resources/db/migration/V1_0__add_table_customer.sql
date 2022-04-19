create table  IF NOT EXISTS customerdb.customer(
    uuid CHAR(36) NOT NULL,
    name VARCHAR NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
CONSTRAINT pk_customer PRIMARY KEY (uuid)
);