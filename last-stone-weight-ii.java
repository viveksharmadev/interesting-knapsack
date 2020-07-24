// https://leetcode.com/problems/last-stone-weight-ii/
class last-stone-weight-ii {
    // tc -> N*S, sc-> N*S
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        
        for(int stone : stones) sum += stone;
        
        return getMinStoneWeight(stones, 0, 0, 0, 
                                 new Integer[stones.length][sum+1]);
    }
    
    private int getMinStoneWeight(int[] stones, int index, int sum1, int sum2,
                                 Integer[][] cache){           
        if(index==stones.length)
            return Math.abs(sum1-sum2);
        
        if(cache[index][sum1]!=null)
            return cache[index][sum1];
        
        int diff1 = getMinStoneWeight(stones, index+1, 
                                      sum1+stones[index], sum2, cache);
        
        int diff2 = getMinStoneWeight(stones, index+1, 
                                      sum1, sum2+stones[index], cache);
        
        cache[index][sum1] = Math.min(diff1, diff2);
        
        return cache[index][sum1];
    }
}
