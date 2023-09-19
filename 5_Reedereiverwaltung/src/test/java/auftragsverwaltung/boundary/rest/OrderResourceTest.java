package auftragsverwaltung.boundary.rest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {

        // Alle Aufträge ausgeben
        @Test
        public void testGetAllOrders() {
            given()
                            .when()
                            .get("/orders")
                            .then()
                            .statusCode(200)
                            .body(
                                            "[0].orderId", is(1),
                                            "[0].description", is("Lieferung von Vorräten zum Nordpol"),
                                            "[0].orderReceivementDate", is("05.05.2023"),
                                            "[1].orderId", is(2),
                                            "[1].description", is("Raub der isla de muerta"),
                                            "[1].orderReceivementDate", is("05.05.2023"));
    }

        // bestimmten Auftrag ausgeben (funktioniert noch nicht)
        @Test
        public void testGetSpecificOrder() {
            given()
                            .when()
                            .get("/orders/1")
                            .then()
                            .statusCode(200)
                            .body(
                                            "[0].orderId", is(2),
                                            "[0].description", is("Raub der isla de muerta"),
                                            "[0].orderReceivementDate", is("05.05.2023"));
    }

        // Neuen Auftrag anlegen
        @Test
        public void testCreateOrder() {
                given()
                                .body("{\"description\": \"Just another order.\", \"orderReceivementDate\": \"05.05.2023\"}")
                                .header("Content-Type", "application/json")
                                .when()
                                .post("/orders")
                                .then()
                                .statusCode(200);

                given()
                                .when()
                                .get("/orders")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].orderId", is(1),
                                            "[0].description", is("Lieferung von Vorräten zum Nordpol"),
                                            "[0].orderReceivementDate", is("05.05.2023"),
                                            "[1].orderId", is(2),
                                            "[1].description", is("Raub der isla de muerta"),
                                            "[1].orderReceivementDate", is("05.05.2023"),
                                            "[2].orderId", is(3),
                                            "[2].description", is("Just another order."),
                                            "[2].orderReceivementDate", is("05.05.2023"));
        }

        // Ändern eines Auftrags (funktioniert noch nicht)
        @Test
        public void testModifyOrder() {
                given()
                                .body("{\"description\": \"Just another changed order.\", \"orderReceivementDate\": \"06.05.2023\"}")
                                .header("Content-Type", "application/json")
                                .when()
                                .put("/orders/2")
                                .then()
                                .statusCode(200);

                given()
                                .when()
                                .get("/orders")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].orderId", is(1),
                                            "[0].description", is("Lieferung von Vorräten zum Nordpol"),
                                            "[0].orderReceivementDate", is("05.05.2023"),
                                            "[1].orderId", is(2),
                                            "[1].description", is("Just another changed order."),
                                            "[1].orderReceivementDate", is("05.05.2023"));
        }

        // Löschen eines Auftrags
        @Test
        public void testDeleteOrder() {
                given()
                                .header("Content-Type", "application/json")
                                .when()
                                .delete("/orders/1")
                                .then()
                                .statusCode(200);

                given()
                                .when()
                                .get("/orders")
                                .then()
                                .statusCode(200)
                                .body(
                                            "[0].orderId", is(2),
                                            "[0].description", is("Raub der isla de muerta"),
                                            "[0].orderReceivementDate", is("05.05.2023"));
        }
}
