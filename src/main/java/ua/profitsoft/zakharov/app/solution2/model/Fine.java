package ua.profitsoft.zakharov.app.solution2.model;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Fine {
    private String first_name;
    private String last_name;
    private String type;
    private Date date_time;
    private Double fine_amount;

    public void setDate_time(String date_time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        try {
            this.date_time = dateFormat.parse(date_time);
        } catch (ParseException exp) {
            throw new RuntimeException("Error in converting date");
        }
    }

    public String getDate_time() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        return dateFormat.format(this.date_time);
    }

}
