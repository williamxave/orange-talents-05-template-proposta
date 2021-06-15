package br.com.zupacademy.projetoproposta.dtos;
import br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao.SolicitaCartaoFeign;
import br.com.zupacademy.projetoproposta.models.AvisoDeViagem;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @Autowired
    private SolicitaCartaoFeign solicitaCartaoFeign;

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    public AvisoViagemRequest(
                              @NotBlank String destino,
                              @NotNull @Future LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }


    public AvisoDeViagem toModel(HttpServletRequest request,String uuidCartao) {
        return new AvisoDeViagem(
                uuidCartao,
                this.destino,
                this.validoAte,
                pegarIpDoCliente(request),
                pegarUserAgent(request)
        );
    }

    private String pegarIpDoCliente(HttpServletRequest request) {
        String ipDoCliente = request.getHeader("X-FORWARDED-FOR");
        if (ipDoCliente == null) {
            return ipDoCliente = request.getRemoteAddr();
        }
        return ipDoCliente;
    }

    private String pegarUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

}
