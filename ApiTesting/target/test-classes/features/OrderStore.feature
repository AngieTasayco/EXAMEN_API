@OrderStore
  Feature: OrderStore Examen

    #@testCrear1
    #Scenario: Crear una orden en el Store
     # Given dado el servicio url "https://petstore.swagger.io/v2/store/order"
      #And inicializo request en post
      #And agrega la cabecera key: "Content-Type", y valor: "application/json"
      #When ingreso el id de la orden 13, el id de la mascota 8 y la cantidad de productos 5
      #Then validar el codigo de respuesta sea 200
      #And validar el id de la orden sea 13



    @testCrear
    Scenario Outline: Crear una orden en el Store
      Given dado el servicio url "https://petstore.swagger.io/v2/store/order"
      And inicializo request en post
      And agrega la cabecera key: "Content-Type", y valor: "application/json"
      When ingreso el id de la orden <IDORDER>, el id de la mascota <IDPET> y la cantidad de productos <CANTIDAD>
      Then validar el codigo de respuesta sea 200
      And validar el id de la orden sea <IDORDER>
      Examples:
        |IDORDER|IDPET|CANTIDAD|
        |14|3         |6       |
        |15 |3         |2       |


    @testConsultar
    Scenario Outline: Consultar una orden del Store
      Given dado el servicio url "https://petstore.swagger.io/v2/store/order"
      When se consulta la orden con el <ID>
      Then validar el codigo de respuesta sea 200
      And validar el id de la orden sea <ID>
      Examples:
        |ID|
        |14|
        |15 |

