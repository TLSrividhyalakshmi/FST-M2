package LiveProject;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.ContentType;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {
    //Headers
    Map<String,String> headers =new HashMap<>();

    //Resource path
    String resourcePath ="/api/users";

    //create the pact
    @Pact(consumer = "UserConsumer",provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        //Headers
        headers.put("Content-Type", "application/json");

        // Request and Response Body
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id",123)
                .stringType("firstName","sri")
                .stringType("lastName","vidhya")
                .stringType("email","sri@example.com");

        //Pact
        return builder.given("A request to create a user")
                .uponReceiving("A request to create a user")
                    .method("POST")
                    .path(resourcePath)
                    .headers(headers)
                    .body(requestResponseBody)
                .willRespondWith()
                     .status(201)
                     .body(requestResponseBody)
                .toPact();

    }
    @Test
    @PactTestFor(providerName = "UserProvider",port = "8282")
    public void postRequestTest(){
        //Mock Server URL

        String mockserver ="http://localhost:8282";

        //Request Body
        Map<String,Object> reqBody =new HashMap<>();
        reqBody.put("id",77232);
        reqBody.put("firstName","Hansel");
        reqBody.put("lastName","Jones");
        reqBody.put("email","hansel@example.com");

        // Generate response
        given().body(reqBody).headers(headers)
                .when().post(mockserver+resourcePath)
                .then().statusCode(201).log().all();

    }
}
