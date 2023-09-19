package boundary.rest;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TeamResourceTest {
    @Test
    public void testGetAllTeams() {
            given()
                            .when().get("/teams")
                            .then()
                            .statusCode(200)
                            .body(
                                            "data[0].id", is("1"),
                                            "data[0].type", is("teams"),
                                            "data[0].attributes.category", is("seniors"),
                                            "data[0].attributes.name", is("Bayern München"),
                                            "data[0].links.self", is("http://localhost:8081/teams/1"),
                                            "data[1].id", is("2"),
                                            "data[1].type", is("teams"),
                                            "data[1].attributes.category", is("juniors"),
                                            "data[1].attributes.name", is("Bayern München"),
                                            "data[1].links.self", is("http://localhost:8081/teams/2"),
                                            "data[2].id", is("3"),
                                            "data[2].type", is("teams"),
                                            "data[2].attributes.category", is("seniors"),
                                            "data[2].attributes.name", is("BVB"),
                                            "data[2].links.self", is("http://localhost:8081/teams/3"),
                                            "data[3].id", is("4"),
                                            "data[3].type", is("teams"),
                                            "data[3].attributes.category", is("juniors"),
                                            "data[3].attributes.name", is("BVB"),
                                            "data[3].links.self", is("http://localhost:8081/teams/4"));
    }
}
