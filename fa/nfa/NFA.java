package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

public class NFA implements NFAInterface {
    // **Instance Variables**

    // All the state in the NFA
    public Set<NFAState> allStates;

    // **Constructor**
    public NFA() {
        // LinkedHashSet being used as it maintains order.
        this.allStates = new LinkedHashSet<>();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFinal'");
    }

    @Override
    public boolean setStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStart'");
    }

    @Override
    public void addSigma(char symbol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSigma'");
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isFinal(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinal'");
    }

    @Override
    public boolean isStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStart'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }

}
