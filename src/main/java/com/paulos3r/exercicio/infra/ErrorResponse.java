package com.paulos3r.exercicio.infra;

public class ErrorResponse {
  private final String message;
  private final long timestamp;

  public ErrorResponse(String message) {
    this.message = message;
    this.timestamp = System.currentTimeMillis();
  }

  public String getMessage() {
    return message;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
