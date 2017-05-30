package dao;

import java.sql.*;

import javax.servlet.ServletContext;

import bean.Buyer;

public class BuyerDao {
	private Connection conn = null;
	private String user = "root";
	private String password = "654321";
	private void initConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");  //?useUnicode=true&characterEncoding=UTF-8
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction_system?useUnicode=true&characterEncoding=UTF-8",
  				user,password);
	}
	
	public boolean validate(String buyerName) throws Exception{   //注册时验证buyer表中是否存在buyerName,若存在，则返回false
		initConnection();
		String sql = "select buyer_name from buyer where buyer_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, buyerName);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			return false;
		else
			return true;
	}
	
	public Buyer loginCheck(String buyerName) throws Exception{
		initConnection();
		Buyer buyer = null;
		String sql = "select * from Buyer where buyer_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, buyerName);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			buyer = new Buyer();
			buyer.setUserName(rs.getString("buyer_name"));
			buyer.setPassword(rs.getString("password"));
		}
		return buyer;
	}
	
	public int addBuyer(Buyer buyer) throws Exception{    //插入buyer，返回插入记录行数
		String buyerName;
		String password;
		int cnt;
		initConnection();
		buyerName = buyer.getUserName();
		password = buyer.getPassword();
		String sql = "insert into buyer(buyer_name,password) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, buyerName);
		ps.setString(2, password);
		cnt = ps.executeUpdate();
		return cnt;
	}
}
