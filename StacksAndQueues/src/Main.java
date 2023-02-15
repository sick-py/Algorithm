public class Main {
    /**
     * a stack is a linear data structure that follows the Last In First Out (LIFO) principle. It can be thought of as a stack of plates in a cafeteria, where the last plate placed on top of the stack is the first one to be removed.
     * */
    public static void main(String[] args) {
        int res = 0;
        String[] test1 = {"5","-2","4","C","D","9","+","+"};

        BaseballGame test = new BaseballGame();
        res = test.calPoints(test1);

        //System.out.println(res);
    }
}