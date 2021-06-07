package br.com.zupacademy.projetoproposta.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.net.URL;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    private Logger logger  = LoggerFactory.getLogger(CustomHealthIndicator.class);

    @Value("${analise_documento.url}/proposta")
    private String URL;

    // O método getHost () da classe URL retorna o nome do host do URL. Este método retornará o endereço IPv6 entre colchetes ('[' e ']'). localhost:9999/api
    // De acordo com a própria Oracle: “Socket é um ponto de comunicação entre duas máquinas”,
    // ou seja, podemos enviar mensagens entre a máquina A e a máquina B através de uma conexão estabelecida com o Socket.
    // Aqui estamos usando para verificar se nossa aplicação está se comunicando ou não com a api externa
    @Override
    public Health health() {
        try (Socket socket = new Socket(new URL(URL).getHost(), 9999)) {
            logger.info("Aplicação está conectada com a api externa de validação de documento.");
        } catch (Exception e) {
            logger.error("API de validação de documentos não está respondendo! Verifique a conexão.");
            return Health.down().withDetail("error", e.getMessage()).build();
        }
        return Health.up().build();
    }
}
