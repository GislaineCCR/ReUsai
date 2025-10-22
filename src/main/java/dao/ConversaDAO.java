package dao;

import database.ConexaoBancoDados;
import model.Conversa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConversaDAO {

    public void inserir(Conversa conversa) {
        String sql = "INSERT INTO Conversas (id_item, id_usuario_interessado, status) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, conversa.getIdItem());
            pstmt.setInt(2, conversa.getIdUsuarioInteressado());
            pstmt.setString(3, conversa.getStatus());

            pstmt.executeUpdate();
            System.out.println("Conversa inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir conversa: " + e.getMessage());
        }
    }

    public void atualizar(Conversa conversa) {
        String sql = "UPDATE Conversas SET id_item = ?, id_usuario_interessado = ?, status = ? WHERE id_conversa = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, conversa.getIdItem());
            pstmt.setInt(2, conversa.getIdUsuarioInteressado());
            pstmt.setString(3, conversa.getStatus());
            pstmt.setInt(4, conversa.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Conversa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma conversa encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar conversa: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Conversas WHERE id_conversa = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Conversa removida com sucesso!");
            } else {
                System.out.println("Nenhuma conversa encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover conversa: " + e.getMessage());
        }
    }
    
    public List<Conversa> listarTodos() {
        String sql = "SELECT * FROM Conversas";
        List<Conversa> conversas = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Conversa conversa = new Conversa();
                conversa.setId(rs.getInt("id_conversa"));
                conversa.setIdItem(rs.getInt("id_item"));
                conversa.setIdUsuarioInteressado(rs.getInt("id_usuario_interessado"));
                conversa.setStatus(rs.getString("status"));
                if (rs.getTimestamp("data_inicio") != null) {
                    conversa.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                }
                conversas.add(conversa);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar conversas: " + e.getMessage());
        }
        
        return conversas;
    }
}
