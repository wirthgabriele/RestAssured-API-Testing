package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.notNullValue;

@Feature("Feature - Criação de Reserva")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test // deveria retornar statuscode 201 (created)
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar uma nova reserva")
    public void criaNovaReserva() {
        postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void criaReservaInvalida() {
        postBookingRequest.postBookingReturnInvalido()
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER) // deveria retornar statuscode 201 (created)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criaNovaReservaMaisParametros() {
        postBookingRequest.postBookingReturnMaisParametros()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void criaNovaReservaAcceptInvalid() {
        postBookingRequest.postBookingReturnAcceptInvalid()
                .then()
                .statusCode(418);
    }

    @Test
    @Severity(SeverityLevel.NORMAL) // deveria retornar statuscode 201
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Validar a criação de mais de um livro em sequencia")
    public void criaMaisDeUmaReserva() {
        postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());
        postBookingRequest.postBookingReturn()
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());
    }

}
