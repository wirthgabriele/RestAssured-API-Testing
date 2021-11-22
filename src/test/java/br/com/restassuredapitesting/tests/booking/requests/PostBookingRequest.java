package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();


    @Step("Cria uma nova reserva")
    public Response postBookingReturn() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }

    @Step("Valida retorno 500 com payload inválido")
    public Response postBookingReturnInvalido() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadInvalidBooking().toString())
                .post("booking");
    }

    @Step("Cria uma reserva enviando mais parâmetros no payload")
    public Response postBookingReturnMaisParametros() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBookingMaisParametros().toString())
                .post("booking");
    }

    @Step("Cria uma nova reserva Accept Inválido")
    public Response postBookingReturnAcceptInvalid() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "aapplication/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }

}
