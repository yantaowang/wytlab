package leetcode;

public class Ltest {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.removeDuplicates(new int[]{0,0,1,13});
//        System.out.println(solution.plusOne(new int[]{9,9}).toString());
        System.out.println(solution.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));

        solution.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);

        ThreeSolution threeSolution = new ThreeSolution();
        threeSolution.findSubstring("barfoothefoobarman",new String[]{"foo","bar"});
        threeSolution.findShortestSubArray(new int[]{2,1,1,2,1,3,3,3,1,3,1,3,2});
    }
}
