package fa.dfa;

import fa.State;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * Class that represents a deterministic finite automata. Implements the 
 * DFAInterface (and by proxy, the FAInterface). DFADriver creates an instance 
 * of this class, reads in data from test files, calls state methods, and 
 * prints the output.
 * 
 * @author Juan Becerra
 * @author Keegan Saunders
 */
public class DFA implements DFAInterface {

    /**
     * Class used as a key value pair for transition functions
     */
    private class StateTransitionPair {

        // The hashcode computed from the state name string and transition char
        private String state;
        private char transition;
        private int hashCode;

        /**
         * Constructor for StateTransitionPair. Simply creates a hashcode from the 
         * concatenated state name and a transition.
         * 
         * @param s
         * @param c
         */
        public StateTransitionPair(String s, char c) {
            this.state = s;
            this.transition = c;
            this.hashCode = (s + c).hashCode();
        }

        /**
         * Returns unique computer hashcode.
         * 
         * Used to ensure that the key value of transitions Map is unique.
         * 
         * @return hashCode
         */
        @Override
        public int hashCode() {
            return hashCode;
        }

        /**
         * @return the state string
         */
        public String getState() {
            return state;
        }

        /**
         * @return the transition char
         */
        public char getTransition() {
            return transition;
        }

        /**
         * Returns whether two STP's are equal.
         * 
         * Used to ensure that the key value of transitions Map is unique.
         */
        @Override
        public boolean equals(Object object) {
            StateTransitionPair otherSTP = (StateTransitionPair) object;
            return this.hashCode() == otherSTP.hashCode();
        }

    }

    // The start state of the DFA. Included in "states" and can be included in "finals"
    private DFAState startState;
    // The set of all states, including all finals and the start state.
    private Set<DFAState> states;
    // The set of all final states, which is a subset of all states.
    private Set<DFAState> finalStates;
    // The set of all known alphabet symbols.
    private Set<Character> alphabet;
    // The HashMap of all transitions. Given a key, a StateTransitionPair object defined above, returns a state object reference.
    private Map<StateTransitionPair, DFAState> transitions;

    // The current state in the DFA simulation. Used in "accepts" and "getToState" methods
    private DFAState currentState;

    /**
     * Initializes all set variables
     */
    public DFA(){
        states = new LinkedHashSet<DFAState>();
        finalStates = new LinkedHashSet<DFAState>();
        alphabet = new LinkedHashSet<Character>();
        transitions = new HashMap<StateTransitionPair, DFAState>();
    }

    @Override
    public void addStartState(String name) {
        // Initializes the start state and adds it to the set of all states.
        startState = new DFAState(name);
        states.add(startState);

        // Also, initialize the start state as the currentState
        currentState = startState;
    }

    @Override
    public void addState(String name) {
        // Initializes a new state and adds to set of all states.
        DFAState newState = new DFAState(name);

        // If new state is a duplicate, adding is ignored.
        states.add(newState);
    }

    @Override
    public void addFinalState(String name) {
        // Initializes a new state and adds to set of all states and final states.
        DFAState newState = new DFAState(name);

        // If new state is a duplicate, adding is ignored.
        states.add(newState);
        finalStates.add(newState);
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        // Create a "toState" object
        DFAState newState = new DFAState(toState);

        // Check if the "new state" is within our set of all states. If not, simply return.
        if(!states.contains(new DFAState(toState)))
            return;

        // Add symbol to alphabet if it is not already in there
        alphabet.add(onSymb);

        // Create a "key" values, which is a StateTransitionPair object
        StateTransitionPair stp = new StateTransitionPair(fromState, onSymb);

        // Maps the key to the corresponding state
        transitions.put(stp, newState);
    }

    @Override
    public Set<? extends State> getStates() {
        // Returns set of all states
        return states;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        // Returns set of all final states
        return finalStates;
    }

    @Override
    public State getStartState() {
        // Returns the start state
        return startState;
    }

    @Override
    public Set<Character> getABC() {
        // Returns the alphabet set
        return alphabet;
    }

    @Override
    public DFA complement() {
        // Create a new DFA instance to return later
        DFA dfaCompliment = new DFA();

        // Copy over the set of all states
        Set<DFAState> finalStatesCompliment = new LinkedHashSet<DFAState>();
        for(DFAState s: states) {
            finalStatesCompliment.add(s);
        }

        // Find the difference of all states and finals states (ie, all non-final states are now final)
        finalStatesCompliment.removeAll(finalStates);
        for (DFAState s: finalStatesCompliment) {
            dfaCompliment.addFinalState(s.getName());
        }

        // Copy over the start state
        dfaCompliment.addStartState(this.startState.getName());

        // Copy over all of the normal states
        for(DFAState s: states) {
            dfaCompliment.addState(s.getName());
        }

        // Copy over the transitions and, by proxy, the alphabet
        transitions.forEach((stp, state) ->
            dfaCompliment.addTransition(
                stp.getState(),
                stp.getTransition(),
                state.getName()
            )
        );

        // Finally, return the compliment
        return dfaCompliment;
    }

    @Override
    public boolean accepts(String s) {
        // Parse through the input string
        s = s.trim();

        // If the input string is "e" (empty string), evaluate the initial state and 
        // return true if it is final.
        if(s.equals("e")) {
            if (finalStates.contains(startState)) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            // Gets the state that the current state and symbol would iterate to
            // If this variable is null, then the transition did not exist
            DFAState newState = (DFAState) getToState(currentState, s.charAt(i));

            // If the newState exists, reassign current state.
            // Otherwise, return false as this is an indication of an input string that would cause
            // a DFA error to occur
            if (newState != null) {
                currentState = newState;
            } else {
                currentState = startState;
                return false;
            }
        }

        // If the current state we made it to is final, return true.
        // Otherwise, return false as the string would not be accepted.
        if (finalStates.contains(currentState)) {
            currentState = startState;
            return true;
        } else {
            currentState = startState;
            return false;
        }
    }

    @Override
    public State getToState(DFAState from, char onSymb) {
        // Make a STP object out of the current state name and transition
        StateTransitionPair stp = new StateTransitionPair(from.getName(), onSymb);

        // Return the value that this stp key looks up
        // Returns null if the transition does not exist
        return transitions.get(stp);
    }

    @Override
    public String toString() {
        // Build a string using a string builder
        StringBuilder returnString = new StringBuilder();

        // Q Section
        returnString.append("Q = { ");
        for(DFAState s: states) {
            returnString.append(s.getName() + " ");
        }
        returnString.append("}\n");

        // Sigma section
        returnString.append("Sigma = { ");
        for(char c: alphabet) {
            returnString.append(c + " ");
        }
        returnString.append("}\n");

        // delta section
        returnString.append("delta =\n\t\t");
        // First, graph all transitions
        for(char c: alphabet) {
            returnString.append(c + "\t");
        }
        returnString.append("\n");
        // Then, for each state, where you would go given a transition
        for(DFAState s: states) {
            returnString.append("\t" + s.getName() + "\t");
            for(char c: alphabet) {
                StateTransitionPair stp = new StateTransitionPair(s.getName(), c);
                // If transition exists, print target
                // Else, print whitespace
                if(transitions.containsKey(stp)) {
                    returnString.append(transitions.get(stp).getName() + "\t");
                } else {
                    returnString.append(" \t");
                }
            }
            returnString.append("\n");
        }

        // q0 section
        returnString.append("q0 = " + startState.getName() + "\n");

        // F section
        returnString.append("F = { ");
        for(DFAState fs: finalStates) {
            returnString.append(fs.getName() + " ");
        }
        returnString.append("}\n");

        // Finally, return constructed string
        return returnString.toString();
    }
}