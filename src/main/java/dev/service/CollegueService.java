package dev.service;

import dev.domain.Collegue;
import dev.exceptions.CollegueNonTrouveException;
import dev.repository.CollegueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class CollegueService {
	
	@Autowired
    private CollegueRepo collegueRepo;

	public CollegueService() {

    }
	
	public Collegue chercherParEmail (String email) {
		return collegueRepo.findByEmail(email)
				.orElseThrow(() -> new CollegueNonTrouveException());
	}


}
