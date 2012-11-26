package bean;

import bdd.RightMap;

public class AlbumBean {

    int idAlbum;
    int idUser;
    int idStatut;

    public AlbumBean() {
        this.idAlbum = -1;
        this.idUser = -1;
        this.idStatut = 0;
    }

    public AlbumBean(int idAlbum, int idUser, int idStatut) {
        this.idAlbum = idAlbum;
        this.idUser = idUser;
        this.idStatut = idStatut;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public void addUser(UserBean user, boolean lire, boolean inserer, boolean modifier, boolean supprimer) {
        RightMap map = new RightMap();
        if (map.get(user.getIdUser(), idAlbum) == null) {
            RightBean r = new RightBean(user.getIdUser(), idAlbum, lire, inserer, modifier, supprimer);
            map.save(r);
        }
    }
}
