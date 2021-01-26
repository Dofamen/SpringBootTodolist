package ma.emsi.todolist.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilisateurID;
    private String nomComple;
    private String adressmMil;
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private Set<Liste> listes = new HashSet<>();


    public Utilisateur(String nomComple, String adressmMil, String motDePasse) {
        this.nomComple = nomComple;
        this.adressmMil = adressmMil;
        this.motDePasse = motDePasse;
    }

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "utilisateurID=" + utilisateurID +
                ", nomComple='" + nomComple + '\'' +
                ", adressmMil='" + adressmMil + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }

    public Set<Liste> getListe() {
        return listes;
    }

    public void setListe(Set<Liste> liste) {
        this.listes = liste;
    }

    public Long getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(Long utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public String getNomComple() {
        return nomComple;
    }

    public void setNomComple(String nomComple) {
        this.nomComple = nomComple;
    }

    public String getAdressmMil() {
        return adressmMil;
    }

    public void setAdressmMil(String adressmMil) {
        this.adressmMil = adressmMil;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
