package br.com.zupacademy.projetoproposta.controllers;


import br.com.zupacademy.projetoproposta.dtos.PropostaRequest;
import br.com.zupacademy.projetoproposta.exceptionhandler.DocumentException;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.repositories.PropostaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    private PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder) throws DocumentException{
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());
        if(possivelProposta.isPresent()){
            throw new DocumentException(HttpStatus.UNPROCESSABLE_ENTITY,"Documento já está cadastrado!");
        }
            Proposta umaProposta =  request.toModel();
            propostaRepository.save(umaProposta);
            URI uri = builder.path("/proposta/{id}").buildAndExpand(umaProposta.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

}
