CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único da pessoa',
    nome VARCHAR(255) NOT NULL COMMENT 'Nome completo da pessoa, não pode ser nulo',
    cpf VARCHAR(14) NOT NULL UNIQUE COMMENT 'Cadastro de Pessoa Física (formato XXX.XXX.XXX-XX), deve ser único e não nulo',
    data_nascimento DATE NOT NULL COMMENT 'Data de nascimento da pessoa, não pode ser nula',
    endereco VARCHAR(500) COMMENT 'Endereço completo da pessoa (opcional, se não for sempre obrigatório)',
    telefone VARCHAR(20) COMMENT 'Número de telefone da pessoa (formato flexível, ex: (XX) XXXXX-XXXX)',
    usuario_id BIGINT NOT NULL UNIQUE COMMENT 'ID do usuário associado a esta pessoa, deve ser único e não nulo',
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE RESTRICT
    -- ON DELETE RESTRICT: Impede a exclusão de um usuário se houver uma pessoa associada a ele.
    -- ON DELETE CASCADE: Exclui a pessoa automaticamente se o usuário associado for excluído (use com cautela!).
    -- ON DELETE SET NULL: Define usuario_id como NULL se o usuário associado for excluído (se usuario_id for NULO).
);

-- Índices Adicionais (para otimização de consultas)

CREATE INDEX idx_pessoa_nome ON pessoa (nome);
CREATE INDEX idx_pessoa_telefone ON pessoa (telefone);
CREATE INDEX idx_pessoa_cpf ON pessoa (cpf);