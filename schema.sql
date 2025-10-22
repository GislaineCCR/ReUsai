-- Seleciona o banco de dados para usar
USE UPX2;

-- Tabela de Usuários
CREATE TABLE IF NOT EXISTS Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    data_hora_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Categorias
CREATE TABLE IF NOT EXISTS Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT
);

-- Tabela de Itens para doação
CREATE TABLE IF NOT EXISTS Itens (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario_doador INT NOT NULL,
    id_categoria INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Disponível', -- Ex: Disponível, Doado, Reservado
    data_postagem TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario_doador) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

-- Tabela de Fotos dos Itens
CREATE TABLE IF NOT EXISTS Fotos (
    id_foto INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    url_foto VARCHAR(255) NOT NULL,
    descricao TEXT,
    FOREIGN KEY (id_item) REFERENCES Itens(id_item) ON DELETE CASCADE
);

-- Tabela de Conversas sobre os Itens
CREATE TABLE IF NOT EXISTS Conversas (
    id_conversa INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    id_usuario_interessado INT NOT NULL, -- Usuário que iniciou a conversa
    data_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'Ativa', -- Ex: Ativa, Finalizada
    FOREIGN KEY (id_item) REFERENCES Itens(id_item),
    FOREIGN KEY (id_usuario_interessado) REFERENCES Usuarios(id_usuario),
    -- Garante que um usuário só pode iniciar uma conversa por item
    UNIQUE (id_item, id_usuario_interessado)
);

-- Tabela de Mensagens dentro de uma Conversa
CREATE TABLE IF NOT EXISTS Mensagens (
    id_mensagem INT AUTO_INCREMENT PRIMARY KEY,
    id_conversa INT NOT NULL,
    id_remetente INT NOT NULL,
    conteudo TEXT NOT NULL,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_leitura VARCHAR(50) DEFAULT 'Não lida', -- Ex: Não lida, Lida
    FOREIGN KEY (id_conversa) REFERENCES Conversas(id_conversa),
    FOREIGN KEY (id_remetente) REFERENCES Usuarios(id_usuario)
);
