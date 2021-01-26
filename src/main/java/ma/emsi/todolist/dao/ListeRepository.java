package ma.emsi.todolist.dao;

import ma.emsi.todolist.model.Liste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListeRepository extends JpaRepository<Liste, Long> {
    // NORMAL SQL QUERY
    @Query(nativeQuery = true, value = "SELECT * FROM liste WHERE utilisateur_id = :id")
    List<Liste> findAllByUtilisateurID(@Param("id") Long id);

    // WITH LINQ QUERY
    @Query("SELECT lis FROM Liste lis WHERE lis.utilisateur.utilisateurID = :userID")
    List<Liste> rechercheByUtitlisateur(@Param("userID") Long id);
}
