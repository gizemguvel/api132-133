package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Homework1 extends ReqresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test

    public void homework01(){

        //set the url
        spec.pathParams("first","api","second","users","third",3);

        //set the expected data

        //send request and get response
       Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
       response.prettyPrint();

       //assertion
        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

    }
}

