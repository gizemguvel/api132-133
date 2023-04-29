package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S4_Patch  extends HerOkuAppBaseUrl {

    //create a map for patch. pojo includes all.
/*
        Given
           https://restful-booker.herokuapp.com/booking/id
        And
           {
                "firstname" : "John",
                "lastname" : "Doe"
            }
        When
           send a patch request
        Then
           status code is 200
        And
           response body should be
           {
            "firstname" : "John",
            "lastname" : "Doe",
            "totalprice" : 555,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Extra pillow"
        }

 */

    @Test
    public void patchTest(){
        //set the url
        spec.pathParams("first","booking","second",bookingId);

        //set the expected data
        Map<String,String> expectedData= new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Doe");

        //send the request and get the response
        Response response= given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),response.jsonPath().getString("firstname"));
        assertEquals(expectedData.get("lastname"),response.jsonPath().getString("lastname"));







    }
}


