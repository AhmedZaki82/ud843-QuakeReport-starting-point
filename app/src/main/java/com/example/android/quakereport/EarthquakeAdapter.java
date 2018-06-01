package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Tsultrim on 5/19/18.
 */

public class EarthquakeAdapter extends ArrayAdapter <Earthquake> {

    static final String SEPARATOR = "of";
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakeClasses) {
        super(context, 0, earthquakeClasses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;
        Earthquake currentItem = getItem(position);

        if (convertView == null) {

            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake,parent,false);
        }

        TextView mag = (TextView) listView.findViewById(R.id.magnitude);
        TextView location = (TextView) listView.findViewById(R.id.location_offset);
        TextView primary = (TextView) listView.findViewById(R.id.primary_location);
        TextView date = (TextView) listView.findViewById(R.id.date);
        TextView time = (TextView) listView.findViewById(R.id.time);

        Double originalMag = currentItem.getMag();

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        String displayMag = decimalFormat.format(originalMag);

        mag.setText(displayMag);


        String originalLocation = currentItem.getLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(SEPARATOR)) {

            String [] parts = originalLocation.split(SEPARATOR);
            locationOffset = parts[0] + SEPARATOR;
            primaryLocation = parts[1];
        } else {

            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        location.setText(locationOffset);
        primary.setText(primaryLocation);



        Date date1 = new Date(currentItem.getDate());

        String displayDate = dateFormat(date1);
        String displayTime = timeFormat(date1);

        date.setText(displayDate);
        time.setText(displayTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentItem.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listView;
    }

    private String dateFormat(Date date) {

        SimpleDateFormat formatDate = new SimpleDateFormat("DD-MM-yyyy");

        return formatDate.format(date);
    }

    private String timeFormat(Date date) {

        SimpleDateFormat formatTime = new SimpleDateFormat("h:mm");

        return formatTime.format(date);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
