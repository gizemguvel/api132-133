package homework;

import base_urls.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Country;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Homework11 extends GmiBankBaseUrl {

    //Type an automation test that creates a "country" which includes at least 3 "states"
    // using the document //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1.


    /*
                {
              "name": "UK",
              "states": [
                {
                  "id": 1,
                  "name": "London"
                },
                {
                  "id": 2,
                  "name": "Birmingham"
                },
                {
                  "id": 3,
                  "name": "Chelsea"
                }
              ]
            }

            https://gmibank.com/api/tp-countries
     */

    @Test
    public void postCountryTest(){
        //set the url
        spec.pathParams("first","api","second","tp-countries");

        //set the expected data

        List<Object>states;

        Country expectedData=new Country();
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response=given(spec).post("{first}/{second}");
        response.prettyPrint();


    }
}
