Feature: Consulta Produto
  Scenario: Consulta produto Cama de Cachorro
    Given que acesso o site da Cobasi
    Then Fecho modal de receber ofertas
    And Pesquiso produto "guia"
    And seleciono o produto "Guia Premium Maldives Dog Trip Brasil Azul"
    When Exibe a tela do produto "Guia Premium Maldives Dog Trip Brasil Azul"
    And o preco a vista de "Por: R$ 41,93à vista"