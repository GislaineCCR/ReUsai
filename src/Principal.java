import database.ConexaoBancoDados;
import java.sql.Connection;

public class Principal {
    public static void main(String[] args) {
        // Obtém a conexão com o banco de dados
        Connection conexao = ConexaoBancoDados.obterConexao();
        
        if (conexao != null) {
            try {
                System.out.println("Conexão realizada com sucesso!");
                System.out.println("Banco de dados: " + conexao.getCatalog());
            } catch (Exception e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            } finally {
                // Fecha a conexão
                ConexaoBancoDados.fecharConexao(conexao);
            }
        } else {
            System.out.println("Não foi possível conectar ao banco de dados.");
        }
    }
}
