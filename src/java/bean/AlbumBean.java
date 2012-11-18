package bean;

public class AlbumBean {

    int idAlbum;
    int idUser;
    int idStatut;

    public AlbumBean() {}

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
}
