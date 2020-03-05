package fa.dfa;

import fa.State;

/**
 * Class that represents a single state in a deterministic finite automata.
 * Extends the functionality of the abstract State class. Used by DFA instances 
 * to represent the IO nature of states in finite state machines.
 * 
 * @author Juan Becerra
 * @author Keegan Saunders
 */
public class DFAState extends State {

    // Hash code used in 
    private final int hashCode;

    /**
     * Constructs a DFAState object given a unique name.
     * initializes a hash code based on the name.
     * 
     * @param name
     */
    public DFAState(String name) {
        this.name = name;
        this.hashCode = name.hashCode();
    }

    /**
     * Returns a hashcode based on the DFAState's name
     * 
     * This is used to ensure that there are never duplicate states in DFA's sets
     * 
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

    /**
     * Returns whether or not two DFAState objects are the same, based on their names.
     * 
     * This is used to ensure that there are never duplicate states in DFA's sets
     * 
     * @param DFAState object
     */
    @Override
    public boolean equals(Object object) {
        DFAState otherState = (DFAState) object;
        return this.hashCode == otherState.hashCode();
    }

}