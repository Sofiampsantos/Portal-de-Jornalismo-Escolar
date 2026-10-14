<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal de Jornalismo Escolar - ESMM - Lista de Contactos</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/estilo.css">
</head>

<body style="background-image: url('${pageContext.request.contextPath}/img/jornmulheres.jpg'); background-size: cover; background-repeat: no-repeat; background-attachment: fixed; background-position: center;">
 
  <div class="container">
				
    <header id="topo">
        <img alt="Fotografia do Portal de Jornalismo Escolar"
             src="${pageContext.request.contextPath}/img/logoP.jpg" style="width: 184px; height: 211px;">
        <h1>Portal de Jornalismo da Escola Secundária de Mem Martins</h1>
    </header>
     
    <nav id="navegacao">
        <ul>
            <li><a href="index.html">Início</a></li>
            <li><a href="quem_somos.html">Quem Somos</a></li>
            <li><a href="projetos.html">Projetos</a></li>
            <li><a href="recursos.html">Recursos</a></li>
            <li><a href="contactos.html">Contactos</a></li>
            <li><a href="contato.jsp">Formulário de Contacto</a></li>
        </ul>
    </nav>
 
    <section id="conteudo">
        <h2>Contactos Recebidos</h2>

        <table border="1" style="width: 100%; margin-bottom: 20px; background-color: white; color: black;">
        
			<tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Assunto</th>
                <th style="color: white;">Mensagem</th> 
                <th style="color: white;">Ações</th>
            </tr>

            <%
            List<String[]> contatos = (List<String[]>) request.getAttribute("contatos");

            if (contatos != null && !contatos.isEmpty()) {
                for (String[] c : contatos) {
            %>
                <tr>
                    <td><%= c[1] %></td> 
                    <td><%= c[2] %></td> 
                    <td><%= c[3] %></td> 
                    <td><%= c[4] %></td> 
                    <td>
                        <a href="EditarContatoServlet?id=<%= c[0] %>">Editar</a> | 
                        <a href="ApagarContatoServlet?id=<%= c[0] %>" onclick="return confirm('Tens a certeza que queres apagar?');">Apagar</a>
                    </td>
                </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="5">Nenhum contacto encontrado</td>
                </tr>
            <%
            }
            %>
        </table>

        <div style="text-align: center; margin-top: 20px;">
            <form action="ListarContatosServlet" method="get" style="display: inline-block; margin-right: 10px;">
                <input type="text" name="busca" placeholder="Procurar por nome, email..." style="width: 250px;">
                <button type="submit">Pesquisar</button>
            </form>

            <a href="ListarContatosServlet?ordem=nome" style="text-decoration: none;">
                <button type="button" style="background-color: #8B0000; color: white; padding: 10px 15px; border: none; cursor: pointer; font-weight: bold;">
                    🔤 Ordenar por Ordem Alfabética
                </button>
            </a>
        </div>

        <br><br>
        <a href="contato.jsp" style="font-weight: bold;">Voltar ao Formulário</a>
    </section>
  </div>
		
  <footer id="rodape">
      <p>&copy; 2026 Portal de Jornalismo Escolar ESMM | Todos os direitos reservados.</p>	
  </footer>	
	
  <script src="${pageContext.request.contextPath}/scripts/script.js"></script>
 
</body>
</html>