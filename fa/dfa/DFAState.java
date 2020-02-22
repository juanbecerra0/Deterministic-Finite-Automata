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

    /**
     * Enumerated types for a DFAState object
     */
    public enum Type {
        START,
        FINAL,
        STARTANDFINAL,
        NORMAL
    }

    private Type type;

    /**
     * Constructs a DFAState object given a unique name and an enumerated type
     * 
     * Types include:
     * - START
     * - FINAL
     * - STARTANDFINAL
     * - NORMAL
     * 
     * If invalid type is given, throws IllegalArgumentException
     * 
     * @param name
     * @param type
     * 
     * @throws IllegalArgumentException
     */
    public DFAState(String name, String type) {
        switch(type) {
            case "START":
                this.type = Type.START;
                break;
            case "FINAL":
                this.type = Type.FINAL;
                break;
            case "STARTANDFINAL":
                this.type = Type.STARTANDFINAL;
                break;
            case "NORMAL":
                this.type = Type.NORMAL;
                break;
            default:
                throw new IllegalArgumentException("DFAState type " + type + " is not a valid type! " +
                "Try START, FINAL, STARTANDFINAL, or NORMAL.");
        }

        this.name = name;
    }

    /**
     * Changes the type of a DFAState object.
     * 
     * If invalid type is given, throws IllegalArgumentException
     * 
     * @param type
     */
    public void changeType(String type) {
        switch(type) {
            case "START":
                this.type = Type.START;
                break;
            case "FINAL":
                this.type = Type.FINAL;
                break;
            case "STARTANDFINAL":
                this.type = Type.STARTANDFINAL;
                break;
            case "NORMAL":
                this.type = Type.NORMAL;
                break;
            default:
                throw new IllegalArgumentException("DFAState type " + type + " is not a valid type! " +
                "Try START, FINAL, STARTANDFINAL, or NORMAL.");
        }
    }

    /**
     * Returns a string representing the DFAState's type.
     * 
     * A returned empty string means something has gone very wrong.
     * 
     * @return Type (String)
     */
    public String getType() {
        switch(type) {
            case START:
                return "STATE";
            case FINAL:
                return "FINAL";
            case STARTANDFINAL:
                return "STARTANDFINAL";
            case NORMAL:
                return "NORMAL";
            default:
                return "";
        }
    }

}