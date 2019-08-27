package dev.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Collegue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String matricule;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    @OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
    private List<RoleCollegue> roles;

    
    public Collegue() {
	
	}
    
    public Collegue(String matricule, String nom, String prenom) {
		this(nom, prenom);
    	this.matricule = matricule;
		
	}
    
    public Collegue(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
    

	public Collegue(String matricule, String email, String nom, String prenom, List<RoleCollegue> roles) {
		super();
		this.matricule = matricule;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.roles = roles;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<RoleCollegue> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCollegue> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public void setRoles(Role roleChauffeur) {
		// TODO Auto-generated method stub
		
	}
    
}
