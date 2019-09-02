package dev.controller.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.domain.Collegue;
import dev.domain.Role;

/**
 * Structure modèlisant un collègue chauffeur servant à communiquer avec l'extérieur (WEB API).
 */

public class ChauffeurVM {
	
		private Long id;
		private String matricule;
	    private String email;
	    private String nom;
	    private String prenom;
	    private String telephone;
	    private String permis;	    
	    private List<Role> roles = new ArrayList<>();

	    

	    public ChauffeurVM(Collegue col) {
			
			this.id = col.getId();
			this.matricule = col.getMatricule();
			this.email = col.getEmail();
			this.nom = col.getNom();
			this.prenom = col.getPrenom();
			this.telephone = col.getTelephone();
			this.permis = col.getPermis();
			this.roles = col.getRoles().stream().map(roleCollegue -> roleCollegue.getRole()).collect(Collectors.toList());
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getPermis() {
			return permis;
		}

		public void setPermis(String permis) {
			this.permis = permis;
		}

		public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
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

	    public List<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(List<Role> roles) {
	        this.roles = roles;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}

		
		
	}
