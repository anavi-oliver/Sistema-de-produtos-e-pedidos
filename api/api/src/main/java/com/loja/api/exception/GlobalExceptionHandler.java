package com.loja.api.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErroResposta> resposta(HttpStatus s, String msg, List<CampoErro> campos) {
        return ResponseEntity.status(s).body(
            new ErroResposta(s.value(), s.getReasonPhrase(), msg, campos, LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> validacao(MethodArgumentNotValidException ex) {
        List<CampoErro> campos = ex.getBindingResult().getFieldErrors().stream()
            .map(f -> new CampoErro(f.getField(), f.getDefaultMessage())).toList();
        return resposta(HttpStatus.BAD_REQUEST, "Dados inválidos", campos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> jsonInvalido(HttpMessageNotReadableException ex) {
        return resposta(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido ou com tipos incorretos", List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tipoParametro(MethodArgumentTypeMismatchException ex) {
        return resposta(HttpStatus.BAD_REQUEST, "Parâmetro '" + ex.getName() + "' inválido", List.of());
    }

    @ExceptionHandler(RecursoNaoEncontradoEX.class)
    public ResponseEntity<ErroResposta> naoEncontrado(RecursoNaoEncontradoEX ex) {
        return resposta(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(ConflitoEX.class)
    public ResponseEntity<ErroResposta> conflito(ConflitoEX ex) {
        return resposta(HttpStatus.CONFLICT, ex.getMessage(), List.of());
    }
}
