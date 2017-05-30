package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder.In;

import bean.Book;

public class BookDao {
	private Connection conn = null;
	private String user = "root";
	private String password = "654321";
	private void initConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");  //?useUnicode=true&characterEncoding=UTF-8
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction_system?useUnicode=true&characterEncoding=UTF-8",
  				user,password);
	}
	public int InsertBook(Book book) throws Exception{
		initConnection();
		String sellerName = book.getSellerName();
		String image = book.getImage();
		String price = book.getPrice();
		String info = book.getInfo();
		String author = book.getAuthor();
		String publishing = book.getPublishing();
		String bookName = book.getBookName();
		
		String sql = "insert into book(seller_name, image, price, info, author, "
				+ "publishing, book_name) values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sellerName);
		ps.setString(2, image);
		ps.setString(3, price);
		ps.setString(4, info);
		ps.setString(5, author);
		ps.setString(6, publishing);
		ps.setString(7, bookName);
		int cnt = ps.executeUpdate();
		return cnt;
	}
	
	public int update(Book book) throws Exception{   
		initConnection();
		String image = book.getImage();
		String bookName = book.getBookName();
		String price = book.getPrice();
		String author = book.getAuthor();
		String publishing = book.getPublishing();
		String info = book.getInfo();
		String sql = "update book set book_name=?, price=?, author=?, publishing=?, info=? where image=?";
		
		/*System.out.println("BookDao:update():image:" + image);
		System.out.println("BookDao:update():bookname:" + bookName);
		System.out.println("BookDao:update():price:" + price);
		System.out.println("BookDao:update():author:" + author);
		System.out.println("BookDao:update():publishing:" + publishing);
		System.out.println("BookDao:update():info:" + info);*/
		
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, bookName);
		ps.setString(2, price);
		ps.setString(3, author);
		ps.setString(4, publishing);
		ps.setString(5, info);
		ps.setString(6, image);
		int cnt = ps.executeUpdate();
		return cnt;
	}
	
	public int Print(String image){
		System.out.println(image);
		return 1;
	}
	
	public int DeleteBook(String image) throws Exception{
		initConnection();
		String sql = "delete from book where image = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, image);
		int cnt = ps.executeUpdate();
		return cnt;
	}
	
	public ArrayList<Book> getBook() throws Exception{
		ArrayList<Book> al = new ArrayList<Book>();
		initConnection();
		String sql1 = "select * from book";
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql1);
		while(rs.next()){
			Book book = new Book();
			book.setSellerName(rs.getString("seller_name"));
			book.setImage(rs.getString("image"));
			book.setPrice(rs.getString("price"));
			book.setAuthor(rs.getString("author"));
			book.setPublishing(rs.getString("publishing"));
			book.setBookName(rs.getString("book_name"));
			book.setInfo(rs.getString("info"));
			al.add(book);
		}
		return al;
 	}
	
	public ArrayList<Book> getMyBook(String sellerName) throws Exception{
		ArrayList<Book> myBook = new ArrayList<Book>();
		initConnection();
		String sql = "select * from book where seller_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sellerName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Book book = new Book();
			book.setSellerName(rs.getString("seller_name"));
			book.setImage(rs.getString("image"));
			book.setPrice(rs.getString("price"));
			book.setAuthor(rs.getString("author"));
			book.setPublishing(rs.getString("publishing"));
			book.setBookName(rs.getString("book_name"));
			book.setInfo(rs.getString("info"));
			myBook.add(book);
		}
		return myBook;
	}
	
}
