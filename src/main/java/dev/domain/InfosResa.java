package dev.domain;

public class InfosResa {

    private String heureDepart;
    private String minuteDepart;
    private String dateDepart;
    private String heureRetour;
    private String minuteRetour;
    private String dateRetour;
    private Vehicule vehiculeSociete;

    public InfosResa() {
    }

    public InfosResa(String heureDepart, String minuteDepart, String dateDepart, String heureRetour,
            String minuteRetour, String dateRetour, Vehicule vehiculeSociete) {
        this.heureDepart = heureDepart;
        this.minuteDepart = minuteDepart;
        this.dateDepart = dateDepart;
        this.heureRetour = heureRetour;
        this.minuteRetour = minuteRetour;
        this.dateRetour = dateRetour;
        this.vehiculeSociete = vehiculeSociete;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getMinuteDepart() {
        return minuteDepart;
    }

    public void setMinuteDepart(String minuteDepart) {
        this.minuteDepart = minuteDepart;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureRetour() {
        return heureRetour;
    }

    public void setHeureRetour(String heureRetour) {
        this.heureRetour = heureRetour;
    }

    public String getMinuteRetour() {
        return minuteRetour;
    }

    public void setMinuteRetour(String minuteRetour) {
        this.minuteRetour = minuteRetour;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Vehicule getVehiculeSociete() {
        return vehiculeSociete;
    }

    public void setVehiculeSociete(Vehicule vehiculeSociete) {
        this.vehiculeSociete = vehiculeSociete;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InfosResa [heureDepart=");
        builder.append(heureDepart);
        builder.append(", minuteDepart=");
        builder.append(minuteDepart);
        builder.append(", dateDepart=");
        builder.append(dateDepart);
        builder.append(", heureRetour=");
        builder.append(heureRetour);
        builder.append(", minuteRetour=");
        builder.append(minuteRetour);
        builder.append(", dateRetour=");
        builder.append(dateRetour);
        builder.append(", vehiculeSociete=");
        builder.append(vehiculeSociete);
        builder.append("]");
        return builder.toString();
    }

}
