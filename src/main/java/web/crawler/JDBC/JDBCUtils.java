package web.crawler.JDBC;


import java.sql.*;

public class JDBCUtils {

    public static Statement myJDBC(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Shanghai&useSSL=false", "root", "allen");
            //创建SQL语句
            //创建Statement对象
            stmt = conn.createStatement();
            //执行SQL语句
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (stmt != null) {//避免空指针异常
//                try {
//                    stmt.close();//释放资源
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return stmt;
    }

    public static Connection connection(){
        Connection conn = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Shanghai&useSSL=false", "root", "allen");
            //执行SQL语句
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public  static void saveContent(String title, String content) throws SQLException {
        Connection connection = connection();
        PreparedStatement ps=null;
        String sql="insert into url_info values (null,?,?)";
        try{
            ps=connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2,content);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("保存数据库出错："+e.getMessage());
        }


    }

}
