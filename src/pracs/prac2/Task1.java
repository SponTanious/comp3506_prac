package pracs.prac2;

import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        State8 startState = new State8("1348627_5");//args[0]);
        State8 finalState = new State8("1238_4765");//args[1]);

        //Breadth First Search
        printComparison(startState, finalState);
        System.out.println("BFS SOLUTION");
        System.out.print("Nodes Visited: ");
        long startTime = System.nanoTime();
        TreeNode<State8> solution1 = bfs(startState, finalState);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.print("Time (ns): ");System.out.println(timeElapsed);
        LinkedList<State8> solutionOrder = new LinkedList<>();
        TreeNode<State8> currentNode = solution1;
        solutionOrder.addFirst(currentNode.state);
        while(currentNode.parent != null){
            currentNode = currentNode.parent;
            solutionOrder.addFirst(currentNode.state);
        }
        System.out.printf("Steps: %d \n", solutionOrder.size()-1);
//        while(!solutionOrder.isEmpty()){
//            solutionOrder.poll().printState();
//        }

        //Depth First Search
        System.out.println("DFS SOLUTION");
        System.out.print("Nodes Visited: ");
        long startTime2 = System.nanoTime();
        TreeNode<State8> solution2 = dfs(startState, finalState);
        long timeElapsed2 = System.nanoTime() - startTime2;
        System.out.print("Time (ns): ");System.out.println(timeElapsed2);

        LinkedList<State8> solution2Order = new LinkedList<>();
        TreeNode<State8> currentNode2 = solution2;
        solution2Order.addFirst(currentNode2.state);
        while(currentNode2.parent != null){
            currentNode2 = currentNode2.parent;
            solution2Order.addFirst(currentNode2.state);
        }
        System.out.printf("Steps: %d \n", solution2Order.size()-1);
//        while(solution2Order.isEmpty() != true){
//            solution2Order.poll().printState();
//        }

    }

    public static TreeNode<State8> bfs(State8 startState, State8 finalState){
        TreeNode<State8> root = new TreeNode<>(startState);
        Queue<TreeNode<State8>> stateQueue = new LinkedList<>(); //add and poll
        stateQueue.add(root);
        HashSet<String> visited = new HashSet<>();

        int count = 0;
        while(true){
            TreeNode<State8>  t = stateQueue.poll();
            visited.add(t.state.state);

            if (t.state.myEquals(finalState)) {
                System.out.println(count);
                return t;
                // find an rearrange order
            }
            for(State8 successor : t.state.getActionStates()){
                //Checks successor is in tree
                if(visited.contains(successor.state)){
                    continue;
                }

                //If successor is not in tree
                visited.add(successor.state);
                t.addChild(successor);
            }

            for (TreeNode<State8> child : t.children){
                stateQueue.add(child);
            }

            count++;
        }
    }

    public static TreeNode<State8> dfs(State8 startState, State8 finalState){
        TreeNode<State8> root = new TreeNode<>(startState);
        Stack<TreeNode<State8>> stateStack = new Stack<>();
        stateStack.push(root);
        HashSet<String> expanded = new HashSet<>();

        int count = 0;
        while(true){
            TreeNode<State8>  t = stateStack.pop();

            expanded.add(t.state.state);
            if (t.state.myEquals(finalState)) {
                System.out.println(count);
                return t;
            }
            for(State8 successor : t.state.getActionStates()){
                //Checks successor is in expanded
                if(expanded.contains(successor.state)){
                    continue;
                }
                //If successor is not in tree
                expanded.add(successor.state);
                t.addChild(successor);
            }
            if(t.children.size() == 0) {
            }
            for (TreeNode<State8> child : t.children){
                stateStack.push(child);
            }
            count++;
        }
    }


//    public static State8 utilityFunction(ArrayList<State8> worldDynamics, State8 finalState){
//        //Calculate Scores
//        int maxScore = 0;
//        State8 maxState = worldDynamics.get(0);
//        for(State8 actionState : worldDynamics){
//
//            int tempScore = 0;
//            for(int i=0 ; i < actionState.state.length() ; i++){
//                if(actionState.state.charAt(i) ==  finalState.state.charAt(i)){
//                    tempScore += 1;
//                }
//            }
//            if(tempScore > maxScore){
//                maxState = actionState;
//                maxScore = tempScore;
//            }
//        }
//        return maxState;
//    }

    public static void printComparison(State8 currentState, State8 finalState){
        System.out.print(currentState.state.substring(0, 3));System.out.print("  ");System.out.println(finalState.state.substring(0, 3));
        System.out.print(currentState.state.substring(3, 6));System.out.print("  ");System.out.println(finalState.state.substring(3, 6));
        System.out.print(currentState.state.substring(6, 9));System.out.print("  ");System.out.println(finalState.state.substring(6, 9));
        System.out.println();
    }
}
