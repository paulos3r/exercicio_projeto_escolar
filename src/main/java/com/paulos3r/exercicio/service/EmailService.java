package com.paulos3r.exercicio.service;

import com.paulos3r.exercicio.infraestrutura.exception.RegraDeNegocioException;
import com.paulos3r.exercicio.model.Usuario;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private final JavaMailSender enviadorEmail;

  @Value("${spring.mail.username}")
  private String EMAIL_ORIGEM;
  @Value("${spring.application.name}")
  private String NOME_ENVIADOR;

  public static final String URL_SITE = "http://localhost:8080"; //"forumhub.com.br"

  public EmailService(JavaMailSender enviadorEmail) {
    this.enviadorEmail = enviadorEmail;
  }

  @Async
  private void enviarEmail(String para, String assunto, String texto) {
    try {
      MimeMessage message = enviadorEmail.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom(EMAIL_ORIGEM);
      helper.setTo(para);
      helper.setSubject(assunto);
      helper.setText(texto, true);

      // enviadorEmail.send(message);   esta dando falha ao envia o email preciso corrigir fururamente quando saber o que faz!

    } catch (Exception e) {
      throw new RegraDeNegocioException("Erro ao enviar email" + e.getMessage());
    }

  }

  public void enviarEmailVerificacao(Usuario usuario) {
    var email = usuario.getEmail();
    String assunto = "Aqui está seu link para verificar o email";
    String conteudo = gerarConteudoEmail("Olá [[name]],<br>"
            + "Por favor clique no link abaixo para verificar sua conta:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFICAR</a></h3>"
            + "Obrigado,<br>"
            + "Fórum Hub :).", usuario.getUsername(), URL_SITE + "/verificar-conta?codigo=" + usuario.getToken());

    enviarEmail(email, assunto, conteudo);
  }

  private String gerarConteudoEmail(String template, String nome, String url) {
    return template.replace("[[name]]", nome).replace("[[URL]]", url);
  }
}