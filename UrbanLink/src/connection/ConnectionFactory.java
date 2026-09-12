package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // As credenciais devem estar obrigatóriamente no código conforme exigência da Sprint 3
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "Rm569432"; // Ex: rm12345
    private static final String PASS = "170408";

    public Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão com o Banco de Dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage());
        }
    }
}