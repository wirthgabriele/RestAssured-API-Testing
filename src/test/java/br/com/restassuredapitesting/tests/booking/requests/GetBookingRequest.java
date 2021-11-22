package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da Listagem de Reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna uma reserva específica da Listagem de Reservas")
    public Response bookingReturnReserva(int id) {
        return given()
                .when()
                .get("booking/"+id);
    }

    @Step("Retorna Ids de reservas utilizando os filtros")
    public Response bookingReturnaIdsFiltros(String filtro1, String valor1, String filtro2, String valor2, String filtro3, String valor3, String filtro4, String valor4) {
        return given()
                .header("Content-Type", "application/json")
                .params(filtro1, valor1, filtro2, valor2, filtro3, valor3, filtro4, valor4)
                .log().all()
                .when()
                .get("booking");
    }


}
