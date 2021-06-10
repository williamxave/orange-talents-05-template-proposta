package br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao;

import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.repositories.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeraCartoesConectandoComApiExterna {
    private final Logger logger = LoggerFactory.getLogger(SolicitaCartaoResponse.class);

    @Autowired
    private SolicitaCartaoFeign solicitaCartaoFeign;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CriarCartao criarCartao;

    //Scheduled vai ficar executando esse método a cada x tempo que estipularmos, tempo está como variavel de ambiente
    //O Método vai até o banco de dados buscar todas as propostas elegiveis salvando em uma lista
    //Usei o for para fazer a lista e executar o codigo dentro do try para cada objeto, conectando na api externa e recebendo
    //um cartao, com base no json recebido crio um cartao relacionando o msmo com a proposta e salvo no banco.

    @Scheduled(fixedDelayString = "${delay.scheduled}")
    void geraCartao(){
        List<Proposta> propostasEligiveis = propostaRepository.listaProspostaElegiveis();
        logger.info("Verificando propostas elegiveis! Quantidade de propostas na fila: " +propostasEligiveis.size());
        for(Proposta propostas : propostasEligiveis) {
            try {
                SolicitaCartaoResponse solicitaCartaoResponse = solicitaCartaoFeign.solicitaCartao(propostas.getId());
                Cartao cartao = criarCartao.criaCartao(solicitaCartaoResponse);
                propostas.setCartao(cartao);
                propostaRepository.save(propostas);
            }catch (FeignException e) {
                new DocumentException(HttpStatus.BAD_REQUEST,"Não foi possivel gerar o cartão");
                logger.error(e.getMessage());
                logger.warn(e.request().toString());
                logger.warn(e.request().url());
            }
        }
    }
}
