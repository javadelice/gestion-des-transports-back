package dev.exception;

import java.util.HashMap;
import java.util.Map;

public class VehiculeInvalideException extends RuntimeException {
    private Map<String,String> erreurs= new HashMap<>();
    public VehiculeInvalideException(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
}
