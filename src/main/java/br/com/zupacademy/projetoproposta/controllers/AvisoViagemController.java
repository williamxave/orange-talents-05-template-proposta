package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.conexoesexternas.avisoviagemapiexterna.AvisoViagemRequestApiExterna;
import br.com.zupacademy.projetoproposta.conexoesexternas.avisoviagemapiexterna.AvisoViagemResponseApiExterna;
import br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao.SolicitaCartaoFeign;
import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.AvisoViagemRequest;
import br.com.zupacademy.projetoproposta.models.AvisoDeViagem;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.repositories.AvisoDeViagemRepository;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {
    private static final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    private CartaoRepository cartaoRepository;
    private AvisoDeViagemRepository avisoDeViagemRepository;
    private SolicitaCartaoFeign solicitaCartaoFeign;

    public AvisoViagemController(CartaoRepository cartaoRepository,
                                 AvisoDeViagemRepository avisoDeViagemRepository,
                                 SolicitaCartaoFeign solicitaCartaoFeign) {
        this.cartaoRepository = cartaoRepository;
        this.avisoDeViagemRepository = avisoDeViagemRepository;
        this.solicitaCartaoFeign = solicitaCartaoFeign;
    }

    @PostMapping("/{uuidCartao}/avisos")
    public ResponseEntity<?> avisoDeViagem(@PathVariable String uuidCartao, @Valid @RequestBody AvisoViagemRequest avisoViagemRequest,
                                                                       HttpServletRequest request) throws DocumentException {
        Cartao possivelCartao = cartaoRepository.findByUuid(uuidCartao)
                .orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND, "Cartão inválido"));

        AvisoViagemRequestApiExterna avisoViagemRequestApiExterna =
                new AvisoViagemRequestApiExterna(avisoViagemRequest.getDestino(),
                        avisoViagemRequest.getValidoAte());

                logger.warn("Tentando conectar com api externa");
                mandaAvisoParaApiExterna(uuidCartao,avisoViagemRequestApiExterna);
                logger.info("Saindo da conexao");

              AvisoDeViagem avisoDeViagem = avisoViagemRequest.toModel(request,uuidCartao);
              avisoDeViagemRepository.save(avisoDeViagem);
              return ResponseEntity.ok().build();

    }
    private void mandaAvisoParaApiExterna(String uuidCartao, AvisoViagemRequestApiExterna request){
        try{
            logger.info("Dentro da conexao com a api externa");
            solicitaCartaoFeign.avisoViagemParaApiExterna(uuidCartao, request);
        } catch (FeignException e){
            new  DocumentException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel mandar o aviso de viagem");
        }
    }
}
