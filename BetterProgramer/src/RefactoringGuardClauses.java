public class RefactoringGuardClauses {
    /** Guard
     * https://betterprogramming.pub/refactoring-guard-clauses-2ceeaa1a9da
    In computer programming, a guard is a boolean expression that must evaluate to true if the program execution is to continue in the branch in question. Regardless of which programming language is used, guard code or a guard clause is a check of integrity preconditions used to avoid errors during execution.
     example:
    * */

    int doSomethingNormal() {
        if (everythingisgood()) {
            /**
             * lots and lots of code here
             * */
            return 1;
        } else {
            return 0; //a special case
        }
    }

    /**
     * In this case, and most of the time, you must reverse the logic to avoid using the reserved word else. The previous code would be rewritten as follows:
     *
     * Therefore, the particular cases that cause an exit of the method would be placed at the beginning of the method and act as guards in a way that avoids continuing through the satisfactory flow of the method.
     * */

    int doSomethingGood() {
        if (!everythingisgood()) {
            return 0;
        }

        /**
         * lots lots of code
         * */

        return 1;
    }

    private boolean everythingisgood() {
        return true;
    }


}
