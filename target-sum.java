// https://leetcode.com/problems/target-sum/
class target-sum {
     // tc -> n*r where r is the range of every possible sum
    // sc-> n*r recursion tree will go till array length 
    // and cache may contain n*r elements
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
