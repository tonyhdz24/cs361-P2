package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;
import fa.State;

/**
 * NFA
 *  A representation of Non-Determenistic Finite Automata (NFA)
 *  Since ASCII does not contain epsilon as a character, 'e' is a reserved character for
 *  epsilon transitions
 *
 * @author Antonio(Tony) Hernandez, Zach Johnston
 */
public class NFA implements NFAInterface {
    // **Instance Variables**
    // Store all characters that make up the Alphabet
    public Set<Character> sigma; // Visibility for testing
    // All the state in the NFA
    public Set<NFAState> allStates; // Visibility for testing
    // Final States
    public Set<NFAState> finalStates; // Visibility for testing
    // Transitions
    public Map<NFAState, Map<Character, Set<NFAState>>> transitions; // Visibility for testing

    public NFAState q0;

    // **Constructor**
    public NFA() {
        // LinkedHashSet being used as it maintains order.
        this.allStates = new LinkedHashSet<>();
        this.sigma = new LinkedHashSet<>();
        this.finalStates = new LinkedHashSet<>();
        this.transitions = new HashMap<>();

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
        allStates.add(newState);
        transitions.put(newState, new HashMap<>()); // Initialize transition entry
        return true;

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

        newFinalState.setIsFinal(true);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        NFAState startState = (NFAState) getState(name);
        if (startState == null) {
            return false;
        }
        q0 = startState;
        return true;

    }

    @Override
    public void addSigma(char symbol) {
        // Checking if symbol is already part of alphabet or epsilon 'e'.
        if (sigma.contains(symbol) || symbol == 'e') {
            return; // Prevents Duplicate Symbols
        }
        // Adding symbol to sigma set
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // Start with e-closure of the start state
        Set<NFAState> reachAbleStates = this.eClosure(q0);

        // Iterate over the input string character by character
        // For each symbol in the string create a new set of states the NFA can
        // transition to from the current states.
        for (char transitionSymbol : s.toCharArray()) {
            // Tracks states after transition
            Set<NFAState> nextStates = new LinkedHashSet<>();

            // For each reachable state in reachAbleStates get the possible transitions from
            // each on transition symbol
            for (NFAState nfaState : reachAbleStates) {
                // Use getToState(state, symbol) to find all possible next states
                Set<NFAState> possibleToStates = getToState(nfaState, transitionSymbol);

                // For all the states we can transition to we add the eClosure of that state to
                // the set of states machine can be in
                for (NFAState toState : possibleToStates) {
                    nextStates.addAll(eClosure(toState));
                }
            }
            // Once all possible next states for a given transition symbol have been found
            // add to nextStates
            // Update reachAbleStates to be nextStates for the next symbol
            reachAbleStates = nextStates;

        }

        // After looping though input string loop through all the states we could be in
        // and check if at least on is a final state
        for (NFAState nfaState : reachAbleStates) {
            if (finalStates.contains(nfaState)) {
                return true;
            }
        }
        return false;

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
        if (!transitions.containsKey(from)) {
            return new HashSet<>(); // No transitions from this state
        }

        Map<Character, Set<NFAState>> stateTransitions = transitions.get(from);
        if (stateTransitions == null || !stateTransitions.containsKey(onSymb)) {
            return new HashSet<>(); // No transition for this symbol
        }

        return stateTransitions.get(onSymb); // Return the states that can be reached
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> eClosures = new LinkedHashSet<NFAState>();
        eClosures.add(s);
        eClosures = eClosureSetBuilder(eClosures, s);
        return eClosures;
    }

    /**
     * Overloaded method
     * Rebinds the state into an equivalent NFAState to pass to eClosure(NFAState)
     *
     * @param state - The target state to find all E-Closures for.
     * @return null - on state not found, A set of the E-Closures for the state in
     *         normal operation
     */
    public Set<NFAState> eClosure(State state) {
        for (NFAState current : allStates) {
            if (current.equals(state)) {
                return eClosure(current);
            }
        }
        // State not found
        return null;
    }

    /**
     * Recursive function to explore all reachable states through Epsilon "e" from a
     * given state
     *      This works as a depth first search as each call to the function descends the depth of the map
     *      while using the set to keep track of which states have been visited. The only modification to
     *      a depth first search is the branching. This is necessary as NFAs can have multiple epsilon transitions
     *      that may not be traversed in certain circumstances without branching.
     * 
     * @param set - the in progress set
     * @param s   - the current state to explore the "e" transitions
     * @return - Upon completion the all reachable states from "e".
     */
    private Set<NFAState> eClosureSetBuilder(Set<NFAState> set, NFAState s) {
        // Check if the state has any transitions; otherwise, return the current set
        if (!transitions.containsKey(s)) {
            return set;
        }
        Set<NFAState> sTransitions = transitions.get(s).get('e');
        if (sTransitions != null) {
            Iterator<NFAState> iterE = sTransitions.iterator();
            while (iterE.hasNext()) {
                NFAState nextState = iterE.next();
                if (!set.contains(nextState)) { // Prevents unnecessary branches
                    set.add(nextState);
                    set = eClosureSetBuilder(set, nextState);
                }
            }
        }
        return set;

    }

    @Override
    public int maxCopies(String s) {
        // Start with start States epsilon closure
        Set<NFAState> currentStates = eClosure(q0);

        // Start tracking the number of copies nfa can have
        int maxCopies = currentStates.size();

        // Loop over each char in the input string
        for (char c : s.toCharArray()) {
            // Stores all states reachable from currentState
            Set<NFAState> nextStates = new HashSet<>();

            // For each currently active state in currentState we check which states can be

            // reached using the transition symbol "c"
            for (NFAState state : currentStates) {
                // To nextStates we add a set of to states given c
                nextStates.addAll(getToState(state, c));
            }

            // Get epsilon closure for all new states
            Set<NFAState> eClosureStates = new HashSet<>();
            for (NFAState state : nextStates) {
                eClosureStates.addAll(eClosure(state));
            }

            // Update currentStates and track max number of active states
            currentStates = eClosureStates;
            maxCopies = Math.max(maxCopies, currentStates.size());
        }

        return maxCopies;
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
        Set<NFAState> toStatesSet = new HashSet<>();

        for (String toStateName : toStates) {
            NFAState possibleToState = (NFAState) getState(toStateName);
            // check possibleToState is in set of states
            if (possibleToState == null) {
                return false;
            }
            // possibleToState is valid add to possible allToStates
            toStatesSet.add(possibleToState);
        }
        // Updating transition map

        // Check if fromState transitioning on onSymb already has a toState
        if (transitions.get(NFAFromState).get(onSymb) != null) {
            // Adding all the new toStates to the set of toStates that already exist on
            // onSymb
            for (NFAState nfaState : toStatesSet) {
                transitions.get(NFAFromState).get(onSymb).add(nfaState);
            }
        } else {
            // Creating the inner transition map for fromState
            transitions.putIfAbsent(NFAFromState, new HashMap<>());
            // Add or update the transition for fromState
            transitions.get(NFAFromState).put(onSymb, toStatesSet);

        }
        return true;
    }

    @Override
    public boolean isDFA() {
        // For all the transitions check the key for 'e' and the value to length = 1
        for (Entry<NFAState, Map<Character, Set<NFAState>>> transition : transitions.entrySet()) {
            // Get the transition map for this state
            Map<Character, Set<NFAState>> transitionMap = transition.getValue();

            // Check for epsilon ('e') transitions
            if (transitionMap.containsKey('e')) {
                return false; // NFA detected
            }

            // Check that each symbol maps to at most one state (deterministic)
            for (Set<NFAState> toStates : transitionMap.values()) {
                if (toStates.size() > 1) {
                    return false; // Non-deterministic transition found
                }
            }
        }
        return true; // If no NFA properties found, it's a DFA
    }

}
