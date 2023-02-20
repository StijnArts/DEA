# Introductie

Deze oefening is deel van de DEA Course aan de Hogeschool Arnhem/Nijmegen. 
Onderwerp is het bekend raken met de Stream API.

# Oefening

Deze oefeningen bestaan uit het laten slagen van Unittests.

FYI: Namen van test methodes dienen altijd descriptief te zijn 
> "Ideally, the order in which unit tests run should not matter, and it is best practice to avoid ordering unit tests." - [Microsoft docs](https://docs.microsoft.com/en-us/dotnet/core/testing/order-unit-tests)

## Stream tests

### I. Het filteren van een lijst
* filterStringsShorterThanThreeCharacters_WithMoreThanThreeCharactersInElements_ShouldRemoveFromList
* filterStringsThatContainOnlyNumerals_WithNonNumeralStrings_ShouldRemoveNonNumeralStrings

### II. Het identificeren van een element uit een lijst
* findShortestString_WithListOfStrings_ReturnsShortestString
* findShortestString_WithEmptyList_ReturnsNull

### III. Het samenvoegen van de elementen uit een lijst
* createAFullSentenceFromTheList_WithListInput_CreatesSentence

### IV. Het bepalen van totalen
* calculateTotalCostOfAllProducts_WithListOfProducts_ReturnsSumOfProductCost
* calculateTotalCostOfAllGadgets_WithListOfProducts_ReturnsSumOfGadgetsCost