package dao;

import database.ConexaoBancoDados;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public void inserir(Item item) {
        String sql = "INSERT INTO Itens (id_usuario_doador, id_categoria, titulo, descricao, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, item.getIdUsuarioDoador());
            pstmt.setInt(2, item.getIdCategoria());
            pstmt.setString(3, item.getTitulo());
            pstmt.setString(4, item.getDescricao());
            pstmt.setString(5, item.getStatus());

            pstmt.executeUpdate();
            System.out.println("Item inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir item: " + e.getMessage());
        }
    }

    public void atualizar(Item item) {
        String sql = "UPDATE Itens SET id_usuario_doador = ?, id_categoria = ?, titulo = ?, descricao = ?, status = ? WHERE id_item = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, item.getIdUsuarioDoador());
            pstmt.setInt(2, item.getIdCategoria());
            pstmt.setString(3, item.getTitulo());
            pstmt.setString(4, item.getDescricao());
            pstmt.setString(5, item.getStatus());
            pstmt.setInt(6, item.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Item atualizado com sucesso!");
            } else {
                System.out.println("Nenhum item encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Itens WHERE id_item = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Item removido com sucesso!");
            } else {
                System.out.println("Nenhum item encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover item: " + e.getMessage());
        }
    }
    
    public List<Item> listarTodos() {
        String sql = "SELECT * FROM Itens";
        List<Item> itens = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id_item"));
                item.setIdUsuarioDoador(rs.getInt("id_usuario_doador"));
                item.setIdCategoria(rs.getInt("id_categoria"));
                item.setTitulo(rs.getString("titulo"));
                item.setDescricao(rs.getString("descricao"));
                item.setStatus(rs.getString("status"));
                if (rs.getTimestamp("data_postagem") != null) {
                    item.setDataPostagem(rs.getTimestamp("data_postagem").toLocalDateTime());
                }
                itens.add(item);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens: " + e.getMessage());
        }
        
        return itens;
    }
}
