package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.Seller;

public class SellerDao {
	private String user = "root";
	private String password = "654321";
	private Connection conn = null;
	private void initConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");  //?useUnicode=true&characterEncoding=UTF-8
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction_system?useUnicode=true&characterEncoding=UTF-8",
  				user,password);
	}
	
	public Seller getSeller(String name) throws Exception{
		Seller seller = null;
		initConnection();
		String sql = "select * from seller where seller_name = ?";
		PreparedStatement ps;
		
		if(conn == null )
			System.out.println("Conn is null");
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			seller = new Seller();
			seller.setSellerName(rs.getString("seller_name"));
			seller.setQq(rs.getString("qq"));
			seller.setWeixin(rs.getString("weixin"));
			seller.setTel(rs.getString("tel"));
			seller.setNowCnt(rs.getInt("now_cnt"));
			System.out.println("SellerDao 查找成功");
		}
		else
			System.out.println("SellerDao 查找失败");
		return seller;
	}
	
	public int addSeller(Seller seller) throws Exception{
		int cnt;
		initConnection();
		String sql = "insert into seller(seller_name, qq, weixin, tel, now_cnt) values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,seller.getSellerName());
		ps.setString(2, seller.getQq());
		ps.setString(3, seller.getWeixin());
		ps.setString(4, seller.getTel());
		ps.setInt(5, seller.getNowCnt());
		cnt = ps.executeUpdate();
		return cnt;
	}
	
	public int addUpload(String sellerName) throws Exception{
		initConnection();
		String sql = "update seller set now_cnt = now_cnt+1 where seller_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sellerName);
		int cnt = ps.executeUpdate();
		return cnt;
	}
	
	public HashMap<String, String> getContact(String sellerName) throws Exception{
		HashMap<String, String> contactMap = new HashMap<String, String>();
		initConnection();
		String sql = "select tel, qq, weixin from seller where seller_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sellerName);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			contactMap.put("tel", rs.getString("tel"));
			contactMap.put("qq", rs.getString("qq"));
			contactMap.put("weixin", rs.getString("weixin"));
		}
		return contactMap;
	}
}
