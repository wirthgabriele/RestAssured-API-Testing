package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Exclusão de Reserva")
public class DeleteBookingTest extends BaseTest {
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test //deveria retornar statuscode 200
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Excluir um reserva com sucesso")
    public void validarExclusaoDeUmaReservaUtilizandoToken() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingToken(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201)
                .assertThat();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar excluir um reserva que não existe")
    public void validarExclusaoDeUmaReservaInexistenteUtilizandoToken() {
        int idInexistente = 9999;

        deleteBookingRequest.deleteBookingToken(idInexistente, postAuthRequest.getToken())
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar excluir uma reserva sem autorização")
    public void validarExclusaoDeUmaReservaSemAutorizacao() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingSemToken(primeiroId)
                .then()
                .statusCode(403);
    }

}
