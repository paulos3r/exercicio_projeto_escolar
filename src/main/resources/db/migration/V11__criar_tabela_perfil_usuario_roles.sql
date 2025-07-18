CREATE TABLE perfil(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE usuarios_roles (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,

    PRIMARY KEY (usuario_id, perfil_id),

    -- Usando nomes de CONSTRAINT mais descritivos, como boa prática
    CONSTRAINT FK_USUARIOS_PERFIS_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT FK_USUARIOS_PERFIS_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);