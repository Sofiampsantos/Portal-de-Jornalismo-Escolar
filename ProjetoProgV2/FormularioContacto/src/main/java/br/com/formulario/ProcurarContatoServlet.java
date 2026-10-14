package br.com.formulario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProcurarContatoServlet")
public class ProcurarContatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String busca = request.getParameter("busca"); 
		List<String[]> resultados = new ArrayList<>();

		if (busca == null) {
			busca = "";
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://localhost:3306/Clube_db";
			conn = DriverManager.getConnection(url, "root", "");

			String sql = "SELECT id, nome, email, assunto, mensagem FROM contatos WHERE nome LIKE ? OR email LIKE ? OR assunto LIKE ?";
			stmt = conn.prepareStatement(sql);

			String termo = "%" + busca.trim() + "%";

			stmt.setString(1, termo);
			stmt.setString(2, termo);
			stmt.setString(3, termo);

			rs = stmt.executeQuery();

			while (rs.next()) {
				resultados.add(new String[]{
						rs.getString("id"),
						rs.getString("nome"),
						rs.getString("email"),
						rs.getString("assunto"),
						rs.getString("mensagem")
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}


		request.setAttribute("contatos", resultados);
		request.getRequestDispatcher("listar.jsp").forward(request, response);
	}
}