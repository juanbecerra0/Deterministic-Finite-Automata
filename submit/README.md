# Project 1: Deterministic Finite Automata

* Authors: Keegan Saunders and Juan Becerra
* Class: CS361 Section 1
* Semester: Spring 2020

## Overview

Given a properly formatted file, constructs a deterministic finite automata
object, fit with several methods that allows a user to add states, symbols, 
and simulate a DFA parsing a string. Within the driver class, several tests 
can be given as input and the program will output DFA information in set 
notation to the console.

## Compiling and Using

To compile the program, go to the root project directory and type this into the console:

```
javac fa/dfa/DFADriver.java
```

To run the compiled program's test suite, type this into the console:

```
java fa.dfa.DFADriver ./tests/(testfile)
```

## Discussion

When initally designing this program, we first wanted to tackle how we would 
approach storing different types of states and transitions using Set and 
Map implementations. Storing the start state was easy enough, as it could 
just be stored as a single DFAState variable in a DFA object, but storing 
the remaining states using sets posed a lot of questions, such as:

- Would DFA keep track of the states' types?
- Would DFAState keep track of their own type, perhaps through enumeration?
- Do we want multiple sets to keep track of different states?
- Do we want a single set, but have states tag themselves?

Eventually we decided on this design:

Make individual sets for the set of all state and the set of all final states.
Furthermore, we should implement them using a LinkedHashSet in order to keep 
the order of the states as they are scanned in the file (this would help us 
in creating a valid toString() output). In addition, make a set of the alphabet,
which is updated in the addTransition. We also overrided the hashCode() and equals() 
methods to make them comparable relative to a DFAState's name (we weren't sure if this
step was necessary, but we wanted to make sure that attempting to add duplicate states, 
i.e. states with the same name, was not allowed in our sets).

As for mapping transitions to states, we created a new private class in DFA called 
a StateTransitionPair. We originally wanted to use a Pair or Tuple like in C++ or 
C#, but we couldn't find a good type in the base Java library. This STP object 
would serve as a key in our map data structure, which would map directly to 
a corresponding state. In other words, given a STP (a current state String and 
a transition char), the map would return the state that it would go to. We also 
overrided the hashCode() and equals() functions here, much like in DFAState.

Overall, the Set and Map implementations made this project fairly easy. The constant 
time required for reffering to stored states, alphabet symbols, and transitions made 
it simple to program the more complicated functions, like complement() and toString(). 
Complement simply copies the data over from the original DFA as is, except for the 
final states. The final states set is computed by taking the set of all states and 
finding the difference with the set of all original final states using the removeAll
Set method. This effectively makes it so DFAC's final states set is the exact opposite 
of the original. 

In the end, we were able to create a working DFA simulation that passed all of the tests.
Along the way, we learned just how effective sets and maps can be for accessing large data
sets in an object-oriented program. In addition, we were able to apply a lot of clever 
object-oriented design principles that made our code make sense compared to how we'd 
imagine a DFA in set notation.

## Testing

Our test-driven development consisted of frequently running the given test files, tests
p1tc1.txt, p1tc2.txt, and p1tc3.txt. This was mostly to test our toString() output to 
see if we implemented our design correctly, but we also used the driver to test to make 
sure that the strings that would either be accepted or denied matched up with the provided
sample output. Finally, we'd see if our compliment was correct, which was one of the 
tougher parts of the assignment.

When we first implemented toString() it seemed that our methods were working as intended, 
but the order of elements was automatically sorting unlike in the provided sample outputs.
We were able to fix this by instead making our Sets implement the LinkedHashSet class, rather 
than the HashSet class. This would ensure that objects were stored in the order that they were 
read into the file. From there, it looked as though our accepts method was working as intended. 
We'd parse through the input string, call the getToState method, which would use its inputs to 
create STP objects, and it would return whatever our map would return (a state object). If it 
returned null, this indicated that the state-transition pair did not exist, and that we should 
immediately return false on accepts().

We assumed that accepts() was workingcorrectly, but it did not work for string "e" in the first 
test file. Our DFAC was always incorrect because both the DFA and DFAC rejected the string "e". It
wasn't until a somewhat embarrassing Piazza post that we learned that the input string "e" signified 
and empty string, which would just require us to evaluate the initial state and return true if it was 
final. At this point, all of our tests were passing, and our output matched the samples.

## Extra Credit

Extra credit was not offered in this assignment, but we won't turn it down ;)
