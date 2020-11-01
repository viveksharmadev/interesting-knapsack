// https://leetcode.com/problems/count-sorted-vowel-strings/
class Solution {
    // tc -> n*vowelLength*2, sc-> n*vowelLength
    public int countVowelStrings(int n) {
        return getVowelStrsCount(n, 0, new Integer[n+1][5]);
    }
    
    private int getVowelStrsCount(int n, int index, Integer[][] cache){
        if(n==0) return 1;
        
        if(index==5) return 0;
        
        if(cache[n][index]!=null) return cache[n][index];
        
        int includeVowel = getVowelStrsCount(n-1, index, cache);
        int skipVowel = getVowelStrsCount(n, index+1, cache);
        
        int sum = includeVowel + skipVowel;
        
        cache[n][index] = sum;
        
        return sum;
    }
}
