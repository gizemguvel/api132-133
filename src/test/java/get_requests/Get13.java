package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get13 extends DummyRestApiBaseUrl {

    /*
        Given
            https://dummy.restapiexample.com/api/v1/employees
        When
            User sends Get Request to the Url
        Then
            Status code is 200
        And
            There are 24 employees
        And
            "Tiger Nixon" and "Garrett Winters" are among the employees
        And
            The greatest age is 66
        And
            The name of the lowest age is "[Tatyana Fitzpatrick]"
        And
            Total salary of all employees is 6,644,770
 */

    @Test
    public void get13() {
        //set the url
        spec.pathParam("first", "employees");

        //set the expected data

        //send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response.then().statusCode(200).
                body("data", hasSize(24),
                        "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ages = jsonPath.getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("ages = " + ages);
        int greatestAge = ages.get(ages.size() - 1);
        System.out.println("greatestAge = " + greatestAge);
        assertEquals(66, greatestAge);


        String nameOfTheLowestAge = jsonPath.getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("nameOfTheLowestAge = " + nameOfTheLowestAge);
        assertEquals("[Tatyana Fitzpatrick]", nameOfTheLowestAge);

        List<Integer> salaries = jsonPath.getList("data.employee_salary");

        //1st way: ForEach Loop
        int sumOfSalaries=0;
        for(int w:salaries){

           sumOfSalaries += w;
        }
        System.out.println("sumOfSalaries = " + sumOfSalaries);

        //2nd way: Functional Programming
        int sumOfSalariesLambda=salaries.stream().reduce(0,Math::addExact);
        System.out.println("sumOfSalariesLambda = " + sumOfSalariesLambda);

        assertEquals(6644770,sumOfSalariesLambda);









    }
}
