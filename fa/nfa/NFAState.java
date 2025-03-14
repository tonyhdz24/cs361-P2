package fa.nfa;

import fa.State;

/**
 * NFAState
 *  This class represents the NFA version of a state
 *  States can be a final state, and this class stores the name of the states
 *
 * @author Antonio (Tony) Hernadez, Zach Johnston
 */
public class NFAState extends State {
    // Instance variable
    boolean isFinal;

    public NFAState(String name) {
        super(name);
        isFinal = false;
    }

    /**
     * The following methods allow us to use contains() on a LinkedHashSet<> to
     * check if a State is in the set or not
     * Takes in and Object which is cast to DFAState then checks if its name matches
     * 
     * @param newState - New state we want to add
     */
    @Override
    public boolean equals(Object newState) {
        // Cast newState to be a DFAState
        NFAState other = (NFAState) newState;
        return this.getName().equals(other.getName());
    }

    /**
     * Sets states isFinal boolean to be either true or false
     * 
     * @param isFinal - whether or not state is final
     */
    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Returns if states isFinal
     * 
     * @return whether or not state is final
     */
    public boolean getIsFinal() {
        return this.isFinal;
    }

    // Allows us to compare based on name
    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
