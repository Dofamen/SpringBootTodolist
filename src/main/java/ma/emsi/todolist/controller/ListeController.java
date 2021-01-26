package ma.emsi.todolist.controller;

import ma.emsi.todolist.dao.ListeRepository;
import ma.emsi.todolist.dao.UtilisateurRepository;
import ma.emsi.todolist.model.Liste;
import ma.emsi.todolist.model.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ListeController {
    private ListeRepository listeRepository;
    private UtilisateurRepository utilisateurRepository;

    public ListeController(ListeRepository listeRepository, UtilisateurRepository utilisateurRepository) {
        this.listeRepository = listeRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/userlists/{id}")
    List<Liste> getUserLists(@PathVariable Long id){
        return this.listeRepository.rechercheByUtitlisateur(id);
    }

    @GetMapping("/liste/{id}")
    Liste getListebyID(@PathVariable Long id){
        Optional<Liste> liste = listeRepository.findById(id);
        return  liste.orElseThrow();
    }

    @PostMapping("/liste/{uid}")
    Liste createList(@PathVariable Long uid, @RequestBody Liste liste)throws Exception{
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()) {
            liste.setUtilisateur(user.get());
            listeRepository.save(liste);
        }
        throw new Exception();
    }

    @PostMapping("/liste/{uid}/{lid}")
    Liste updateList(@PathVariable Long uid,@PathVariable Long lid, @RequestBody Liste liste)throws Exception{
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()) {
            Optional<Liste> lst = listeRepository.findById(lid);
            if (lst.isPresent()){
                Utilisateur usr = user.get();
                Liste l = lst.get();
                if (l.getUtilisateur().getUtilisateurID().equals(usr.getUtilisateurID())){
                    if (liste.getArrierPlan()!=null) l.setArrierPlan(liste.getArrierPlan());
                    if (liste.getNom()!=null) l.setNom(liste.getNom());
                    if (l.getIcone()!=null) l.setIcone(liste.getIcone());

                    listeRepository.save(l);
                }
            }
        }
        throw new Exception();
    }

    @DeleteMapping("/liste/{lid}")
    void deleteListe(@PathVariable Long lid){
        listeRepository.deleteById(lid);
    }

}
