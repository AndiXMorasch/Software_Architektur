package boundary.rest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PlayerResourceTest {
    @Test
        public void testGetAllPlayers() {
                given()
                                .when().get("/players")
                                .then()
                                .statusCode(200)
                                .body(
                                                "data[0].id", is("1"),
                                                "data[0].type", is("persons"),
                                                "data[0].attributes.name", is("Leroy Sané"),
                                                "data[0].attributes.condition", is("fit"),
                                                "data[0].links.self", is("http://localhost:8081/players/1"),
                                                "data[1].id", is("2"),
                                                "data[1].type", is("persons"),
                                                "data[1].attributes.name", is("Robert Lewandowski"),
                                                "data[1].attributes.condition", is("fit"),
                                                "data[1].links.self", is("http://localhost:8081/players/2"),
                                                "data[2].id", is("3"),
                                                "data[2].type", is("persons"),
                                                "data[2].attributes.name", is("Manuel Neuer"),
                                                "data[2].attributes.condition", is("verletzt"),
                                                "data[2].links.self", is("http://localhost:8081/players/3"),
                                                "data[3].id", is("4"),
                                                "data[3].type", is("persons"),
                                                "data[3].attributes.name", is("Marco Reus"),
                                                "data[3].attributes.condition", is("fit"),
                                                "data[3].links.self", is("http://localhost:8081/players/4"));
        }
}
