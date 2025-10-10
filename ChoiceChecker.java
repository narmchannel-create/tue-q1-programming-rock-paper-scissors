/**
 * This is the interface that will be implemented by
 * each of the followings: rock, scissors and paper checkers.
 */
public interface ChoiceChecker {
    Boolean check(Choice computerChoice);
    
    /**
     * This method allows to set userchoice to each checker
     * to easily determine who won the round.
     */
    void setUserChoice(Choice userChoice);
}
