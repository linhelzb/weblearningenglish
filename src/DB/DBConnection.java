package DB;
import java.sql.*;
public class DBConnection {
	
	public static Connection CreateConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/toeicmyclass?useUnicode=true?characterEncoding=UTF-8";
		String usename  = "root";
		String password = "luong";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			conn = DriverManager.getConnection(url, usename, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
