package com.greedy;

//#45 https://leetcode.com/problems/jump-game-ii/
public class JumpGame {
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] arr = {1,2,1,1,1};
        System.out.println(""+jumpGame.jump(arr));
    }

    //Solution inspired by
    //https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MinJumpToReachEnd.java
    private int jump(int[] nums) {
        //if there is only one element
        //you can reach end without any jump
        if(nums.length == 1) {
            return 0;
        }
        int count = 0;
        int i = 0;
        //loop until you find index such that its value takes you to end of the array
        while(i + nums[i] < nums.length - 1) {
            int maxVal = 0;
            int maxValIndex = 0;
            //iterate over all the possibilies of index i and find maximum index that can be reached.
            for(int j = 1; j <= nums[i]; j++) {
                //max index that can be reached
                int val = nums[j+i] + j;
                if(val > maxVal) {
                    //keep current max value
                    maxVal = val;
                    //keep index that we used to reach this max value
                    maxValIndex = j + i;
                }
            }
            //whatever is current maxVal is the maximum value of index that can be reached for given i
            //make this max jump, so assign i to index that got you this max value
            //since we choose to jump maximum index, it is greedy algorithm
            i = maxValIndex;
            //since you made one jump, increment counter
            count++;
        }
        //since our loop terminates when we still need to make 1 more jump
        //i.e. i + nums[i]. Adding nums[i] means we still need to make 1 more jump
        //add 1 to the return
        return count + 1;
    }
}
