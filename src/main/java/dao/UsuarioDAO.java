package dao;

import database.ConexaoBancoDados;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nome_completo, email, senha, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getTelefone());
            pstmt.setString(5, usuario.getEndereco());

            pstmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nome_completo = ?, email = ?, senha = ?, telefone = ?, endereco = ? WHERE id_usuario = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getTelefone());
            pstmt.setString(5, usuario.getEndereco());
            pstmt.setInt(6, usuario.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";

        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
    
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        
        try (Connection conexao = ConexaoBancoDados.obterConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNomeCompleto(rs.getString("nome_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEndereco(rs.getString("endereco"));
                if (rs.getTimestamp("data_hora_criacao") != null) {
                    usuario.setDataHoraCriacao(rs.getTimestamp("data_hora_criacao").toLocalDateTime());
                }
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        
        return usuarios;
    }
}
