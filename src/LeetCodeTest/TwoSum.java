package LeetCodeTest;

public class TwoSum {
    void main(){
        System.out.println(twoSum(new int[]{2, 7, 11, 15}, 9));

    }
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length ; i++) {
                for(int j = i+1; j < nums.length ; i++){
                    if(nums[i] + nums[j] == target){
                        return new int[]{i,j} ;
                    }
                }
            }
            return new int[]{};



        }
}
