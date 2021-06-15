package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.enums.TipoDeCarteira;
import br.com.zupacademy.projetoproposta.models.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

   Optional<Carteira> findByCarteiraAndCartaoId(TipoDeCarteira tipoDeCarteira, String idCartao);
}
