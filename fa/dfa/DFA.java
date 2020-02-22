package fa.dfa;

import fa.State;

import java.util.Set;
import java.util.HashSet;
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

    // The start state of the DFA. Included in "states" and can be included in "finals"
    private DFAState startState;
    // The set of all states, including all finals and the start state.
    private Set<DFAState> states;
    // The set of all final states, which is a subset of all states.
    private Set<DFAState> finalStates;
    // The set of all known alphabet symbols.
    private Set<Character> alphabet;
    // The HashMap of all transitions. Given a key (concat String of state name and transition), returns a state object reference.
    private Map<String, DFAState> transitions;

    /**
     * Initializes all set variables
     */
    public DFA(){
        states = new HashSet<DFAState>();
        finalStates = new HashSet<DFAState>();
        alphabet = new HashSet<Character>();
        transitions = new HashMap<String, DFAState>();
    }

    @Override
    public void addStartState(String name) {
        // Initializes the start state and adds it to the set of all states.
        startState = new DFAState(name);
        states.add(startState);
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

        // Create a "key" values, which is the name of a state and the symbol concatenated.
        String inputKey = fromState + onSymb;

        // Maps the key to the corresponding state
        transitions.put(inputKey, newState);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public State getToState(DFAState from, char onSymb) {
        // TODO Auto-generated method stub
        return null;
    }

}