package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Excluir um reserva com sucesso")
    public Response deleteBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/" + id);
    }

    @Step("Excluir um reserva com sucesso")
    public Response deleteBookingSemToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete("booking/" + id);
    }
}
