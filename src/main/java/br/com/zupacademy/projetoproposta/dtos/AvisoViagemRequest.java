package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.AvisoDeViagem;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String uuidCartao;

    @NotBlank
    private String destinoDaViagem;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDoTerminoDaViagem;

    public AvisoViagemRequest(@NotBlank String uuidCartao,
                              @NotBlank String destinoDaViagem,
                              @NotNull @Future LocalDate dataDoTerminoDaViagem) {
        this.uuidCartao = uuidCartao;
        this.destinoDaViagem = destinoDaViagem;
        this.dataDoTerminoDaViagem = dataDoTerminoDaViagem;
    }


    public AvisoDeViagem toModel(HttpServletRequest request) {
        return new AvisoDeViagem(
                this.uuidCartao,
                this.destinoDaViagem,
                this.dataDoTerminoDaViagem,
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

    public String getDestinoDaViagem() {
        return destinoDaViagem;
    }

    public LocalDate getDataDoTerminoDaViagem() {
        return dataDoTerminoDaViagem;
    }
    public String getUuidCartao() {
        return uuidCartao;
    }

}
