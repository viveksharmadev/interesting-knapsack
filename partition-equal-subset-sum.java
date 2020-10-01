// https://leetcode.com/problems/partition-equal-subset-sum/
class partition-equal-subset-sum {
   // tc -> 2^n, sc-> n
   public boolean canPartition(int[] nums) {
       int sum = 0;
       for(int num : nums){
           sum += num;
       }
       
       if(sum%2!=0) return false;        
       
       return doesPartition(nums, sum/2, 0); 
   }
   
   private boolean doesPartition(int[] nums, int sum, int index){
       if(sum==0) return true;
       
       if(index>nums.length-1) return false;
       
       if(nums[index] <= sum)
           if(doesPartition(nums, sum-nums[index], index+1))
               return true;        
       
       return doesPartition(nums, sum, index+1);
   }
}

// Using Memoization
class partition-equal-subset-sum {
   // tc -> n(nums)*s(sum), sc-> n*s
   public boolean canPartition(int[] nums) {
       int sum = 0;
       for(int num : nums){
           sum += num;
       }
       
       if(sum%2!=0) return false;        
       
       return doesPartition(nums, sum/2, 0, new Boolean[nums.length][sum]); 
   }
   
   private boolean doesPartition(int[] nums, int sum, int index,
                                Boolean[][] cache){
       if(sum==0) return true;
       
       if(index>nums.length-1) return false;
       
       if(cache[index][sum]!=null)
           return cache[index][sum];
           
       if(nums[index] <= sum){
           if(doesPartition(nums, sum-nums[index], index+1, cache)){
               cache[index][sum] = true;
               return true;        
           }
       }
       
       cache[index][sum] =  doesPartition(nums, sum, index+1, cache);
       return cache[index][sum];
   }
}

// May be brtual force to knapsack(Not optimized) (Time limit Exceeded)

class Solution {
    // tc -> n*target, sc-> target
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        
        if(sum%2!=0) return false;
        
        return doesPartition(nums, sum/2, 0, new HashMap<String, Boolean>());
    }
    
    private boolean doesPartition(int[] nums, int target, int index,
                                 Map<String, Boolean> cache){        
        if(target==0) return true;
            
        String key = index + " " + target;
        
        if(index > nums.length-1) return false;
        
        if(cache.containsKey(key)) return cache.get(key);
        
        boolean firstOption = doesPartition(nums, target-nums[index],
                                           index+1, cache);
        boolean secondOption = doesPartition(nums, target, index+1, cache);
        
        boolean result = firstOption || secondOption;
        
        cache.put(key, result);
        
        return cache.get(key);
    }
}

 // Optimized but Still bad perfomance

class Solution {
    // tc -> n*target, sc-> target
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        
        if(sum%2!=0) return false;
        
        return doesPartition(nums, sum/2, 0, new HashMap<String, Boolean>());
    }
    
    private boolean doesPartition(int[] nums, int target, int index,
                                 Map<String, Boolean> cache){        
        if(target==0) return true;
            
        String key = index + " " + target;
        
        if(index > nums.length-1) return false;
        
        if(cache.containsKey(key)) return cache.get(key);
        
        if(doesPartition(nums, target-nums[index],
                                           index+1, cache)){
            cache.put(key, true);
            return true;
        }
        
        boolean secondOption = doesPartition(nums, target, index+1, cache);
        
        cache.put(key, secondOption);
        
        return cache.get(key);
    }
}

// Good Performance

class Solution {
    // tc -> n*target, sc-> target
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        
        int target = sum/2;
        
        if(sum%2!=0) return false;
        
        return doesPartition(nums, target, 0, new Boolean[nums.length][target+1]);
    }
    
    private boolean doesPartition(int[] nums, int target, int index,
                                 Boolean[][] cache){        
        if(target < 0) return false;
        
        if(target==0) return true;
            
        if(index > nums.length-1) return false;
        
        if(cache[index][target]!=null) return cache[index][target];
        
        if(doesPartition(nums, target-nums[index],
                                           index+1, cache)){
            cache[index][target] = true;
            return true;
        }
        
        cache[index][target] = doesPartition(nums, target, index+1, cache);
        
        return cache[index][target];
    }
}

