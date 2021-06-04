package br.com.zupacademy.projetoproposta.exceptionhandler;

import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.ErroPadrao;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.ValidadorDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidadorDeErro erro = new ValidadorDeErro(HttpStatus.BAD_REQUEST.value(),"Erro de validação", System.currentTimeMillis());
        for(FieldError x :e.getBindingResult().getFieldErrors()){
            erro.addErro(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
