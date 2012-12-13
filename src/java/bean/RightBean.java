package bean;

public class RightBean {

    int idUser;
    int idAlbum;
    boolean lire;
    boolean inserer;
    boolean modifier;
    boolean supprimer;

    public RightBean() {
        this.idUser = -1;
        this.idAlbum = -1;
        this.lire = true;
        this.inserer = false;
        this.modifier = false;
        this.supprimer = false;
    }
    
    public RightBean(int idUser, int idAlbum, boolean lire, boolean inserer, boolean modifier, boolean supprimer) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
        this.lire = lire;
        this.inserer = inserer;
        this.modifier = modifier;
        this.supprimer = supprimer;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public boolean isLire() {
        return lire;
    }

    public void setLire(boolean lire) {
        this.lire = lire;
    }

    public boolean isInserer() {
        return inserer;
    }

    public void setInserer(boolean inserer) {
        this.inserer = inserer;
    }

    public boolean isModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }
    
}
