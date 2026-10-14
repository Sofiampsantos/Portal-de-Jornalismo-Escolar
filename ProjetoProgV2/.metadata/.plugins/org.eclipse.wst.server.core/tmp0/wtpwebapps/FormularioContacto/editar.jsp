<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal de Jornalismo Escolar - ESMM - Editar Contacto</title>
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
        <h2>Editar Contacto</h2>
        
        <form action="SalvarEdicao" method="post" style="box-shadow: none; padding: 10px 0; border-top: none;">
            <input type="hidden" name="id" value="${contato[0]}">
         
            <label style="font-weight: bold; color: #4a2c1a;">Nome:</label>
            <input type="text" name="nome" value="${contato[1]}" required><br><br>
         
            <label style="font-weight: bold; color: #4a2c1a;">Email:</label>
            <input type="email" name="email" value="${contato[2]}" required><br><br>
         
            <label style="font-weight: bold; color: #4a2c1a;">Assunto:</label>
            <input type="text" name="assunto" value="${contato[3]}" required><br><br>
         
            <label style="font-weight: bold; color: #4a2c1a;">Mensagem:</label>
            <textarea name="mensagem" rows="5" required>${contato[4]}</textarea><br><br>
         
            <button type="submit" style="background-color: #b30000; color: white; border: none; padding: 10px 20px; font-weight: bold; cursor: pointer;">Salvar Alterações</button>
        </form>
        
        <br>
        <a href="ListarContatosServlet" style="font-weight: bold; color: #4a2c1a;">Cancelar e Voltar</a>
    </section>
  </div>
		
  <footer id="rodape">
      <p>&copy; 2026 Portal de Jornalismo Escolar ESMM | Todos os direitos reservados.</p>	
  </footer>	
 
</body>
</html>