package fa.dfa;

import fa.State;
import java.util.Set;
import java.util.HashSet;
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

    public DFA(){
        HashMap<String, DFAstate> transitions = new HashMap<String, DFAstate>();
        HashMap<String, DFAState> sigma = new HashSet<String, DFAState>();
    }

    @Override
    public void addStartState(String name) {
        sigma.add(name, new DFAState(name, "START"));
    }

    @Override
    public void addState(String name) {
        sigma.add(name, new DFAState(name, "NORMAL"));

    }

    @Override
    public void addFinalState(String name) {
        if(sigma.contains(name){

        }        sigma.add(name, new DFAState(name, "NORMAL"));
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        // TODO Auto-generated method stub

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