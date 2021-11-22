package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void validarAlteraçãoDeUmaReservaUtilizandoToken() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void validarAlteraçãoDeUmaReservaUtilizandoBasicAuth() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingBasicAuth(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void validarAlteraçãoDeUmaReservaSemToken() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingSemToken(primeiroId)
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void validarAlteraçãoDeUmaReservaTokenInvalido() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingTokenInvalido(primeiroId)
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void validarAlteraçãoDeUmaReservaNaoExiste() {
        int idInexistente = 999;

        putBookingRequest.updateBookingToken(idInexistente, postAuthRequest.getToken())
                .then()
                .statusCode(405);

    }
}
