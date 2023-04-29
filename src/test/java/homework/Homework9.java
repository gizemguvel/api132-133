package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework9 extends PetStoreBaseUrl {


/*
Using the https://petstore.swagger.io/ document, write an automation test that finds the number of "pets" with a status of "available"
and asserts that those are more than 100.
 */

//https://petstore.swagger.io/v2/pet/findByStatus?status=available
    @Test
    public void Homework09(){
        spec.pathParams("first","pet","second","findByStatus").
                queryParam("status","available");

        Response response=given(spec).get("{first}/{second}");
        //response.prettyPrint();

        assertEquals(200,response.statusCode());

        JsonPath jsonPath=response.jsonPath();
        List<String>availablePets= jsonPath.getList("findAll{it.status=='available'}.name");
        System.out.println("availablePets = " + availablePets);



        int size=availablePets.size();
        System.out.println("size = " + size);
        assertTrue(size>100);

        int availablePetSize=response.jsonPath().getList("id").size();
        assertTrue(availablePetSize>100);
        System.out.println("availablePetSize = " + availablePetSize);


    }






}
