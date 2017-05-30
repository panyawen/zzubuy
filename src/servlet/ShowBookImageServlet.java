package servlet;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowBookImageServlet extends HttpServlet {

    private static final String JPG="image/jpeg;charset=utf-8";   // 原来是gb2312
    
    /**
     * get方法
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 本地文件路径
//    	System.out.println("AAA");
    	request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
//    	String image = (String)session.getAttribute("image");
    	String image = request.getParameter("image");
        String path = request.getRealPath("/");
        String filePath = "G:\\web_workspaces\\zzubuy\\WebRoot\\book\\" + image + ".jpg";
        
//        System.out.println("filePath:" + filePath);
        
        File file = new File(filePath);
        // 获取输出流
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        // 读数据
        byte[] data = new byte[fileInputStream.available()];
        fileInputStream.read(data);
        fileInputStream.close();
        // 回写
        response.setContentType(JPG);
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);   
    }
}