<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal de Jornalismo Escolar - Comentários</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/estilo.css">
</head>
<body style="background-image: url('${pageContext.request.contextPath}/img/jornmulheres.jpg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed; background-position: center;">

  <div class="container">
    <header id="topo">
        <img alt="Logo" src="${pageContext.request.contextPath}/img/logoP.jpg" style="width: 184px; height: 211px;">
        <h1>Portal de Jornalismo da Escola Secundária de Mem Martins</h1>
    </header>
     
    <nav id="navegacao">
        <ul>
            <li><a href="index.html">Início</a></li>
            <li><a href="ListarContatosServlet">Ver Contactos</a></li>
            <li><a href="ListarComentariosServlet">Mural de Comentários</a></li>
        </ul>
    </nav>
 
    <section id="conteudo">
        <h2>Deixa o teu Comentário</h2>

        <form action="InserirComentarioServlet" method="post" style="margin-bottom: 40px;">
            <label>O teu Nome:</label>
            <input type="text" name="autor" placeholder="Escreve o teu nome..." required>
            
            <label>Comentário:</label>
            <textarea name="texto" rows="4" placeholder="O que achas do nosso portal?..." required style="width: 100%; margin-top: 5px; border: 1px solid #c4a484; padding: 10px;"></textarea>
            
            <br><br>
            <button type="submit" style="background-color: #b30000; color: white; padding: 10px 20px; border: none; cursor: pointer; font-weight: bold;">Enviar Comentário</button>
        </form>

        <hr style="border: 1px solid #4a2c1a; margin: 30px 0;">

        <h2>Comentários dos Leitores (Mural)</h2>

        <table border="1" style="width: 100%; background-color: white; color: black;">
            <tr>
                <th style="width: 25%;">Autor</th>
                <th>Comentário</th>
                <th style="width: 15%; color: white;">Ações</th>
            </tr>

            <%
            List<String[]> lista = (List<String[]>) request.getAttribute("comentarios");
            if (lista != null && !lista.isEmpty()) {
                for (String[] com : lista) {
            %>
                <tr>
                    <td style="font-weight: bold;"><%= com[1] %></td>
                    <td style="text-align: left; padding: 10px;"><%= com[2] %></td>
                    <td>
                        <a href="ApagarComentarioServlet?id=<%= com[0] %>" onclick="return confirm('Queres mesmo apagar este comentário?');" style="color: #b30000; font-weight: bold;">Apagar</a>
                    </td>
                </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="3">Ainda não há comentários. Sê o primeiro!</td>
                </tr>
            <%
            }
            %>
        </table>
    </section>
  </div>

  <footer id="rodape">
      <p>&copy; 2026 Portal de Jornalismo Escolar ESMM | Todos os direitos reservados.</p> 
  </footer> 
</body>
</html>