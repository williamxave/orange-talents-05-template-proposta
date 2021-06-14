package br.com.zupacademy.projetoproposta.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoDeViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String uuidCartao;

    @NotBlank
    private String destinoDaViajem;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDoTerminoDaViagem;

    @CreationTimestamp
    private LocalDateTime avisoDaViagem;

    @NotBlank
    private String ipDoCliente;

    @NotBlank
    private String userAgent;

    public AvisoDeViagem(
                        @NotBlank String uuidCartao,
                        @NotBlank String destinoDaViajem,
                        @NotNull @Future LocalDate dataDoTerminoDaViagem,
                        @NotBlank String ipDoCliente,
                        @NotBlank String userAgent) {
        this.uuidCartao = uuidCartao;
        this.destinoDaViajem = destinoDaViajem;
        this.dataDoTerminoDaViagem = dataDoTerminoDaViagem;
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
    }

    @Deprecated
    public AvisoDeViagem(){
    }
}
