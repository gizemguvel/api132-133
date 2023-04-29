package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification spec;
    @Before //will run before each test methods
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").setContentType(ContentType.JSON).build();
    }
}
