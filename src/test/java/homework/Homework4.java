package homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Homework4 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */
@Test
    public void homework04(){
    //set the url
    spec.pathParam("first","booking").
            queryParams("firstname","Josh","lastname","Allen");
    //set the expected data

    //send request and get response
    Response response=given().spec(spec).when().get("{first}");
    response.prettyPrint();
    //do assertion
    assertEquals(200,response.getStatusCode());
    assertTrue(response.asString().contains("bookingid"));


}
}
