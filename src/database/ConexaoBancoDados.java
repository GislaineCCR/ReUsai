package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/UPX2";
    private static final String USUARIO = "upx2_user";
    private static final String SENHA = "facens123";
    
    // Metodo para obter a conexão
    public static Connection obterConexao() {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Cria e retorna a conexão
            System.out.println("Conectando ao banco de dados...");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver do banco de dados não encontrado!");
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
    
    // Metodo para fechar a conexão
    public static void fecharConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
