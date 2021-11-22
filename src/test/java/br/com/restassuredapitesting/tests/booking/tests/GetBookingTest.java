package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

@Feature("Feature - Retorno de Reservas")
public class GetBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs das reservas")
    public void validaListagemDeIdsDasReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar uma reserva específica")
    public void validaListagemDeReservaEspecifica() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.bookingReturnReserva(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void validaListagemDeReservaPeloFirstname() {
        Response result = postBookingRequest.postBookingReturn()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String firstname = result.path("booking.firstname");
        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("firstname", firstname, "", "", "", "", "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void validaListagemDeReservaPeloLastname() {
        Response result = postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String lastname = result.path("booking.lastname");
        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("lastname", lastname, "", "", "", "", "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test // teste falha - está retornando data maior, e deveria estar retornando data maior ou igual
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void validaListagemDeReservaPeloCheckin() {
        Response result = postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String checkin = result.path("booking.bookingdates.checkin");
        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("checkin", checkin, "", "", "", "", "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void validaListagemDeReservaPeloCheckout() {
        Response result = postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String checkout = result.path("booking.bookingdates.checkout");
        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("checkout", checkout, "", "", "", "", "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test // teste falha - erro filtro name (não existe) - filtro checkin data deveria ser igual a data buscada
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout e checkout")
    public void validaListagemDeReservaPeloCheckoutCheckout() {
        Response result = postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String checkout = result.path("booking.bookingdates.checkout");
        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("checkout", checkout, "checkout", checkout, "", "", "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin e checkout")
    public void validaListagemDeReservaPeloNomeCheckinCheckout() {
        Response result = postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .extract()
                .response();

        String name = faker.funnyName().name();
        String checkin = result.path("booking.bookingdates.checkin");
        String checkout = result.path("booking.bookingdates.checkout");

        int bookingid = result.path("bookingid");

        getBookingRequest.bookingReturnaIdsFiltros("name", name, "checkin", checkin, "checkout", checkout, "", "")
                .then()
                .statusCode(200)
                .body("bookingid", hasItem(bookingid))
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void validaErroFiltro() {
        getBookingRequest.bookingReturnaIdsFiltros("checkout", "H^12", "", "", "", "", "", "")
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno da lista de reservas")
    public void validaSchemaDaListagemDeReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookingsIds"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno de uma reserva específica")
    public void validaSchemaDeUmaReservaEspecifica() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        getBookingRequest.bookingReturnReserva(primeiroId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

}
