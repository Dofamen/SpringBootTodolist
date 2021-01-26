package ma.emsi.todolist.controller;

import ma.emsi.todolist.dao.UtilisateurRepository;
import ma.emsi.todolist.model.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /*
    * GET: GET INFORMATION
    * POST:
    * POST, PUT, PATCHA:
    * DELETE:
    * */

    @GetMapping("/users")
    List<Utilisateur> getUsers(){
        return utilisateurRepository.findAll();
    }

    @GetMapping("/user/{id}")
    Utilisateur getUser(@PathVariable Long id){
        Optional<Utilisateur> user = utilisateurRepository.findById(id);

        return user.orElseThrow();
    }

    @PostMapping("/user")
    Utilisateur createUser(@RequestBody Utilisateur user){

        return this.utilisateurRepository.save(user);
    }

    @PostMapping("/user/{id}")
    Utilisateur updateUser(@PathVariable Long id, @RequestBody Utilisateur user){
        Optional<Utilisateur> usr = this.utilisateurRepository.findById(id);

        if (usr.isPresent()){
            Utilisateur u = usr.get();
            u.setAdressmMil(user.getAdressmMil());
            u.setNomComple(user.getNomComple());
            return utilisateurRepository.save(u);
        }
        return null;
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id){
        utilisateurRepository.deleteById(id);
    }

    @PostMapping("/login")
    Utilisateur login(@RequestBody Utilisateur user){
        Optional<Utilisateur> usr = utilisateurRepository.findAllByAdressmMilAndMotDePasse(user.getAdressmMil(),
                user.getMotDePasse());
        return  usr.orElseThrow();
    }


}
