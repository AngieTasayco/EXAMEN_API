package com.everis.base.stepDefinitions;

import com.everis.base.OrderStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OrderStoreSD {

    @Steps
    OrderStoreStep orderStore;

    @Given("dado el servicio url {string}")
    public void dadoElServicioUrl(String sURL) {
        orderStore.setURL(sURL);
    }


    @Then("validar el codigo de respuesta sea {int}")
    public void validarElCodigoDeRespuestaSea(int arg0) {
        orderStore.validarStatusCode(arg0);
    }


    @And("inicializo request en post")
    public void agregoParametrosDeRequestEnPost() {
        orderStore.inicializoParametrosRequestPost();
    }

    @And("^agrega la cabecera key: \"([^\"]*)\", y valor: \"([^\"]*)\"$")
    public void agregaLaCabeceraKeyYValor(String k, String v) {
        orderStore.setHeader(k, v);
    }


    @When("ingreso el id de la orden {int}, el id de la mascota {int} y la cantidad de productos {int}")
    public void ingresoElIdDeLaOrdenElIdDeLaMascotaYLaCantidadDeProductos(int idOrder, int idPet, int cantidad) {
        orderStore.registrarOrden(idOrder,idPet,cantidad);
        orderStore.enviarPos();
    }

    @And("validar el id de la orden sea {int}")
    public void validarElIdDeLaOrdenSea(int idOrder) {
        orderStore.validarBodyContent(idOrder);
    }

    @When("se consulta la orden con el {int}")
    public void seConsultaLaOrdenConElID(int idOrder) {
        orderStore.consultaOrden(idOrder);
    }
}
