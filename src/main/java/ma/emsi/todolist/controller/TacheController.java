package ma.emsi.todolist.controller;

import ma.emsi.todolist.dao.ListeRepository;
import ma.emsi.todolist.dao.TacheRepository;
import ma.emsi.todolist.dao.UtilisateurRepository;
import ma.emsi.todolist.model.Liste;
import ma.emsi.todolist.model.Tache;
import ma.emsi.todolist.model.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TacheController {
    private TacheRepository tacheRepository;
    private ListeRepository listeRepository;
    private UtilisateurRepository utilisateurRepository;

    public TacheController(TacheRepository tacheRepository, ListeRepository listeRepository, UtilisateurRepository utilisateurRepository) {
        this.tacheRepository = tacheRepository;
        this.listeRepository = listeRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/taches/{uid}/{lid}")
    List<Tache> getListeTache(@PathVariable Long uid, @PathVariable Long lid) throws Exception {
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()) {
            Optional<Liste> liste = listeRepository.findById(user.get().getUtilisateurID());
            if (liste.isPresent()) {
                return tacheRepository.findAllByListe(liste.get().getListeID());
            }
        }
        throw  new Exception();
    }

    @GetMapping("/tache/{uid}/{lid}/{tid}")
    Tache getTache(@PathVariable Long uid, @PathVariable Long lid, @PathVariable Long tid)throws Exception{
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()) {
            Optional<Liste> liste = listeRepository.findById(lid);
            if (liste.isPresent()) {
                Optional<Tache> tache = tacheRepository.findById(tid);
                return tache.orElseThrow();
            }
        }
        throw new Exception();
    }

    @PostMapping("/tache/{uid}/{lid}")
    Tache createTache(@PathVariable Long uid, @PathVariable Long lid, @RequestBody Tache tache)throws Exception{
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()){
            Optional<Liste> liste = listeRepository.findById(lid);
            if (liste.isPresent()){
                Tache tach = new Tache();
                tach.setDateCreation(tache.getDateCreation());
                tach.setDateEcheance(tache.getDateEcheance());
                tach.setDescription(tache.getDescription());
                tach.setEtat(tache.isEtat());
                tach.setListe(liste.get());
                tach.setImportant(tach.isImportant());
                tacheRepository.save(tach);
                return tach;
            }
        }
        throw  new Exception();
    }

    @PostMapping("/tache/{uid}/{lid}/{tid}")
    Tache updateTache(@PathVariable Long uid, @PathVariable Long lid, @PathVariable Long tid, @RequestBody Tache tach) throws Exception{
        Optional<Utilisateur> user = utilisateurRepository.findById(uid);
        if (user.isPresent()){
            Optional<Liste> liste = listeRepository.findById(lid);
            if (liste.isPresent()){
                Optional<Tache> tache = tacheRepository.findById(tid);
                if (tache.isPresent()){
                    Tache t = tache.get();
                    if (t.getListe().getListeID().equals(liste.get().getListeID()) &&
                            t.getListe().getUtilisateur().getUtilisateurID().equals(user.get().getUtilisateurID())) {
                        t.setImportant(tach.isImportant());
                        t.setEtat(tach.isEtat());
                        if (tach.getListe() != null) t.setListe(tach.getListe());
                        if (tach.getDescription() != null) t.setDescription(tach.getDescription());
                        if (tach.getDateEcheance() != null) t.setDateEcheance(tach.getDateEcheance());

                        tacheRepository.save(t);
                    }
                }
            }
        }

        throw new Exception();
    }

    @DeleteMapping("/tache/{tid}")
    void deleteTache(@PathVariable Long tid){
        tacheRepository.deleteById(tid);
    }

}
