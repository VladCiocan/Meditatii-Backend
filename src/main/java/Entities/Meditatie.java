package Entities;


import java.sql.Timestamp;

/**
 * Created by dioni on 2/15/2017.
 */
public class Meditatie {
    private int id;
    private int room;
    private int id_elev;
    private int id_prof;
    private int status;
    private Timestamp start_time;
    private Timestamp end_time;
    private String student_key;
    private String prof_key;



    public Timestamp getEnd_time() {
        return end_time;
    }

    public String getStudent_key() {
        return student_key;
    }

    public void setStudent_key(String student_key) {
        this.student_key = student_key;
    }

    public String getProf_key() {
        return prof_key;
    }

    public void setProf_key(String prof_key) {
        this.prof_key = prof_key;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getId_elev() {
        return id_elev;
    }

    public void setId_elev(int id_elev) {
        this.id_elev = id_elev;
    }

    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }
}
