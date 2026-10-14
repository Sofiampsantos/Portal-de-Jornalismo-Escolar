<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal de Jornalismo Escolar - ESMM - Sucesso</title>
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
 
    <section id="conteudo" style="text-align: center; padding: 40px 20px;">
        <h2 style="color: #b30000; border: none; font-size: 2em;">Mensagem enviada com sucesso!</h2>
        <p style="font-size: 1.2em; margin-bottom: 30px;">Agradecemos o teu contacto. A mensagem foi registada no sistema do Portal.</p>
        
        <div style="margin-top: 20px;">
            <a href="contato.jsp" style="font-weight: bold; color: #b30000; margin-right: 20px;">Voltar ao Formulário</a> | 
            <a href="ListarContatosServlet" style="font-weight: bold; color: #b30000; margin-left: 20px;">Listar Contactos</a>
        </div>
    </section>
  </div>
		
  <footer id="rodape">
      <p>&copy; 2026 Portal de Jornalismo Escolar ESMM | Todos os direitos reservados.</p>	
  </footer>	
 
</body>
</html>