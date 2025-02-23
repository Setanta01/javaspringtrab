package br.appLogin.appLogin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.appLogin.appLogin.model.Avaliacao;
import br.appLogin.appLogin.model.Doutor;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Listar avaliações de um médico
    List<Avaliacao> findByDoutor(Doutor doutor);

    // Avaliações relacionadas a uma consulta
List<Avaliacao> findByDoutorOrderByIdDesc(Doutor doutor);
}
