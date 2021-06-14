package br.com.zupacademy.projetoproposta.health;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ConfigMetricas {

    private final MeterRegistry meterRegistry;
    private Counter contadorDePropostasCriadas;

    //MeterRegistry que é a classe principal para criar métricas
    public ConfigMetricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.contadorDePropostas();
    }

    //Counter, metrica para contador a quantidade de propostas criadas usando o método increment la no construtor
    public void contadorDePropostas() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "william-propostas-api"));
        tags.add(Tag.of("banco", "Itaú"));
         this.contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
    }

    public void addProposta(){
        this.contadorDePropostasCriadas.increment();
    }
}

