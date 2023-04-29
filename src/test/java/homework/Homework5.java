package homework;

import base_urls.JsonPlaceHolderBaseUrl;
import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework5 extends ReqresBaseUrl {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void homework05(){
        //set the url
        spec.pathParams("first","api","second","unknown","third",3);
        //set the expected data
        //send the request get the response
        Response response=given().spec(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();
        //do assertion
        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(json.getInt("data.id"),3,"id didn't match");
        softAssert.assertEquals(json.getString("data.name"),"true red","name didn't match");
        softAssert.assertEquals(json.getInt("data.year"),2002,"year didn't match");
        softAssert.assertEquals(json.getString("data.color"),"#BF1932","color didn't match");
        softAssert.assertEquals(json.getString("data.pantone_value"),"19-1664","pantone_value didn't match");
        softAssert.assertEquals(json.getString("support.url"),"https://reqres.in/#support-heading","url didn't match");
        softAssert.assertEquals(json.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text didn't match");
        softAssert.assertAll();


    }








}
