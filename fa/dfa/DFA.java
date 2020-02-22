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
    DFAState start;
    private Map<String, DFAState> transitions;
    private Set<DFAState> states;
    private Set<DFAState> finals;
    private Set<String> alphabet;

    public DFA(){
        transitions = new HashMap<String, DFAState>();
        states = new HashSet<DFAState>();
        finals = new HashSet<DFAState>();
        alphabet = new HashSet<String>();
        
    }

    @Override
    public void addStartState(String name) {
        start = new DFAState(name);
        states.add(start);
    }

    @Override
    public void addState(String name) {
        states.add(new DFAState(name));
    }

    @Override
    public void addFinalState(String name) {
        DFAState state = new DFAState(name);
        states.add(state);
        finals.add(state);
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        String key = fromState+onSymb;
        if(sigma.containsKey(fromState) && sigma.containsKey(toState))

    }

    @Override
    public Set<? extends State> getStates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State getStartState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Character> getABC() {
        // TODO Auto-generated method stub
        return null;
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