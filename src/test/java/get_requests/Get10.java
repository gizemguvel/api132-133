package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/23
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
    public void get10(){

        //set the url
        spec.pathParams("first","booking","second",23);

        //set the expected data
        Map<String,String> bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Josh");
        expectedData.put("lastname","Allen");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","midnight snack");

        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));

        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));




    }

    @Test
    public void get10b(){

        //set the url
        spec.pathParams("first","booking","second",38);

        //set the expected data
        Map<String,String> bookingdatesMap=new HerokuAppTestData().bookingdatesMapMethod("2018-01-01","2019-01-01");
        Map<String,Object> expectedData=new HerokuAppTestData().expectedDataMethod("Jane","Doe",111,true,bookingdatesMap,"Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));





    }
}
