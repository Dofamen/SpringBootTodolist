package ma.emsi.todolist.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listeID;
    private String nom;
    private String icone;
    private String arrierPlan;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private  Utilisateur utilisateur;

    @OneToMany(mappedBy = "liste")
    @JsonIgnore
    private Set<Tache> taches = new HashSet<>();

    public Liste(String nom, String icone, String arrierPlan, Utilisateur utilisateur) {
        this.nom = nom;
        this.icone = icone;
        this.arrierPlan = arrierPlan;
        this.utilisateur = utilisateur;
    }

    public Liste() {
    }

    @Override
    public String toString() {
        return "Liste{" +
                "listeID=" + listeID +
                ", nom='" + nom + '\'' +
                ", icone='" + icone + '\'' +
                ", arrierPlan='" + arrierPlan + '\'' +
                '}';
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Long getListeID() {
        return listeID;
    }

    public void setListeID(Long listeID) {
        this.listeID = listeID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getArrierPlan() {
        return arrierPlan;
    }

    public void setArrierPlan(String arrierPlan) {
        this.arrierPlan = arrierPlan;
    }
}
