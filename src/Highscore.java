import java.sql.*;

public class Highscore implements AutoCloseable {
    private final String url;
    private Connection connection;

    public Highscore(String location) {
        url = "jdbc:sqlite:c:/sqlite/db/ + location";
    }

    public void connect() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        if(connection == null) {
            throw new SQLException("Driver error");
        }

        final String sqlCommand = "CREATE TABLE IF NOT EXISTS t_highscore (\n"
                + "name TEXT, \n"
                + "score INTEGER \n"
                + ");";

        Statement stmt = connection.createStatement();

        stmt.execute(sqlCommand);
    }

    public void addScore(String name, int score) throws SQLException {
        if(connection == null) {
            throw new NullPointerException("Driver is null");
        }

        final String sql = "INSERT INTO t_highscore(name, score) VALUES (?,?)";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, name);
        stmt.setInt(2, score);
        stmt.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
