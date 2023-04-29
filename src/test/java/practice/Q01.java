package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Q01 extends ReqresBaseUrl {

    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void get01(){
        //set the url
        spec.pathParams("first","api","second","unknown");

        //send the request get the response
        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response.then().assertThat().statusCode(200);

        JsonPath json=response.jsonPath();
        List<String>pantoneList=json.getList("data.pantone_value");
        System.out.println("pantoneList = " + pantoneList);

        List<Integer> ids=json.getList("data.findAll{it.id>3}.id");
        assertEquals(3,ids.size());

        List<String>names=json.getList("data.findAll{it.id<3}.name");
        assertEquals(2,names.size());







    }



}
