package practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetPractice01 extends HerOkuAppBaseUrl {

    /*
   Given
           https://restful-booker.herokuapp.com/booking/43
   When
           I send GET Request to the URL
   Then
           Status code is 200
                   {
                       "firstname": "Jane",
                       "lastname": "Doe",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2018-01-01",
                           "checkout": "2019-01-01"
                       },
                       "additionalneeds":  "Extra pillows please"
                   }
*/

    @Test
    public void get01() throws IOException {
        //set the url
        spec.pathParams("first","booking","second",66);

        //set the expected data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Jane","Doe",111,true,bookingDatesPojo,"Extra pillows please");

        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        BookingPojo actualData= new ObjectMapper().readValue(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);

        JsonPath jsonPath=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        assertEquals(actualData.getLastname(),expectedData.getLastname());
        assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());
        assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(jsonPath.getString("firstname"),"Jane","not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin did NOT match");
        softAssert.assertEquals(actualData.getFirstname(),"Jane","not match");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout(),"not match");




        softAssert.assertAll();




    }






}
