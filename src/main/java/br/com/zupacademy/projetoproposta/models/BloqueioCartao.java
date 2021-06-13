package br.com.zupacademy.projetoproposta.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime dataDeBloqueio = LocalDateTime.now();
    @NotBlank
    private String ipDoCliente;
    @NotBlank
    private String userAgent;

    public BloqueioCartao(
                          @NotBlank String ipDoCliente,
                          @NotBlank String userAgent) {
        this.dataDeBloqueio = dataDeBloqueio;
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
    }
    @Deprecated
    public BloqueioCartao(){
    }
}
