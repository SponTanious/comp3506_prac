package pracs.prac3;

import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        State8 startState = new State8("1348627_5");//args[0]);
        State8 finalState = new State8("1238_4765");//args[1]);

        //A Star Search
        printComparison(startState, finalState);
        System.out.println("A STAR SOLUTION");
        System.out.print("Nodes Visited: ");
        long startTime = System.nanoTime();
        TreeNode<State8> solution = aStar(startState, finalState);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.print("Time (ns): ");System.out.println(timeElapsed);
        LinkedList<State8> solutionOrder = new LinkedList<>();
        //Print Solution
        TreeNode<State8> currentNode = solution;
        solutionOrder.addFirst(currentNode.state);
        while(currentNode.parent != null){
            currentNode = currentNode.parent;
            solutionOrder.addFirst(currentNode.state);
        }
        System.out.printf("Steps: %d \n", solutionOrder.size()-1);
//        while(!solutionOrder.isEmpty()){
//            solutionOrder.poll().printState();
//        }
    }


    public static TreeNode<State8> aStar(State8 startState, State8 finalState){
        TreeNode<State8> root = new TreeNode<>(startState, calcHeuristic(startState, finalState));
        PriorityQueue<TreeNode<State8>> stateQueue = new PriorityQueue<>();
        stateQueue.offer(root);
        HashSet<String> visited = new HashSet<>();

        int count = 0;
        while(true) {
            TreeNode<State8> t = stateQueue.poll();
            visited.add(t.state.state);

            //Checks Goal state
            if (t.state.myEquals(finalState)) {
                System.out.println(count);
                return t;
                // find an rearrange order
            }

            //Looks at successors
            for(State8 successor : t.state.getActionStates()){
                //Checks successor is in tree
                if(visited.contains(successor.state)){
                    continue;
                }

                //If successor is not in tree
                visited.add(successor.state);
                t.addChild(successor, calcHeuristic(successor, finalState));
            }
            for (TreeNode<State8> child : t.children){
                stateQueue.add(child);
            }
            count++;
        }
    }

    public static int calcHeuristic(State8 currentState, State8 finalState){
        char[] charsCurrent = currentState.state.toCharArray();
        int indexI = 0;
        int stepsSum = 0;
        for(char aChar : charsCurrent){
            int indexJ = finalState.state.indexOf(aChar);
            int[] posI = findPosition(indexI);
            int[] posJ = findPosition(indexJ);

            int steps = Math.abs(posI[0]-posJ[0]) + Math.abs(posI[1]-posJ[1]);
            stepsSum += steps;
            indexI++;
        }
        return stepsSum;


    }

    private static int[] findPosition(int index){
        int[] pos = {(int) Math.floor(index / 3.0), index%3};
        return pos;
    }


    public static void printComparison(State8 currentState, State8 finalState){
        System.out.print(currentState.state.substring(0, 3));System.out.print("  ");System.out.println(finalState.state.substring(0, 3));
        System.out.print(currentState.state.substring(3, 6));System.out.print("  ");System.out.println(finalState.state.substring(3, 6));
        System.out.print(currentState.state.substring(6, 9));System.out.print("  ");System.out.println(finalState.state.substring(6, 9));
        System.out.println();
    }
}
