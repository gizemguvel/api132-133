package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S5_Delete extends HerOkuAppBaseUrl {
    /*
            Given
               https://restful-booker.herokuapp.com/booking/id
            When
               send a delete request
            Then
               status code should be 201
            And
               response body should be "created"
     */

@Test
    public void deleteTest(){
    //set the url
    spec.pathParams("first","booking","second",bookingId);

    //set the expected data
    String expectedData="Created";

    //send the request and get the response
    Response response=given(spec).delete("{first}/{second}");
    response.prettyPrint();

    //do assertion
    assertEquals(201,response.statusCode());
    assertEquals(expectedData,response.asString());

}












}
