package dev.dto;

import java.util.List;

import dev.domain.ResaVehicule;
import dev.domain.Vehicule;

public class VehiculeDetailsDTO {

		private Vehicule vehicule;
		private List<ResaVehiculeLightDTO> listResasEnCours;
		private List<ResaVehiculeLightDTO> listResasPassees;
				
		
		public VehiculeDetailsDTO() {
			super();
		}

		

		public VehiculeDetailsDTO(Vehicule vehicule, List<ResaVehiculeLightDTO> listResasEnCours,
				List<ResaVehiculeLightDTO> listResasPassees) {
			super();
			this.vehicule = vehicule;
			this.listResasEnCours = listResasEnCours;
			this.listResasPassees = listResasPassees;
		}



		public Vehicule getVehicule() {
			return vehicule;
		}


		public void setVehicule(Vehicule vehicule) {
			this.vehicule = vehicule;
		}



		public List<ResaVehiculeLightDTO> getListResasEnCours() {
			return listResasEnCours;
		}



		public void setListResasEnCours(List<ResaVehiculeLightDTO> listResasEnCours) {
			this.listResasEnCours = listResasEnCours;
		}



		public List<ResaVehiculeLightDTO> getListResasPassees() {
			return listResasPassees;
		}



		public void setListResasPassees(List<ResaVehiculeLightDTO> listResasPassees) {
			this.listResasPassees = listResasPassees;
		}

		


		
		
		
		
		
	
}
