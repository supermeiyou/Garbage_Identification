
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    public static void main(String[] args) {
        // JDBC连接参数
        String url = "jdbc:postgresql://localhost:5432/trash_sort";
        String username = "postgres";
        String password = "123456";

        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 在此处执行插入数据操作
            Statement statement = connection.createStatement();
            String sql = "update public.user set passwords=123456 where account='admin'";
            statement.executeUpdate(sql);
            // 关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}