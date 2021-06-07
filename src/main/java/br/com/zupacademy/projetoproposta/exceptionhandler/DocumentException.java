package br.com.zupacademy.projetoproposta.exceptionhandler;

import org.springframework.http.HttpStatus;

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
