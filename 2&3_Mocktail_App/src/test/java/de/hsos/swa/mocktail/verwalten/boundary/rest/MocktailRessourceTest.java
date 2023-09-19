package de.hsos.swa.mocktail.verwalten.boundary.rest;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MocktailRessourceTest {

        // Alle Mocktails ausgeben
        @Test
        public void testGetAllMocktails() {
                given()
                                .when().get("/mocktails")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].name", is("Sex on the couch"),
                                                "[0].beschreibung", is("Kein Alkohol, nur Saft"),
                                                "[1].name", is("Short island icetea"),
                                                "[1].beschreibung", is("Hauptsache viel Zucker"),
                                                "[2].name", is("Mini Mojito"),
                                                "[2].beschreibung", is("Viele Limetten"),
                                                "[3].name", is("Jumbo Jet"),
                                                "[3].beschreibung", is("mit Alkohol"));
        }

        // Erzeugen eines Mocktail
        @Test
        public void testCreateMocktail() {
                given()
                                .body("{\"name\": \"geilerMocktail\", \"beschreibung\": \"Das ist der beste Mocktail von allen!\"}")
                                .header("Content-Type", "application/json")
                                .when()
                                .post("/mocktails")
                                .then()
                                .statusCode(200);

                given()
                                .when().get("/mocktails")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].name", is("Sex on the couch"),
                                                "[0].beschreibung", is("Kein Alkohol, nur Saft"),
                                                "[1].name", is("Short island icetea"),
                                                "[1].beschreibung", is("Hauptsache viel Zucker"),
                                                "[2].name", is("Mini Mojito"),
                                                "[2].beschreibung", is("Viele Limetten"),
                                                "[3].name", is("Jumbo Jet"),
                                                "[3].beschreibung", is("mit Alkohol"),
                                                "[4].name", is("geilerMocktail"),
                                                "[4].beschreibung", is("Das ist der beste Mocktail von allen!"));
        }

        // Verändern eines Mocktail
        @Test
        public void testModifyMocktail() {
                given()
                                .body("{\"name\": \"geilerMocktail\", \"beschreibung\": \"Das ist der beste Mocktail von allen!\"}")
                                .header("Content-Type", "application/json")
                                .when()
                                .put("/mocktails/5")
                                .then()
                                .statusCode(200);

                given()
                                .when().get("/mocktails")
                                .then()
                                .statusCode(200)
                                .body(
                                                "[0].name", is("Sex on the couch"),
                                                "[0].beschreibung", is("Kein Alkohol, nur Saft"),
                                                "[1].name", is("Short island icetea"),
                                                "[1].beschreibung", is("Hauptsache viel Zucker"),
                                                "[2].name", is("Mini Mojito"),
                                                "[2].beschreibung", is("Viele Limetten"),
                                                "[3].name", is("Jumbo Jet"),
                                                "[3].beschreibung", is("mit Alkohol"),
                                                "[4].name", is("geilerMocktail"),
                                                "[4].beschreibung", is("Das ist der beste Mocktail von allen!"));
        }
}
