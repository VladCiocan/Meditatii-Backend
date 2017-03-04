package Entities;

/**
 * Created by dioni on 2/22/2017.
 */
public class Programare {
    private int id;
    private int id_prof;
    private int id_elev;
    private int duration;
    private String time;
    private int status;
    private Profesor p;
    private int room;



    public int getId() {
        return id;
    }

    public Profesor getP() {
        return p;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setP(Profesor p) {
        this.p = p;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public int getId_elev() {
        return id_elev;
    }

    public void setId_elev(int id_elev) {
        this.id_elev = id_elev;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
