Feature: To validate PetStore API functionality

  @API
  Scenario: To validate GET available Pet API
    Given I have the endpoint as "baseURL/ENDPOINT_GET_PET"
    When I send the "get" request to "ENDPOINT_GET_PET" using
      | ContentType | application/json |           |
      | query       | status           | available |
    Then I assert all pets status as "available"

  @API
  Scenario: To validate POST add Pet API
    Given I have the endpoint as "baseURL/ENDPOINT_ADD_PET"
    When I send the "post" request using request body as "AddPet"
      | ContentType | application/json |  |
    Then I assert value as "demotestsumit" from response path "category.name"
    And I store the "id" of Pet

  @API
  Scenario: To validate PUT status of Pet API
    Given I have the endpoint as "baseURL/ENDPOINT_ADD_PET"
    When I send the "put" request using request body as "UpdatePetStatus"
      | ContentType | application/json |  |
    Then I assert value as "sold" from response path "status"

  @API
  Scenario: To validate Delete Pet API
    Given I have the endpoint as "baseURL/ENDPOINT_DELETE_PET"
    When I send the "delete" request to "ENDPOINT_DELETE_PET" using
      | ContentType | application/json |    |
      | path        | petid            | id |
    Then I assert value as "id" from response path "message"
