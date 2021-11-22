package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma Reserva específica com o parâmetro token")
    public Response updateBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }

    @Step("Atualiza uma Reserva específica com o parâmetro Basic Auth")
    public Response updateBookingBasicAuth(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }





    @Step("Atualiza uma Reserva específica com token inválido")
    public Response updateBookingSemToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }

    @Step("Atualiza uma Reserva específica com token inválido")
    public Response updateBookingTokenInvalido(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "abc123")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);
    }
}
