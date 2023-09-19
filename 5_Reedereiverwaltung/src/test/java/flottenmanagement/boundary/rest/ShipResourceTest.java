package flottenmanagement.boundary.rest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ShipResourceTest {
    
    // Alle Schiffe ausgeben
    @Test
    public void testGetAllShips() {
            given()
                            .when()
                            .get("/ships")
                            .then()
                            .statusCode(200)
                            .body(
                                            "[0].id", is(1),
                                            "[0].name", is("Ever Given"),
                                            "[0].available", is(false),
                                            "[1].id", is(2),
                                            "[1].name", is("msc titanic"),
                                            "[1].available", is(false),
                                            "[2].id", is(3),
                                            "[2].name", is("black pearl"),
                                            "[2].available", is(false),
                                            "[3].id", is(4),
                                            "[3].name", is("queen annes revenge"),
                                            "[3].available", is(true));
    }

    // bestimmtes Schiff ausgeben (funktioniert noch nicht)
    @Test
    public void testGetSpecificShip() {
            given()
                            .when()
                            .get("/ships/1")
                            .then()
                            .statusCode(200)
                            .body(
                                            "[0].id", is(1),
                                            "[0].name", is("Ever Given"),
                                            "[0].available", is(false));
    }

    // Neues Schiff anlegen
    @Test
        public void testCreateShip() {
                given()
                                .body("{\"name\": \"RMS Queen Mary\", \"available\": \"true\"}")
                                .header("Content-Type", "application/json")
                                .when()
                                .post("/ships")
                                .then()
                                .statusCode(200);

                given()
                                .when().get("/ships")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].id", is(1),
                                            "[0].name", is("Ever Given"),
                                            "[0].available", is(false),
                                            "[1].id", is(2),
                                            "[1].name", is("msc titanic"),
                                            "[1].available", is(false),
                                            "[2].id", is(3),
                                            "[2].name", is("black pearl"),
                                            "[2].available", is(false),
                                            "[3].id", is(4),
                                            "[3].name", is("queen annes revenge"),
                                            "[3].available", is(true),
                                            "[4].id", is(5),
                                            "[4].name", is("RMS Queen Mary"),
                                            "[4].available", is(true));
        }

    // Schiff bearbeiten (funktioniert noch nicht)
    @Test
    public void testModifyShip() {
            given()
                            .body("{\"name\": \"RMS Queen Mary\", \"available\": \"true\"}")
                            .header("Content-Type", "application/json")
                            .when()
                            .post("/ships/2")
                            .then()
                            .statusCode(200);

            given()
                            .when()
                            .get("/ships")
                            .then()
                            .statusCode(200)
                            .body(
                                "[0].id", is(1),
                                "[0].name", is("Ever Given"),
                                "[0].available", is(false),
                                "[1].id", is(2),
                                "[1].name", is("RMS Queen Mary"),
                                "[1].available", is(true),
                                "[2].id", is(3),
                                "[2].name", is("black pearl"),
                                "[2].available", is(false),
                                "[3].id", is(4),
                                "[3].name", is("queen annes revenge"),
                                "[3].available", is(true));
    }
}
