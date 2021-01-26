package ma.emsi.todolist.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tacheID;
    private String description;
    private Date dateCreation;
    private Date dateEcheance;
    private boolean etat;
    private boolean important;



    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "liste_id")
    @JsonIgnore
    private Liste liste;

    public Tache(String description, Date dateCreation, Date dateEcheance, boolean etat, boolean important, Liste liste) {
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
        this.etat = etat;
        this.important = important;
        this.liste = liste;
    }

    public Tache() {
    }

    @Override
    public String toString() {
        return "Tache{" +
                "tacheID=" + tacheID +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateEcheance=" + dateEcheance +
                ", etat=" + etat +
                ", important=" + important +
                '}';
    }

    public Liste getListe() {
        return liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }


    public Long getTacheID() {
        return tacheID;
    }

    public void setTacheID(Long tacheID) {
        this.tacheID = tacheID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
