package br.com.zupacademy.projetoproposta.exceptionhandler;

import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.ErroPadrao;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.ValidadorDeErro;
import feign.FeignException;
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
    public ResponseEntity<ErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidadorDeErro erro = new ValidadorDeErro(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            erro.addErro(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(DocumentException.class)
    public ResponseEntity<CampoDeMessagem> documentException(DocumentException e) {
        CampoDeMessagem campoDeErro = new CampoDeMessagem(e.getHttpStatus().getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(campoDeErro);
    }

//    @ExceptionHandler(FeignException.UnprocessableEntity.class)
//    public ResponseEntity<ErroPadrao> feignException(FeignException.UnprocessableEntity e)  {
//        ValidadorDeErro erro = new ValidadorDeErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", System.currentTimeMillis());
//            erro.addErro(e.getMessage().replace(e.getMessage(),"Documento"), "CPF ou CNPJ é inválido");
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
//    }

}

