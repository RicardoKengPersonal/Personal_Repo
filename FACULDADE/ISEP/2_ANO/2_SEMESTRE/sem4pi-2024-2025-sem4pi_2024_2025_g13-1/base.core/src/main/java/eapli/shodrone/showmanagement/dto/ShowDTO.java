package eapli.shodrone.showmanagement.dto;

import java.io.Serializable;

public class ShowDTO implements Serializable {
    public final String showID;
    public final String date;
    public final String time;
    public final String location;
    public final String status;

    public ShowDTO(String showID, String date, String time, String location, String status) {
        this.showID = showID;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Data: %s | %s ",
                showID, date, time);
    }
}
