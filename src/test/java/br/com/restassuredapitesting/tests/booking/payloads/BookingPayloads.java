package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.simple.JSONObject;

import javax.xml.crypto.dsig.XMLObject;

public class BookingPayloads {

    public static JSONObject payloadValidBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");


        payload.put("firstname", "Cristiano");
        payload.put("lastname", "Ronaldo");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast");

     return payload;

    }

    public static JSONObject payloadInvalidBooking() {
        JSONObject payloadInvalid = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");


        payloadInvalid.put("firstname", false);
        payloadInvalid.put("lastname", 200);
        payloadInvalid.put("totalprice", 111);
        payloadInvalid.put("depositpaid", true);
        payloadInvalid.put("bookingdates", bookingDates);
        payloadInvalid.put("additionalneeds", "breakfast");

        return payloadInvalid;

    }

    public static JSONObject payloadValidBookingMaisParametros() {
        JSONObject payloadMaisParametros = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");


        payloadMaisParametros.put("firstname", "Cristiano");
        payloadMaisParametros.put("lastname", "Ronaldo");
        payloadMaisParametros.put("totalprice", 111);
        payloadMaisParametros.put("depositpaid", true);
        payloadMaisParametros.put("bookingdates", bookingDates);
        payloadMaisParametros.put("additionalneeds", "breakfast");

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");


        payloadMaisParametros.put("firstname", "Cristiano");
        payloadMaisParametros.put("lastname", "Ronaldo");
        payloadMaisParametros.put("totalprice", 111);
        payloadMaisParametros.put("depositpaid", true);
        payloadMaisParametros.put("bookingdates", bookingDates);
        payloadMaisParametros.put("additionalneeds", "breakfast");

        return payloadMaisParametros;

    }

}
