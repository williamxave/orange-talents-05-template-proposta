package br.com.zupacademy.projetoproposta.dtos;
import br.com.zupacademy.projetoproposta.models.BloqueioCartao;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BloqueioRequest {

    private String ipDoCliente;

    private String userAgent;

    public BloqueioRequest(String ipDoCliente, String userAgent) {
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
    }

    public BloqueioRequest(){

    }

    public String getIpDoCliente() {
        return ipDoCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    private String pegarIpDoCliente(HttpServletRequest request) {
        String ipDoCliente = request.getHeader("X-FORWARDED-FOR");
        if (ipDoCliente == null) {
          return  ipDoCliente = request.getRemoteAddr();
        }
        return  ipDoCliente;
    }

    private String pegarUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return  userAgent;
    }

    public BloqueioCartao toModel(HttpServletRequest request) {
        return new BloqueioCartao(
        this.ipDoCliente  = pegarIpDoCliente(request),
        this.userAgent = pegarUserAgent(request)
        );
    }

}
