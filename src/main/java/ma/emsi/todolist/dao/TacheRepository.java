package ma.emsi.todolist.dao;

import ma.emsi.todolist.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    @Query(nativeQuery = true, value = "SELECT tach FROM Tache WHERE liste_id=:lid")
    List<Tache> findAllByListe(@Param("lid") Long lid);

    @Query(nativeQuery = false, value = "SELECT tach FROM Tache tach WHERE tach.liste.utilisateur.utilisateurID = : uid")
    List<Tache> findAllByUtilisateur(@Param("uid") Long uid);
}
