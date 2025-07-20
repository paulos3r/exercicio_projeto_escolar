package com.paulos3r.exercicio.infra;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Logger para registar erros
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  // 1. Trata exceções de validação de argumentos (@Valid em DTOs)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
    // Coleta todas as mensagens de erro de validação de campo
    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> {
              String fieldName = error.getField();
              String defaultMessage = error.getDefaultMessage();
              return String.format("Campo '%s': %s", fieldName, defaultMessage);
            })
            .collect(Collectors.joining("; ")); // Junta as mensagens com um ponto e vírgula

    logger.warn("Erro de validação de requisição: {}", errorMessage);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
  }

  // 2. Trata erros de requisição malformada (JSON inválido, tipos incorretos)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> tratarErro400(HttpMessageNotReadableException ex) {
    String errorMessage = "Corpo da requisição inválido. Verifique o formato JSON ou os tipos de dados.";
    if (ex.getLocalizedMessage() != null && ex.getLocalizedMessage().contains("JSON parse error")) {
      errorMessage += " Detalhes: " + ex.getLocalizedMessage().split("\n")[0]; // Pega a primeira linha da mensagem de erro JSON
    }
    logger.warn("Erro de requisição HTTP (400 - Bad Request): {}", errorMessage, ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
  }

  // 3. Trata erros de dados inválidos ou recursos não encontrados (do serviço)
  // Ex: CPF já existe, usuário vinculado, recurso referenciado não encontrado.
  @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class, EntityNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
    logger.warn("Erro de requisição de negócio (400 - Bad Request): {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
  }

  // 4. Trata exceções de regra de negócio personalizadas
  // Assumindo que RegraDeNegocioException é uma RuntimeException personalizada
  @ExceptionHandler(RegraDeNegocioException.class)
  public ResponseEntity<ErrorResponse> tratarErroRegraDeNegocio(RegraDeNegocioException ex) {
    logger.warn("Erro de regra de negócio (400 - Bad Request): {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
  }

  // 5. Trata acesso negado (geralmente por falta de permissão)
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> tratarErro403(AccessDeniedException ex) {
    logger.warn("Acesso negado (403 - Forbidden): {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Acesso negado. Você não tem permissão para esta operação."));
  }

  // 6. Trata falha de autenticação (credenciais inválidas, token expirado/ausente)
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> tratarErro401(AuthenticationException ex) {
    logger.warn("Falha de autenticação (401 - Unauthorized): {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Falha na autenticação. Credenciais inválidas ou ausentes."));
  }

  // 7. Trata todas as outras exceções não capturadas (erros internos do servidor)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
    logger.error("Erro interno do servidor (500 - Internal Server Error): {}", ex.getMessage(), ex); // Loga como ERRO
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."));
  }
}