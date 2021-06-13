package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.Biometria;
import br.com.zupacademy.projetoproposta.models.BloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {
}
