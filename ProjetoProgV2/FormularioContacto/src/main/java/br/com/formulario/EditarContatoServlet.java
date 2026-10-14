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
import java.sql.ResultSet;

@WebServlet("/EditarContatoServlet")
public class EditarContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		if (id != null && !id.trim().isEmpty()) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost:3306/Clube_db";
				conn = DriverManager.getConnection(url, "root", "");

				String sql = "SELECT id, nome, email, assunto, mensagem FROM contatos WHERE id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(id));
				
				rs = stmt.executeQuery();

				if (rs.next()) {

					request.setAttribute("id", rs.getInt("id"));
					request.setAttribute("nome", rs.getString("nome"));
					request.setAttribute("email", rs.getString("email"));
					request.setAttribute("assunto", rs.getString("assunto"));
					request.setAttribute("mensagem", rs.getString("mensagem"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try { if (rs != null) rs.close(); } catch (Exception e) {}
				try { if (stmt != null) stmt.close(); } catch (Exception e) {}
				try { if (conn != null) conn.close(); } catch (Exception e) {}
			}
		}


		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}
}