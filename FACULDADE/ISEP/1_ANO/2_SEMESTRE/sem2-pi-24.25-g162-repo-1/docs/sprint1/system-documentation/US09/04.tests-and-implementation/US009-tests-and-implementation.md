# US009 - As a Player, I want to buy a locomotive.

## 4. Tests

### 4.1. Domain Tests

**Test 1:** Ensure that a locomotive cannot be created with a null type.

        @Test(expected = IllegalArgumentException.class)
        public void ensureNullLocomotiveTypeNotAllowed() {
            new Locomotive(null);
        }

**Test 2:** Ensure that the locomotive is created with the correct type.

        @Test
        public void ensureLocomotiveIsCreatedWithCorrectType() {
        LocomotiveType type = new LocomotiveType("Steam", 1850);
        Locomotive loco = new Locomotive(type);
        
            assertEquals(type, loco.type());
        }

**Test 3:** Ensure that available locomotives are filtered correctly by scenario date.

        @Test
        public void ensureLocomotiveFilteringByScenarioDate() {
            Scenario scenario = new Scenario(1870);
            List<LocomotiveType> allTypes = locomotiveRepo.getAllTypes();
            List<LocomotiveType> allowed = scenario.getAllowedLocomotiveTypes(allTypes);
        
            for (LocomotiveType type : allowed) {
                assertTrue(type.year() <= scenario.year());
            }
        }

**Test 4:** Ensure that the selected locomotive is available in the list before purchase.

        @Test
        public void ensureSelectedLocomotiveExistsInAvailableList() {
            Scenario scenario = new Scenario(1900);
            LocomotiveType selected = new LocomotiveType("Diesel", 1890);
            List<LocomotiveType> allowed = scenario.getAllowedLocomotiveTypes(locomotiveRepo.getAllTypes());
        
            assertTrue(allowed.contains(selected));
        }

**Test 5:** Ensure the controller fetches and returns available locomotive types. 

        @Test
        public void ensureAvailableLocomotivesAreFetched() {
            List<LocomotiveType> locos = controller.getAvailableLocomotives();
        
            assertNotNull(locos);
            assertFalse(locos.isEmpty());
        }

**Test 6:** Ensure player ownership is updated after a successful purchase.

        @Test
        public void ensurePlayerOwnsLocomotiveAfterPurchase() {
        Player player = new Player("player1", 5000);
        LocomotiveType type = new LocomotiveType("Electric", 1950, 3000);
        PlayerSession.instance().setCurrentPlayer(player);
        
            Locomotive locomotive = controller.buyLocomotive(type.name());
        
            assertTrue(player.ownsLocomotive(locomotive));
            assertEquals(2000, player.wallet());
        }

**Test 7:** Ensure purchase is denied if the player has insufficient funds.

        @Test(expected = IllegalStateException.class)
        public void ensureInsufficientFundsPreventsPurchase() {
        Player player = new Player("player2", 1000);
        LocomotiveType type = new LocomotiveType("High-Speed", 2000, 5000);
        PlayerSession.instance().setCurrentPlayer(player);
        
            controller.buyLocomotive(type.name());
        }


## 5. Construction (Implementation)

### Class BuyLocomotiveController

```java
public Locomotive buyLocomotive(String typeName) {
    Player player = PlayerSession.instance().getCurrentPlayer();
    Scenario scenario = MapSession.instance().getCurrentMap().currentScenario();

    LocomotiveType type = scenario.getLocomotiveTypeByName(typeName);

    if (player.wallet() < type.price()) {
        throw new IllegalStateException("Insufficient funds.");
    }

    Locomotive locomotive = new Locomotive(type);
    player.purchaseLocomotive(locomotive);

    return locomotive;
}

```

### Class Scenario

```java
public LocomotiveType getLocomotiveTypeByName(String name) {
    return allowedLocomotives.stream()
            .filter(type -> type.name().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Locomotive type not found"));
}

```


## 6. Integration and Demo

* A new option "Buy Locomotive" was added to the Player menu within the Transport Management UI.

* For demo purposes, several locomotive types (Steam, Diesel, Electric) are bootstrapped during startup based on the current scenario's year.

* The system automatically filters locomotives allowed in the current scenario when opening the locomotive shop.

## 7. Observations

n/a.