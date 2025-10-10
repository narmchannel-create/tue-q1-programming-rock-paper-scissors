/**
 * The rock checker implements the ChoiceChecker interface
 * and has the function check() that determines who won, given that
 * the user has played rock.
 */
public class RockChecker implements ChoiceChecker {
    Choice userChoice;

    public void setUserChoice(Choice userChoice) {
        this.userChoice = userChoice;
    }

    /**
    * This method allows to set userchoice to each checker
    * to easily determine who won the round.
    */
    public Boolean check(Choice computerChoice) {
        Boolean won = false;
        
        if (userChoice.getName().equals("Rock") && computerChoice.getName().equals("Scissors")) {
            won = true;
        }

        return won;
    }
}
