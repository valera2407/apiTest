import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;


import java.io.IOException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class TestApi {

    URL file = Resources.getResource("test.json");
    String myJson;

    @Test
    public void postApi() throws IOException {

        myJson = Resources.toString(file, Charsets.UTF_8);

        given().contentType(ContentType.JSON)
                .body(myJson)
                .when().post("http://dummy.restapiexample.com/api/v1/create")
                .then().statusCode(200).extract().response().prettyPrint();
    }

    @Test
    public void putApi() throws IOException {

        myJson = Resources.toString(file, Charsets.UTF_8);

        given().contentType(ContentType.JSON).body(myJson)
                .when().put("http://dummy.restapiexample.com/api/v1/update/21")
                .then().statusCode(200).extract().response().prettyPrint();
    }

    @Test
    public void deleteApi() {

        given().when().delete("http://dummy.restapiexample.com/api/v1/delete/9")
                .then().statusCode(200).extract().response().prettyPrint();
    }

    @Test
    public void getListApi() {

        given().when().get("http://dummy.restapiexample.com/api/v1/employees")
                .then().statusCode(200).extract().response().prettyPrint();
    }

    @Test
    public void getApi() throws IOException {

        String str = given().when().get("http://dummy.restapiexample.com/api/v1/employee/5")
                .then().statusCode(200).extract().response().asPrettyString();
    }
}
