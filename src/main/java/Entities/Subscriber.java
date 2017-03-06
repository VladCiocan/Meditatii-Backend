package Entities;

import java.sql.Timestamp;

/**
 * Created by dioni on 3/6/2017.
 */
public class Subscriber {
    private int id;
    private String email;
    private Timestamp timp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getTimp() {
        return timp;
    }

    public void setTimp(Timestamp timp) {
        this.timp = timp;
    }
}
