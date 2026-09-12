package dao;

import connection.ConnectionFactory;
import model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoucherDAO {
    private Connection conexao;

    public VoucherDAO() {
        this.conexao = new ConnectionFactory().conectar();
    }

    public void inserir(Voucher voucher) {
        String sql = "INSERT INTO TB_VOUCHER (codigo, usuarioId, transporteId, valorCredito, pontosUsados, status, dataGeracao, antiFraude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, voucher.getCodigo());
            stmt.setString(2, voucher.getUsuarioId());
            stmt.setString(3, voucher.getTransporteId());
            stmt.setDouble(4, voucher.getValorCredito());
            stmt.setDouble(5, voucher.getPontosUsados());
            stmt.setString(6,voucher.getStatus());
            stmt.setString(7, voucher.getDataGeracao());
            stmt.setBoolean(8, voucher.getAntiFraude());
            stmt.execute();
            System.out.println("Voucher cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir voucher: " + e.getMessage());
        }
    }

    public List<Voucher> listar() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM TB_VOUCHER";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Voucher v = new Voucher();
                v.setCodigo(rs.getString("codigo"));
                v.setUsuarioId(rs.getString("usuarioId"));
                v.setTransporteId(rs.getString("transporteId"));
                v.setValorCredito(rs.getDouble("valorCredito"));
                v.setPontosUsados(rs.getDouble("pontosUsados"));
                v.setStatus(rs.getString("status"));
                v.setDataGeracao(rs.getString("dataGeracao"));
                v.setAntifraude(rs.getBoolean("antiFraude"));
                vouchers.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vouchers: " + e.getMessage());
        }
        return vouchers;
    }

    public void atualizar(Voucher voucher) {
        String sql = "UPDATE TB_VOUCHER SET usuarioId = ?, transporteId = ?, valorCredito = ?, pontosUsados = ?, status = ?, dataGeracao = ?, antiFraude = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, voucher.getUsuarioId());
            stmt.setString(2, voucher.getTransporteId());
            stmt.setDouble(3, voucher.getValorCredito());
            stmt.setDouble(4, voucher.getPontosUsados());
            stmt.setString(5,voucher.getStatus());
            stmt.setString(6, voucher.getDataGeracao());
            stmt.setBoolean(7, voucher.getAntiFraude());
            stmt.setString(8, voucher.getCodigo());
            stmt.execute();
            System.out.println("Voucher atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar voucher: " + e.getMessage());
        }
    }

    public void excluir(String codigo) {
        String sql = "DELETE FROM TB_VOUCHER WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, codigo); // Alterado de setInt para setString
            stmt.execute();
            System.out.println("Voucher excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir voucher: " + e.getMessage());
        }
    }
}