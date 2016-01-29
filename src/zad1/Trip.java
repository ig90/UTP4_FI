package zad1;

import java.util.Locale;

public class Trip {
    public static final String SEA = "place.sea";
    public static final String LAKE = "place.lake";
    public static final String MOUNTAINS = "place.mountains";

    private String locale;
    private String city;
    private String dateFrom;
    private String dateTo;
    private String place;
    private String price;
    private String currency;

    public Trip(String locale, String city, String dateFrom, String dateTo, String place, String price, String currency) {
        this.locale = locale;
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.place = place;
        this.price = price;
        this.currency = currency;
    }

    public String getCity() {
        return city;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getPrice() {
        return price;
    }

    public String getPlace() {
        return place;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return "('" + locale +"', '" + city +"', '" + dateFrom +"', '" + dateTo +"', '" + place +"', '" + price +"', '" + currency +"')";
    }
}
