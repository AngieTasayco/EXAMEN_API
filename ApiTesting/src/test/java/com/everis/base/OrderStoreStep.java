package com.everis.base;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderStoreStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStoreStep.class);
    static private String BASE_URL = "https://petstore.swagger.io/v2/store/order/";

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;
    private String bodyPost;

    @Before
    public void init() {

        LOGGER.info(" Inicializa el constructor request ");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        LOGGER.info(" Inicializa el constructor response ");
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }


    public void setURL(String sURL){
        BASE_URL=sURL;
        LOGGER.info("URL BASE "+BASE_URL);
    }

    public void validarStatusCode(int i) {
        assertThat(lastResponse().statusCode(), is(i));
        LOGGER.info("Status Code: "+i);
    }

    public void validarBodyContent(int idOrder) {
        assertThat(lastResponse().getBody().path("id"), equalTo(idOrder));
        LOGGER.info("Validar Orden: "+idOrder);
    }

    public void registrarOrden(int idOrder, int idPet,int cantidad ){
        JsonObject parametros = new JsonObject();
        parametros.addProperty("id", idOrder);
        parametros.addProperty("petId", idPet);
        parametros.addProperty("quantity", cantidad);
        parametros.addProperty("shipDate", "2022-10-18T14:25:47.303Z");
        parametros.addProperty("status", "placed");
        parametros.addProperty("complete", true);

        bodyPost = parametros.toString();

        builder.setBody(bodyPost);

        LOGGER.info("ID: "+idOrder+" IDPET:"+idPet+ " CANTIDAD: "+cantidad);
    }
    @Step("set Service Name")
    public void inicializoParametrosRequestPost() {
        RestAssured.baseURI = BASE_URL;
        builder = new RequestSpecBuilder();
        requestSpecification = builder.build();
    }

    @Step("set Header")
    public void setHeader(String k, String v) {
        builder.addHeader(k, v);
    }


    public void enviarPos(){
        response = given().spec(requestSpecification).when().post();
    }

    public void consultaOrden(int idOrder){
        LOGGER.info("Consulta de Orden: "+idOrder);
        given().
                spec(requestSpec).
                log().all().
                when().
                get(""+idOrder).
                then().
                spec(responseSpec).
                and().
                log().all();
    }
}
