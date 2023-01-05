package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

    @When("the Maker starts a game")
    public void the_maker_starts_a_game() {
        System.out.println("thgge Maker starts a game");
    }
    @Then("the Maker waits for a Breaker to join")
    public void the_maker_waits_for_a_breaker_to_join() {
        System.out.println("ffthe Maker waits for a Breaker to join");

    }


}
