package dev.exception;

import java.util.HashMap;
import java.util.Map;

public class DateReservationVehiculeInvalide extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Map <String, String> erreurs = new HashMap<>();

    public DateReservationVehiculeInvalide(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    public Map <String, String> getErreurs (){
        return erreurs;
    }
}
