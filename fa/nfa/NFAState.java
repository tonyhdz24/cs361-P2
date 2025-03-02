package fa.nfa;

import fa.State;

public class NFAState extends State {
    public NFAState(String name) {
        super(name);
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

    // Allows us to compare based on name
    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
