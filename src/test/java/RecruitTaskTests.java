import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RecruitTaskTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://recruit-stage.portnov.com/recruit/api/v1";
    }

    @Test
    @Order(1)
    public void testGetAllCandidates() {
        given()
                .log().all()
                .when()
                .get("/candidates")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void testGetAllPositions() {
        given()
                .log().all()
                .when()
                .get("/positions")
                .then()
                .log().all()
                .statusCode(200);
    }
}
