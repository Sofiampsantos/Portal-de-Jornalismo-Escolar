package br.com.formulario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String userDigitado = request.getParameter("utilizador");
        String senhaDigitada = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean loginValido = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Clube_db";
            conn = DriverManager.getConnection(url, "root", "");

            // Procura na tabela de utilizadores se o username e a senha batem certo
            String sql = "SELECT * FROM utilizadores WHERE username = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userDigitado);
            stmt.setString(2, senhaDigitada);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                loginValido = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Garante que todas as ligações à Base de Dados são fechadas corretamente
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        if (loginValido) {
            // Cria a sessão de segurança no servidor para lembrar que o admin entrou
            HttpSession session = request.getSession();
            session.setAttribute("utilizadorLogado", userDigitado);
            
            // CORRIGIDO: Redireciona diretamente para o teu ficheiro index.html original
            response.sendRedirect("index.html");
        } else {
            // Se falhar, volta ao login bonito e envia a mensagem de erro
            request.setAttribute("erro", "Utilizador ou Palavra-passe incorretos!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}