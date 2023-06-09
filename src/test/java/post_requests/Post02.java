package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
/*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post02(){
        //Set the url
        spec.pathParam("first","booking");

        //set the expected data
        Map<String ,String> bookingdatesMap=new HerokuAppTestData().bookingdatesMapMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedData= new HerokuAppTestData().expectedDataMethod("John","Doe",11111,true,bookingdatesMap,null);

        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response=given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
        //Since the value data type is Object in the actual data map, all values will return as object data type.
        //to use map methods from an object container we need to do typecasting





    }

}
