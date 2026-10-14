package br.com.formulario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InserirComentarioServlet")
public class InserirComentarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String autor = request.getParameter("autor");
        String texto = request.getParameter("texto");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Clube_db";
            conn = DriverManager.getConnection(url, "root", "");

            String sql = "INSERT INTO comentarios (autor, texto) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, autor);
            stmt.setString(2, texto);

            stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        // Redireciona para o servlet de listagem para mostrar o comentário novo na hora
        response.sendRedirect("ListarComentariosServlet");
    }
}