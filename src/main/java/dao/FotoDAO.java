package dao;

import database.ConexaoBancoDados;
import model.Foto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FotoDAO {

    public void inserir(Foto foto) {
        String sql = "INSERT INTO Fotos (id_item, url_foto, descricao) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, foto.getIdItem());
            pstmt.setString(2, foto.getUrlFoto());
            pstmt.setString(3, foto.getDescricao());

            pstmt.executeUpdate();
            System.out.println("Foto inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir foto: " + e.getMessage());
        }
    }

    public void atualizar(Foto foto) {
        String sql = "UPDATE Fotos SET id_item = ?, url_foto = ?, descricao = ? WHERE id_foto = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, foto.getIdItem());
            pstmt.setString(2, foto.getUrlFoto());
            pstmt.setString(3, foto.getDescricao());
            pstmt.setInt(4, foto.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Foto atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma foto encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar foto: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Fotos WHERE id_foto = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Foto removida com sucesso!");
            } else {
                System.out.println("Nenhuma foto encontrada com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover foto: " + e.getMessage());
        }
    }
    
    public List<Foto> listarTodos() {
        String sql = "SELECT * FROM Fotos";
        List<Foto> fotos = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Foto foto = new Foto();
                foto.setId(rs.getInt("id_foto"));
                foto.setIdItem(rs.getInt("id_item"));
                foto.setUrlFoto(rs.getString("url_foto"));
                foto.setDescricao(rs.getString("descricao"));
                fotos.add(foto);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar fotos: " + e.getMessage());
        }
        
        return fotos;
    }
}
