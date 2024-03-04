package com.example.eclipse;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.MGRSRef;
import uk.me.jstott.jcoord.ellipsoid.ModifiedEverestEllipsoid;
import uk.me.jstott.jcoord.datum.OSGB36Datum;
import uk.me.jstott.jcoord.datum.WGS84Datum;
import uk.me.jstott.jcoord.solar.SolarPosition;


public class MainActivity extends AppCompatActivity {

    /*@SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.menu);
        TextView txt2 = findViewById(R.id.txt2);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        txt2.setText("The last total Solar eclipse was on " + formattedDate);
    }*/

    public static void main(String[] args) {
        try {
            // Set the date range
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date startDate = dateFormat.parse("2024-01-01");
            Date endDate = dateFormat.parse("2024-12-31");

            // Iterate through each day in the date range
            Date currentDate = startDate;
            while (currentDate.compareTo(endDate) <= 0) {
                boolean eclipse = isSolarEclipse(currentDate);
                if (eclipse) {
                    System.out.println("Solar eclipse on: " + dateFormat.format(currentDate));
                }
                // Increment date by one day
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.DATE, 1);
                currentDate = calendar.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSolarEclipse(Date date) {
        try {
            // Calculate the Sun's position
            SolarPosition solarPosition = new SolarPosition(date, 0, 0);

            // Calculate the Moon's position (not implemented in JCoord, so using a dummy position)
            LatLng moonPosition = new LatLng(0, 0);

            // Calculate the angular separation between the Sun and the Moon
            double angularSeparation = solarPosition.calculateSolarEclipseAngle(moonPosition);

            // Check if the angular separation is close to 0 (indicating a solar eclipse)
            return Math.abs(angularSeparation) < 0.5; // Adjust this threshold as needed
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

}
