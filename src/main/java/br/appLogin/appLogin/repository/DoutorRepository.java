package br.appLogin.appLogin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.appLogin.appLogin.model.Doutor;

public interface DoutorRepository extends JpaRepository<Doutor, Long> {

    // Login do médico
    Doutor findByEmailAndSenha(String email, String senha);

    // Listar médicos que aceitam determinado plano de saúde
    List<Doutor> findByPlanoAtendido(String planoSaude);
    
    
    
    // Busca por nome ou especialidade (usado na filtragem)
    @Query("SELECT d FROM Doutor d WHERE " +
            "(:nome IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especialidade IS NULL OR LOWER(d.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%')))")
    List<Doutor> findAllByNomeOrEspecialidade(String nome, String especialidade);

    // Busca por plano atendido, nome ou especialidade combinados
    @Query("SELECT d FROM Doutor d WHERE " +
            "(:planoAtendido IS NULL OR LOWER(d.planoAtendido) = LOWER(:planoAtendido)) AND " +
            "(:nome IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especialidade IS NULL OR LOWER(d.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%')))")
    List<Doutor> findByPlanoAtendidoAndNomeOrEspecialidade(String planoAtendido, String nome, String especialidade);


        // Buscar médicos por nome ou especialidade (paciente sem plano de saúde)
    List<Doutor> findByNomeContainingIgnoreCaseOrEspecialidadeContainingIgnoreCase(String nome, String especialidade);

    // Buscar médicos pelo plano atendido e filtro (paciente com plano de saúde)
    List<Doutor> findByPlanoAtendidoAndNomeContainingIgnoreCaseOrEspecialidadeContainingIgnoreCase(
            String planoSaude, String nome, String especialidade);
}

