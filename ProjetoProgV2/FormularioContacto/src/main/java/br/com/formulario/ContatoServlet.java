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

@WebServlet("/ContatoServlet")
public class ContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String assunto = request.getParameter("assunto");
		String mensagem = request.getParameter("mensagem");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://localhost:3306/Clube_db";
			conn = DriverManager.getConnection(url, "root", "");


			String sql = "INSERT INTO contatos (nome, email, assunto, mensagem) VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, nome);
			stmt.setString(2, email); 
			stmt.setString(3, assunto);
			stmt.setString(4, mensagem);
			
			stmt.executeUpdate();


			response.sendRedirect("sucesso.jsp");

		} catch (Exception e) {
			e.printStackTrace();
	

		} finally {

			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
	}
}