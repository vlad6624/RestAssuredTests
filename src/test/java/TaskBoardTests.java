import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskBoardTests {

    private static int taskId;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://taskboard.portnov.com";
    }

    @Test
    @Order(1)
    public void testGetAllTasks() {
        given()
                .log().all()
                .when()
                .get("/api/Task")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    @Order(2)
    public void testCreateTask() {
        String newTask = "{" +
                "  \"id\": 0," +
                "  \"taskName\": \"string\"," +
                "  \"description\": \"string\"," +
                "  \"dueDate\": \"2024-12-18T02:22:41.561Z\"," +
                "  \"priority\": 0," +
                "  \"status\": \"string\"," +
                "  \"author\": \"string\"" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(newTask)
                .when()
                .post("/api/Task")
                .then()
                .statusCode(201)
                .extract().response();
        taskId = response.path("id");
        System.out.println(response.path("id").toString());

    }

    @Test
    @Order(3)
    public void testGetAllTaskById() {
        // Id = 270
        Response response  = given()
                .get("/api/Task/" + taskId)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asString());


    }

@Test
@Order(4)
    public void testUpdateTask(){
    String updTask = "{" +
            "  \"id\": "+taskId+" ," +
            "  \"taskName\": \"Vlad's task updated\"," +
            "  \"description\": \"New task\"," +
            "  \"dueDate\": \"2024-12-18T02:22:30.923Z\"," +
            "  \"priority\": 1," +
            "  \"status\": \"New\"," +
            "  \"author\": \"Vlad G\"" +
            "}";
    given()
            .contentType(ContentType.JSON)
            .body(updTask)
            .when()
            .put("/api/Task/" + taskId)
            .then()
            .statusCode(204);
}

@Test
@Order(5)
    public void testDeleteTask(){
        given()
                .when()
                .delete("/api/Task/" + taskId)
                .then()
                .statusCode(204);
}


}
