package leetcode;

public class Ltest {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.removeDuplicates(new int[]{0,0,1,13});
//        System.out.println(solution.plusOne(new int[]{9,9}).toString());
        System.out.println(solution.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}
