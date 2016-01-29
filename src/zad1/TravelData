package zad1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TravelData {
    private List<Trip> offers;

    public  TravelData(File file){
        offers = new ArrayList<Trip>();
        try {
        	 for(File f : file.listFiles())readFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(File file) throws IOException {
        FileInputStream fstream = null;
        BufferedReader reader = null;

        try {
            fstream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fstream));

            String line = null;
            while((line = reader.readLine()) != null) offers.add(evaluate(line));
        } finally {
            fstream.close();
            reader.close();
        }
    }

    private Trip evaluate(String data){
        String[] d = data.split("\t");
        return new Trip(d[0], d[1], d[2], d[3], d[4], d[5], d[6]);
    }

    static Locale getCountryLocale(Locale loc, String name){
        for(Locale l : Locale.getAvailableLocales()){
            if(l.getDisplayCountry(loc).equals(name)) {
                return l;
            }
        }

        return loc;
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        List<String> result = new ArrayList<String>();
        Locale l = getLocaleFromString(locale);
        ResourceBundle rb = ResourceBundle.getBundle("Messages", getLocaleFromString(locale));
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat(dateFormat);

        for(Trip t : offers){
            try {
                String line = getCountryLocale(getLocaleFromString(t.getLocale()), t.getCity()).getDisplayCountry(l) + " " + output.format(input.parse(t.getDateFrom())) + " " + output.format(input.parse(t.getDateTo())) +
                        " " + rb.getString(getPlaceKey(t)) + " " + t.getPrice() + " " + t.getCurrency();
                result.add(line);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    static Locale getLocaleFromString(String data){
        String[] sdata = data.split("_");
        if(sdata.length == 1) return new Locale(data);

        return new Locale(sdata[0], sdata[1]);
    }

    static String getPlaceKey(Trip trip){
        ResourceBundle rs = ResourceBundle.getBundle("Messages", getLocaleFromString(trip.getLocale()));
        if(rs.getString(Trip.SEA).equals(trip.getPlace())) return Trip.SEA;
        if(rs.getString(Trip.LAKE).equals(trip.getPlace())) return Trip.LAKE;
        if(rs.getString(Trip.MOUNTAINS).equals(trip.getPlace())) return Trip.MOUNTAINS;

        return null;
    }

    public List<Trip> getOffers() {
        return offers;
    }
}
