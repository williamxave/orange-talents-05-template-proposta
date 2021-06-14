package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.AvisoDeViagem;
import br.com.zupacademy.projetoproposta.models.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoDeViagemRepository extends JpaRepository<AvisoDeViagem, Long> {
}
