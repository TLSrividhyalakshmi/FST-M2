package LiveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHub_RestAssured_Project {
    RequestSpecification requestSpec;
    String sshKey;
    int sshKeyId;

    @BeforeClass
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","token ghp........")
                .setBaseUri("https://api.github.com")
                .build();
        sshKey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCCKzTjwU8m8cqnaMFpJb9Ek0awZz0UPOqV6pFJqsPxQ6HI+k5SRujFu412U97dX4dkJOnI22P/c/vySAMkBCaX0QCE09Ft9s85V3uouEb13aN8d4d2AaVLb3AIGCoNR0eb79/wKEluGPLrJGwRxJf6Rn1iA4tARp+BhAWN3MKKAjKj/xfvJeq80c0Aq1vvyKJsAS3I4TbNsClZqI0HhLkyYOFtkEI6kK10m/9R4dA5vQiA7u3DrE4mSsOpBB876JPrDkMMAG+Gg7tMb9Bohi88W9Orr+wiFulwzbRtCNO4Tg46WQ1ajFIxVvVr0JaqSZxIHtA7CLsUv0iVBHQ3UTtl";
    }
    @Test(priority = 1)
    public void addKeys()
    {
        String reqBody="{\"title\":\"TestKey\",\"key\":\""+sshKey+"\"}";
        Response response=given().spec(requestSpec)
                .body(reqBody)
                .when().post("/user/keys");


        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);
        sshKeyId=response.then().extract().path("id");
        response.then().statusCode(201);

    }

    @Test(priority = 2)
    public void getKeys()
    {
        Response response=given().spec(requestSpec)
                .pathParam("keyId",sshKeyId)
                .when().get("/user/keys/{keyId}");
        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);

        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteKeys()
    {
        Response response=given().spec(requestSpec)
                .pathParam("keyId",sshKeyId)
                .when().delete("/user/keys/{keyId}");

        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);

        response.then().statusCode(204);
    }
}
