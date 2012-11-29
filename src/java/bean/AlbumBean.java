package bean;

import bdd.PhotoMap;
import bdd.RightMap;
import java.util.ArrayList;

public class AlbumBean {

    int idAlbum;
    String nameAlbum;
    String descr;
    int idUser;
    int idStatut;

    public AlbumBean() {
        this.idAlbum = -1;
        this.nameAlbum = "";
        this.descr = "";
        this.idUser = -1;
        this.idStatut = 0;
    }

    public AlbumBean(int idAlbum, String nameAlbum, String descr, int idUser, int idStatut) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.descr = descr;
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

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public void shareUser(UserBean user, boolean lire, boolean inserer, boolean modifier, boolean supprimer) {
        RightMap map = new RightMap();
        RightBean r = new RightBean(user.getIdUser(), idAlbum, lire, inserer, modifier, supprimer);
        map.save(r);
    }

    public void removeUser(UserBean user) {
        RightMap map = new RightMap();
        map.delete(user.getIdUser(), idAlbum);
    }

    public int getNbPhoto() {
        PhotoMap mapPhoto = new PhotoMap();
        ArrayList<PhotoBean> photos = mapPhoto.getAllbyAttr("idAlbum", idAlbum);
        return photos.size();
    }
}
