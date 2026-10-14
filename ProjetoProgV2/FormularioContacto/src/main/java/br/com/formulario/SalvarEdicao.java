package br.com.formulario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/SalvarEdicao")
public class SalvarEdicao extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
  
        request.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        

        if (id != null && !id.trim().isEmpty()) {
            
            Connection conn = null;
            PreparedStatement stmt = null;
            
            try {
 
                Class.forName("com.mysql.cj.jdbc.Driver");
                
  
                String url = "jdbc:mysql://localhost:3306/Clube_db";
                conn = DriverManager.getConnection(url, "root", "");
                

                String sql = "UPDATE contatos SET nome = ?, email = ?, assunto = ?, mensagem = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, request.getParameter("nome"));
                stmt.setString(2, request.getParameter("email"));
                stmt.setString(3, request.getParameter("assunto"));
                stmt.setString(4, request.getParameter("mensagem"));
                stmt.setInt(5, Integer.parseInt(id));
                
                stmt.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                try { if (stmt != null) stmt.close(); } catch (Exception e) {}
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }
        }
        
 
        response.sendRedirect("ListarContatosServlet");
    }
}