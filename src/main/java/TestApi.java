
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TestApi extends BaseTest{

    @Test
    public void postApi() {
        basePath = "api/v1/create";

        var response = given().baseUri(dataKeep.getUri()).contentType(ContentType.JSON)
                .body(dataKeep.getMyJson())
                .when().basePath(basePath).post()
                .then().statusCode(200)
                .body("data.name", equalTo("Jonny"))
                .extract().response();

        dataKeep.setId(response.jsonPath().getString("data.id"));
        System.out.println("You create new user. " + dataKeep.getId() + " it`s his/her id.");
    }

    @Test
    public void putApi() {
        basePath = "api/v1/update/21";

        given().baseUri(dataKeep.getUri()).contentType(ContentType.JSON)
                .body(dataKeep.getMyJson())
                .when().basePath(basePath).put()
                .then().statusCode(200)
                .body("data.age", equalTo("79"))
                .extract().response().prettyPrint();
    }

    @Test
    public void getApi() {
        basePath = "api/v1/employee/14";

        var response = given().baseUri(dataKeep.getUri())
                .when().basePath(basePath).get()
                .then().statusCode(200)
                .extract().response();

        dataKeep.setEmployeeAge(response.jsonPath().getString("data.employee_age"));
        dataKeep.setEmployeeSalary(response.jsonPath().getString("data.employee_salary"));

        given().baseUri(dataKeep.getUri())
                .when().basePath(basePath).get()
                .then().statusCode(200)
                .body("data.employee_age", equalTo(dataKeep.getEmployeeAge()))
                .body("data.employee_salary", equalTo(dataKeep.getEmployeeSalary()))
                .extract().response().prettyPrint();
    }

    @Test
    public void deleteApi() {
        basePath = "api/v1/delete/" + dataKeep.getId();
        System.out.println(basePath + "\n" + dataKeep.getId());

        given().baseUri(dataKeep.getUri())
                .when().basePath(basePath).delete()
                .then().body("status", equalTo("success"))
                .extract().response().prettyPrint();
    }

    @Test
    public void getListApi() {
        basePath = "api/v1/employees";

        given().baseUri(dataKeep.getUri())
                .when().basePath(basePath).get()
                .then().statusCode(200)
                .extract().response().prettyPrint();
    }
}
