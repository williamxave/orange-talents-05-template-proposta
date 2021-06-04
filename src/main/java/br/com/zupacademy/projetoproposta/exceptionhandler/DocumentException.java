package br.com.zupacademy.projetoproposta.exceptionhandler;

import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class DocumentException extends Exception{

    private HttpStatus httpStatus;
    private  String mensagem;

    public DocumentException(HttpStatus httpStatus, String mensagem) {
        super(mensagem);
        this.httpStatus = httpStatus;
        this.mensagem = mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
