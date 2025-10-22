package dao;

import database.ConexaoBancoDados;
import model.Mensagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    public void inserir(Mensagem mensagem) {
        String sql = "INSERT INTO Mensagens (id_conversa, id_remetente, conteudo, status_leitura) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, mensagem.getIdConversa());
            pstmt.setInt(2, mensagem.getIdRemetente());
            pstmt.setString(3, mensagem.getConteudo());
            pstmt.setString(4, mensagem.getStatusLeitura());

            pstmt.executeUpdate();
            System.out.println("Mensagem inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir mensagem: " + e.getMessage());
        }
    }

    public void atualizar(Mensagem mensagem) {
        String sql = "UPDATE Mensagens SET id_conversa = ?, id_remetente = ?, conteudo = ?, status_leitura = ? WHERE id_mensagem = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, mensagem.getIdConversa());
            pstmt.setInt(2, mensagem.getIdRemetente());
            pstmt.setString(3, mensagem.getConteudo());
            pstmt.setString(4, mensagem.getStatusLeitura());
            pstmt.setInt(5, mensagem.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Mensagem atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma mensagem encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar mensagem: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Mensagens WHERE id_mensagem = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Mensagem removida com sucesso!");
            } else {
                System.out.println("Nenhuma mensagem encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover mensagem: " + e.getMessage());
        }
    }
    
    public List<Mensagem> listarTodos() {
        String sql = "SELECT * FROM Mensagens";
        List<Mensagem> mensagens = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setId(rs.getInt("id_mensagem"));
                mensagem.setIdConversa(rs.getInt("id_conversa"));
                mensagem.setIdRemetente(rs.getInt("id_remetente"));
                mensagem.setConteudo(rs.getString("conteudo"));
                mensagem.setStatusLeitura(rs.getString("status_leitura"));
                if (rs.getTimestamp("data_envio") != null) {
                    mensagem.setDataEnvio(rs.getTimestamp("data_envio").toLocalDateTime());
                }
                mensagens.add(mensagem);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar mensagens: " + e.getMessage());
        }
        
        return mensagens;
    }
}
