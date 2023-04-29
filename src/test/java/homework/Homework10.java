package homework;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework10 extends AutomationExerciseBaseUrl {

    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends Get request
        Note: use prettyPrint like: response.jsonPath().prettyPrint()
    Then
        Assert that number of "Women" usertype is 12

*/

    @Test
    public void homework10(){
        //Set the url
        spec.pathParams("first","api","second","productsList");

        //send the request and get the response
        Response response=given(spec).get("{first}/{second}");
        response.jsonPath().prettyPrint();

        //do assertion
        JsonPath json=response.jsonPath();
        int womenList=json.getList("products.findAll{it.category.usertype.usertype=='Women'}").size();
        System.out.println("womenList = " + womenList);
        assertEquals(12,womenList);

    }

}
