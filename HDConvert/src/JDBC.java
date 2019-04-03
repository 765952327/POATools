import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    Connection con;
 //驱动程序名
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/westudy_db?userUnicode=true&characterEncoding=utf8&useSSL=false";
    String user = "root";
    String password = "1234";
//遍历查询结果集

    public Connection getCon() {
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
