package boundary.rest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ManagerResourceTest {
    @Test
    public void testGetAllManagers() {
        given()
                        .when().get("/managers")
                        .then()
                        .statusCode(200)
                        .body(
                                        "data[0].id", is("1"),
                                        "data[0].type", is("persons"),
                                        "data[0].attributes.name", is("Thomas Tuchel"),
                                        "data[0].links.self", is("http://localhost:8081/managers/1"),
                                        "data[1].id", is("2"),
                                        "data[1].type", is("persons"),
                                        "data[1].attributes.name", is("Juergen Klopp"),
                                        "data[1].links.self", is("http://localhost:8081/managers/2"),
                                        "data[2].id", is("3"),
                                        "data[2].type", is("persons"),
                                        "data[2].attributes.name", is("Pep Guardiola"),
                                        "data[2].links.self", is("http://localhost:8081/managers/3"),
                                        "data[3].id", is("4"),
                                        "data[3].type", is("persons"),
                                        "data[3].attributes.name", is("Carlo Ancelotti"),
                                        "data[3].links.self", is("http://localhost:8081/managers/4"));
}
}
