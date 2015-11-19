import java.util.ArrayList;

public class State {

	private int index;
	private Condition[] conditions;
	private ArrayList<Integer> transitions;

	
	/**
	 * Create a new State object
	 * @param newIndex Index this state is stored at in the FSA's states array
	 * @param newDataValues Values representing the condition values
	 * for each variable in this state.
	 */
	public State(int newIndex, DataValue[] newDataValues) {
		Condition[] newConditions = new Condition[newDataValues.length];
		for (int i = 0; i < newDataValues.length; i++)
			newConditions[i] = new Condition(newDataValues[i]);
		this.index = newIndex;
		this.conditions = newConditions;
		transitions = new ArrayList<Integer>();
	}

	
	/**
	 * Determines whether a set of values fit the definition of this state.
	 * @param dataValues Values of variables at a specific point of time
	 * @return true if these values satisfy every condition of this state
	 */
	boolean isStateSatisfiedBy(DataValue[] dataValues) {
		for (int i = 0; i < this.conditions.length; i++) {
			Condition stateCondition = this.conditions[i];
			if (!stateCondition.isConditionSatisfiedBy(dataValues[i]))
				return false;
		}
		return true;
	}

	
	/**
	 * Adds a transition between this state and the state found at the provided
	 * index in the FSA's states array, if this transition does not already
	 * exist in the current FSA.
	 * @param nextStateIndex The index of the next state
	 */
	void addTransitionIfNotPresent(int nextStateIndex) {
		if (!this.transitions.contains(nextStateIndex))
			this.transitions.add(nextStateIndex);
	}

	
	/**
	 * @return true if there is a transition between this state and the state
	 * at the provided index
	 */
	boolean isTransitionPresent(int nextStateIndex) {
		return this.transitions.contains(nextStateIndex);
	}

	
	/**
	 * @return the index this state is stored at in the array of states for
	 * the current FSA
	 */
	public int getIndex() {
		return index;
	}
	
	
	public String toString() {
		String output = "\tConditions: ";
		for (Condition c : this.conditions) {
			output += c.toString() + " ";
		}
		output += "\n\t\tTransitions: ";
		for (int i = 0; i < transitions.size(); i++) {
			output += transitions.get(i);
			if (i != transitions.size() - 1)
				output += ", ";
		}
		return output;
	}
}