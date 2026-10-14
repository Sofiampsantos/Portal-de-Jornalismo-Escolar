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

@WebServlet("/ListarContatosServlet") 
public class ListarContatosServlet extends HttpServlet { 

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		List<String[]> contatos = new ArrayList<>(); 
		

		String termoBusca = request.getParameter("busca");
		String ordenarPor = request.getParameter("ordem");
		

		if (ordenarPor == null || 
				(!ordenarPor.equals("nome") && 
				 !ordenarPor.equals("email") && 
				 !ordenarPor.equals("assunto"))) {
			ordenarPor = "nome"; 
		}
					
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try { 

			Class.forName("com.mysql.cj.jdbc.Driver"); 

			String url = "jdbc:mysql://localhost:3306/Clube_db";
			conn = DriverManager.getConnection(url, "root", "");


			String sql = "SELECT id, nome, email, assunto, mensagem FROM contatos WHERE 1=1";
			

			if (termoBusca != null && !termoBusca.trim().isEmpty()) {
				sql += " AND (nome LIKE ? OR email LIKE ? OR assunto LIKE ?)";
			}
			

			sql += " ORDER BY " + ordenarPor + " ASC";
			
			stmt = conn.prepareStatement(sql); 
			

			if (termoBusca != null && !termoBusca.trim().isEmpty()) {
				String filtro = "%" + termoBusca.trim() + "%";
				stmt.setString(1, filtro);
				stmt.setString(2, filtro);
				stmt.setString(3, filtro);
			}

			rs = stmt.executeQuery();

			while (rs.next()) { 
			    contatos.add(new String[]{
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

		request.setAttribute("contatos", contatos);
		request.getRequestDispatcher("listar.jsp").forward(request, response);
	} 
}