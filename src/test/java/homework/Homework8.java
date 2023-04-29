package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetStoreTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework8 extends PetStoreBaseUrl {
    /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/" documentation.
   */

    /*
    Given
      https://petstore.swagger.io/v2/user
    And
       {
          "id": 0,
          "username": "gguvel",
          "firstName": "gizem",
          "lastName": "guvel",
          "email": "gizem@hotmail.com",
          "password": "1234",
          "phone": "12345",
          "userStatus": 0
        }
    When
      I send a POST request
    Then
       status code should be  200
    And
       response body should be like
            {
                "code": 200,
                "type": "unknown",
                "message": "9223372036854757933"
            }

     */
@Test
    public void Homework08(){
        //set the url
        spec.pathParam("first","user");

        //set the expected data
        Map<String,Object> expectedData=new PetStoreTestData().expectedDataMapMethod("gguvel","gizem","guvel",
                "gizem@hotmail.com","1234","12345",0);
        System.out.println("expectedData = " + expectedData);

        //send the request-get the response
        Response response=given(spec).when().body(expectedData).post("{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));







    }



}
