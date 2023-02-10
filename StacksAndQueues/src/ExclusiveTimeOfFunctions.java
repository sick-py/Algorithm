import java.util.ArrayDeque;
import java.util.Deque;

public class ExclusiveTimeOfFunctions {
    /**
     * We are given an integer number n, representing the number of functions running in a single-threaded CPU, and a list of strings, where each string represents the start or end timestamp of a specific function. Each function has a unique ID between 0 and n - 1. Compute the exclusive time of the functions in the program.
     *
     * Note: Exclusive time is the sum of the execution times for all the calls to a specific function.
     *
     * We’ll solve this problem using the stack containing the starting time of all functions in the program.
     *
     * First, obtain the function ID, starting or ending time, and timestamp from each string in the given list.
     * If the string contains “start”, push the current log details to the stack.
     * Otherwise, we pop the log details from the stack and add the execution time of the current function in the actual exclusive time.
     * If the stack is not empty, the current function is a child function. Thus, we subtract the execution time of this function from the parent function. We decrease the time in the parent function in advance.
     * We store the execution time of each function at the index equal to the function ID in the result array.
     * When the same function is called recursively, we add the function’s execution time in the current value at the specific index.
     *
     * {function_id}:{"start" | "end"}:{timestamp}
     * */
    class Log {
        int id;
        boolean isStart;
        int time;

        public Log(String s) {
            String[] strs = s.split(":");
            id = Integer.valueOf(strs[0]);
            isStart = strs[1].equals("start");
            time = Integer.valueOf(strs[2]);
        }
    }

    int[] time(int n, String[] logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] res = new int[n];
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                // Add the execution time of the current function in the actual result
                res[top.id] += (log.time - top.time + 1);
                // If the stack is not empty, subtract the current child function execution time
                // from the parent function
                if (!stack.isEmpty()) {
                    res[stack.peek().id] -= (log.time - top.time + 1);
                }
            }
        }
        return res;
    }
}
