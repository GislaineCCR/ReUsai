package dao;

import database.ConexaoBancoDados;
import model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO Categorias (nome, descricao) VALUES (?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNome());
            pstmt.setString(2, categoria.getDescricao());

            pstmt.executeUpdate();
            System.out.println("Categoria inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
        }
    }

    public void atualizar(Categoria categoria) {
        String sql = "UPDATE Categorias SET nome = ?, descricao = ? WHERE id_categoria = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, categoria.getNome());
            pstmt.setString(2, categoria.getDescricao());
            pstmt.setInt(3, categoria.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Categoria atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma categoria encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Categorias WHERE id_categoria = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Categoria removida com sucesso!");
            } else {
                System.out.println("Nenhuma categoria encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
        }
    }
    
    public List<Categoria> listarTodos() {
        String sql = "SELECT * FROM Categorias";
        List<Categoria> categorias = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        
        return categorias;
    }
}
