package fa.nfa;

import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

public class NFA implements NFAInterface {
    // **Instance Variables**
    // Store all characters that make up the Alphabet
    public Set<Character> sigma; // Visibility for testing
    // All the state in the NFA
    public Set<NFAState> allStates; // Visibility for testing
    // Final States
    public Set<NFAState> finalStates; // Visibility for testing
    // Transitions
    public Hashtable<Hashtable<NFAState, Character>, Set<NFAState>> transitions; // Visibility for testing
    public NFAState q0;

    // **Constructor**
    public NFA() {
        // LinkedHashSet being used as it maintains order.
        this.allStates = new LinkedHashSet<>();
        this.sigma = new LinkedHashSet<>();
        this.finalStates = new LinkedHashSet<>();
        this.transitions = new Hashtable<>();

        // Adding epsilon to alphabet for e transitions
        this.addSigma('e');
    }

    @Override
    public boolean addState(String name) {
        // Creating new state with given name
        NFAState newState = new NFAState(name);

        // Check if it is a new state
        if (allStates.contains(newState)) {
            return false; // State with that name already exists
        }

        // Add state to NFA
        return allStates.add(newState);

    }

    @Override
    public boolean setFinal(String name) {
        // Given a state name find that state in allStates
        NFAState newFinalState = (NFAState) getState(name);

        // Check if a state with that name was found
        if (newFinalState == (null)) {
            return false; // Not Found
        }

        // Check state with given name is in finalStates
        if (finalStates.contains(newFinalState)) {
            return false; // Is already a final state
        }

        // Set that state to be final state
        finalStates.add(newFinalState);

        // ?? We might need update the state’s status to final not just add to set of
        // ?? final states
        newFinalState.setIsFinal(true);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        NFAState target = new NFAState(name);
        if (allStates.contains(target)) {
            // Need to make sure the state stored in q0 and allstates is the same one.
            allStates.remove(target);
            q0 = target;
            allStates.add(target);
            return true;
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        // Checking if symbol is already part of alphabet
        if (sigma.contains(symbol)) {
            return; // Prevents Duplicate Symbols
        }
        // Adding symbol to sigma set
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {

        // Loop through all states in allStates
        for (NFAState state : allStates) {
            // Find the one that has the same name as given name
            if (state.getName().equals(name)) {
                return state;
            }
        }
        // Return null of state is NOT in allStates
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        NFAState state = new NFAState(name);
        // Check if state with given name is in set of final states
        if (finalStates.contains(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        NFAState target = new NFAState(name);
        return q0.equals(target);
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        // Validate symbol
        if (!sigma.contains(onSymb)) {
            return false; // onSymb not in alphabet
        }
        // Validate fromState
        NFAState NFAFromState = new NFAState(fromState);
        if (!allStates.contains(NFAFromState)) {
            return false; // NFAFromState not in set of acceptable states
        }

        // Validate toStates - all of them
        // A set of all the to states given in toStates string input
        Set<NFAState> allToStates = new LinkedHashSet<>();
        for (String toStateName : toStates) {
            NFAState possibleToState = new NFAState(toStateName);
            // check possibleToState is in set of states
            if (!allStates.contains(possibleToState)) {
                return false;
            }
            // possibleToState is valid add to possible allToStates
            allToStates.add(possibleToState);
        }

        // Updating transition map
        Hashtable<NFAState, Character> path = new Hashtable<>(1);
        path.put(NFAFromState, onSymb);
        transitions.put(path, allToStates);
        return true;
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }

}
