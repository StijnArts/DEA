# Introductie
Deze oefening is deel van de DEA Course aan de Hogeschool Arnhem/Nijmegen. Doel is het bekend raken met test-driven development, met jUnit als test framework voor het schrijven van unittests.

# Oefeningen
In deze oefening werken we nogmaals de FizzBuzz kata uit. Echter, zorgen we er deze keer voor dat we ook de bijbehorende unittests schrijven.

Daarbij beginnen we steeds met het schrijven van een unittest voor een specifieke requirement van FizzBuzz. Pas daarna implementeren we de requirement zelf. 

## Oefening 1: Toevoegen van een jUnit aan een Maven project
Om gebruik te kunnen maken van jUnit moeten we een aantal dependencies aan de pom.xml file toevoegen, zodat Maven deze dependencies gebruikt als we het project bouwen.

We gaan [jUnit 5](https://junit.org/junit5/) gebruiken. Deze versie bestaat uit 3 dependencies, zoals te zien bij `Latest Release`. Wij hebben er nu maar 2 van nodig. De informatie voor deze dependencies kunnen we vinden in [Maven Central](https://search.maven.org/). Voeg in de pom.xml file de laatste versie van de dependencies toe voor `junit-jupiter` en `junit-platform-launcher`.

## Oefening 2: Het maken van een testklasse
Het project bevat twee klassen:
- `FizzBuzzRunner` met een `main()` methode
- `FizzBuzzExecutor` met een `execute()` methode.


De `FizzBuzzExecutor` bevat het 'gedrag' of de business logica; de code die een getal checkt en het juiste resultaat terug geeft. De `FizzBuzzRunner` bevat enkel een `main()` methode die een reeks van getallen genereert en voor ieder getal de `FizzBuzzExecutor.execute()` methode vraagt wat het resultaat is (`Fizz`, `Buzz`, `FizzBuzz` of het getal zelf).


In deze oefening implementeren we de methode `execute` van de `FizzBuzzExecutor`. Dit hebben we in de vorige opdracht al gedaan, maar dit vergeten we even en deze keer beginnen we in plaats daar van met het schrijven van unittests.

* Voeg een testklasse toe voor de klasse `FizzBuzzExecutor`. Volg hierbij de volgende aanwijzingen:
    * Voor het antwoord op de vraag: 'Wat is eigenlijk een testklasse?', bekijk de [Userguide](https://junit.org/junit5/docs/current/user-guide/) van jUnit 5
    * Voor het antwoord op de vraag: 'Waar moet ik mijn tests plaatsen?', lees het volgende onderdeel van de [Introduction to the Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout).
    
## Oefening 3: Schrijf een eerste unittest
* Voeg aan je testklasse onderstaande unittest toe en zorg ervoor dat je hem kunt uitvoeren en dat hij slaagt.
```java
    void executeWithValidIntTest(){
        // Arrange
        var sut = new FizzBuzzExecutor();
            
        // Act
        var testValue = sut.execute(37);
            
        // Assert
        Assertions.assertEquals("37", testValue);
    }
```

De afkorting **sut** staat hierbij voor **S**ystem **U**nder **T**est. Een handige (aangeraden) naamgeving voor de klasse die je daadwerkelijk aan het testen bent.

Om van de methode een unittest te maken moet je deze annoteren met de annotatie `@Test`. Wanneer je de test daarna uitvoert zie je dat deze faalt. Om hem te laten slagen zul je de methode `execute(int)` correct moeten implementeren. Bouw op dit moment nog geen volledig FizzBuzz gedrag in, zorg er enkel voor dat je test slaagt. Enkele aanwijzingen:
    
* Voor het antwoord op de vraag: 'Hoe run ik mijn Unittest met Maven?', lees deze [tutorial](https://www.mkyong.com/maven/how-to-run-unit-test-with-maven/).
* Het uitvoeren van een Unittest kan binnen IntelliJ door met de rechtermuisknop op de unittest te klikken en dan op de optie `Run executeWithValidIntTest()`. IntelliJ voert dan de gekozen unittest uit.

## Oefening 4: Voer de unittest uit met behulp van Maven  
Run je test met Maven via de commandline. Er is een kans dat Maven de test niet kan vinden en in zijn output aangeeft dat er geen tests uitgevoerd zijn. Dit komt omdat Maven gebruikt maakt van een te oude plugin om Unittests uit te voeren. Het gaat hierbij om de *maven-surefire-plugin*.
* Voor uitleg hoe je dit probleem moet oplossen, lees deze [tutorial](https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven).
  
## Oefening 5: Schrijf een tweede unittest voor Fizz
* Voeg een tweede unittest toe die test of de methode `execute(int)` de string `Fizz` teruggeeft wanneer `int` deelbaar is door 3 en zorg ervoor dat deze unittest slaagt. Kies zelf een of meerdere getallen om te testen. Je kunt nu een deel van de vorige oplossing beschikbaar maken. Hint: het juiste `if`-statement.

## Oefening 6: Voeg een setup() methode toe aan je testklasse
In beide unittests die je nu hebt gemaakt instantieer je een `FizzBuzzExecutor`. Je hebt hier dus herhalende code. Om dit op te lossen voegen we een *setup* methode toe.

* Voeg de volgende methode en instantie-variabele toe aan je testklasse. Annoteer de methode met `@BeforeEach` zodat deze code voor elke `@Test` uitgevoerd gaat worden. Verwijder dan ook deze code uit de twee tests. Die is nu overbodig geworden.
```java
    private FizzBuzzExecutor sut;
    
    public void setup(){
        sut = new FizzBuzzExecutor();
    }
```

## Oefening 7: Schrijf een derde unittest voor Buzz
* Voeg een derde unittest toe die test of de methode `execute(int)` de string `Buzz` teruggeeft wanneer `int` deelbaar is door 5 en zorg ervoor dat deze unittest slaagt.

## Oefening 8: Schrijf een vierde unittest voor FizzBuzz
* Voeg een vierde unittest toe die test of de methode `execute(int)` de string `FizzBuzz` teruggeeft wanneer `int` deelbaar is door 15 en zorg ervoor dat deze unittest slaagt.

## Oefening 9: Run with Coverage
Gebruik IntelliJ om je unittests uit te voeren en gelijktijdig inzichtelijk te maken welk percentage van je code wordt ge-unittest. Dit heet de 'code coverage'. Ook dit kan via het contextmenu, echter moet je daarvoor NIET met de rechter-muisknop op de unittest klikken, maar op de testklasse.

Om het tonen van Coverage weer uit te zetten ga je in de IDE naar `Run > Hide Coverage`.

## Oefening 10: Code coverage kan variëren en is code coverage een silver bullet?
Optioneel:
Noteer hoe hoog je totale coverage nu is (uitkomst van oefening 9). Commentaar daarna tijdelijk de `FizzBuzz` test van oefening 9 uit en bepaal opnieuw de test coverage. Is deze veranderd? Is dit hetzelfde bij klasgenoten met een andere (maar ook werkende!) implementatie? Zo ja, kun je bedenken hoe dit zou kunnen?

Als de code coverage niet verandert, verander dan ook de `Buzz` test van oefening 7. Verandert de code coverage nu wel? Klopt dit met je verwachting? Bij sommige bedrijven is er de eis om 100% code coverage te hebben. Is dit waardevol? En is 100% coverage een garantie dat er geen fouten in je code zitten? Waarom wel, of niet?
