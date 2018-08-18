package pracs.prac3;

import java.util.ArrayList;

public class State8 {
    String state;
    int row;
    int col;

    public State8(String state) {
        this.state = state;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '_') {
                col = i % 3;
                row = (int) Math.floor(i / 3.0);
                break;
            }
        }
    }

    public void printState() {
        System.out.println(state.substring(0, 3));
        System.out.println(state.substring(3, 6));
        System.out.println(state.substring(6, 9));
        System.out.println();

    }

    public State8 moveUp() {
        if (row > 0) {
            return new State8(changePosition(row - 1, col));
        }
        return this;
    }

    public State8 moveDown() {
        if (row < 2) {
            return new State8(changePosition(row + 1, col));
        }
        return this;
    }

    public State8 moveLeft() {
        if (col > 0) {
            return new State8(changePosition(row, col - 1));
        }
        return this;
    }

    public State8 moveRight() {
        if (col < 2) {
            return new State8(changePosition(row, col + 1));
        }
        return this;
    }

    private String changePosition(int row, int col) {
        int pos1 = this.row * 3 + this.col;
        int pos2 = row * 3 + col;
        char[] stateCharArray = state.toCharArray();
        stateCharArray[pos1] = stateCharArray[pos2];
        stateCharArray[pos2] = '_';
        return new String(stateCharArray);
    }

    public ArrayList<State8> getActionStates() {
        ArrayList<State8> worldDynamics = new ArrayList<State8>();
        if (!myEquals(this.moveUp())) {
            worldDynamics.add(this.moveUp());
        }
        if (!myEquals(this.moveDown())) {
            worldDynamics.add(this.moveDown());
        }
        if (!myEquals(this.moveLeft())) {
            worldDynamics.add(this.moveLeft());
        }
        if (!myEquals(this.moveRight())) {
            worldDynamics.add(this.moveRight());
        }
        return worldDynamics;
    }

    public boolean myEquals(State8 b) {
        return this.state.equals(b.state);
    }
}
