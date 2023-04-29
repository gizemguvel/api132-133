package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "John" and lastname is "Smith"
     */
    @Test
    public void get05(){
        //set the url
        spec.pathParam("first","booking").
                queryParams("firstname","John","lastname","Smith");

        //set the expected data

        //send the request and get the response
        Response response=given().spec(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));




    }








}
