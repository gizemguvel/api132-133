package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework2 extends ReqresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
    @Test
    public void homework02(){
        //set the url
        spec.pathParams("first","api","second","users","third",23);
        //set the expected data

        //send the request and get the response
       Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
       response.prettyPrint();

       //do assertion
        response.then().statusCode(404).statusLine("HTTP/1.1 404 Not Found").
                header("Server",equalTo("cloudflare")).
                body("", anEmptyMap());//This is no key value pair in the response body. So it is an empty map.



    }
}
