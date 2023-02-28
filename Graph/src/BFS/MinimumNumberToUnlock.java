package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumNumberToUnlock {
    /**
     * What is described in the title is the common combination lock in our life. If there is no restriction, the minimum number of dials is easy to calculate, just like we usually open the combination lock and go straight to the combination dial.
     *
     * But the difficulty now is that it cannot appear deadends, how to calculate the minimum number of rotations?
     *
     * In the first step, we don’t care about all the restrictions, regardless deadendsof targetthe restrictions of and , let’s think about a question: If you are asked to design an algorithm to enumerate all possible password combinations, how do you do it ?
     *
     * Exhaustive, let’s make it simpler, if you only turn the lock once, how many possibilities are there? There are a total of 4 positions, and each position can be turned up or down, that is, there are 8 possibilities, right?
     * Think about it carefully, this can be abstracted into a graph, each node has 8 adjacent nodes , and you are asked to find the shortest distance, isn’t this a typical BFS, the framework can come in handy, first write a The "simple" BFS framework code and other things:
     * */
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    void allCombination(String target) {
        Queue<String> q = new LinkedList<>();
        q.offer("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                System.out.println(cur); //jude whether its target
                for (int j = 0; i < 4; i++) {
                    String up = plusOne(cur, j);
                    String down = minusOne(cur, j);
                    q.offer(up);
                    q.offer(down);
                }
            }
        }
    }
    /**
     * 1. Will go back. For example, we "0000"dialed "1000", but "1000"when , we will dial out another one "0000", which will cause an infinite loop.
     *
     * 2. There is no termination condition. According to the requirements of the topic, we targetshould .
     *
     * 3. Without deadendscorrect handling, these "death codes" cannot appear logically, which means that you need to skip them when you encounter these codes.
     * */

    int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
