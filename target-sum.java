// https://leetcode.com/problems/target-sum/
class target-sum {
   //tc - n*sum because at each index we calculate every possible sum at most once
   //sc - n - recursion tree will go till array length
   public int findTargetSumWays(int[] nums, int S) {
       return getFindTargetSum(nums, 0, S, 0,
                               new HashMap<String, Integer>());
   }
   
   private int getFindTargetSum(int[] nums, int currSum, 
                                int targetSum, int index,
                               Map<String, Integer> cache){
       String key = index + " " + currSum;
       if(cache.containsKey(key))
           return cache.get(key);
       
       if(index==nums.length){
           if(currSum == targetSum)
               return 1;
           else 
               return 0;
       }
       int add = getFindTargetSum(nums, currSum + nums[index], 
                                 targetSum, index+1, cache);
       int minus = getFindTargetSum(nums, currSum - nums[index], 
                                 targetSum, index+1, cache);
       
       cache.put(key, add+minus);
       
       return cache.get(key);
   }
}
