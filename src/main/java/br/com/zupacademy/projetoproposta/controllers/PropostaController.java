package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.dtos.PropostaRequest;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.repositories.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
            Proposta podeTerUmaPropostaValida =  request.toModel();
            propostaRepository.save(podeTerUmaPropostaValida);
            URI uri = builder.path("/proposta/{id}").buildAndExpand(podeTerUmaPropostaValida.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

}
