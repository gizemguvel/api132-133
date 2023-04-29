package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetById extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
           {
                 "firstname": "Josh",
                 "lastname": "Allen",
                 "totalprice": 111,
                 "depositpaid": true,
                 "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                 },
                 "additionalneeds": "midnight snack"
            }
     */

    @Test
    public void get08(){
        //set the url
        spec.pathParams("first","booking","second",24);

        //set the expected data

        //send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        response.then().body("firstname",equalTo("Josh"),"lastname",equalTo("Allen"),
                "totalprice", equalTo(111),
                "depositpaid", equalTo(true),
                "bookingdates.checkin", equalTo("2018-01-01"),
                "bookingdates.checkout", equalTo("2019-01-01"),
                "additionalneeds", equalTo("midnight snack"));


    }

}
