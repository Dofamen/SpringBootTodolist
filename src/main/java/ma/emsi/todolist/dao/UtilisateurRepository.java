package ma.emsi.todolist.dao;

import ma.emsi.todolist.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findAllByAdressmMilAndMotDePasse(String adressmMil, String motDePasse);
}
