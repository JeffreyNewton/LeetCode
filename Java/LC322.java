package lc322;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC322 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        LC322 LC322 = new LC322();
        System.out.println(LC322.coinChange(coins, amount));
        System.out.println(LC322.coinChangeDPTD(coins, amount));
        System.out.println(LC322.coinChangeDPBU(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        
        return coinChangeHelper(0, coins, amount);
    }

    public int coinChangeHelper(int index, int[] coins, int amount) {
      
        if(amount < 1) return 0;
        
        if(index < coins.length && amount > 0) {
            
            int maxValFromCoin = amount / coins[index];
            int minCost = Integer.MAX_VALUE;
            
            for(int i = 0; i < maxValFromCoin + 1; i++) {
                if(amount >= i * coins[index]) {
                    int temp = coinChangeHelper(index + 1, coins, amount - i *
                            coins[index]);
                    
                    if(temp != -1) minCost = Math.min(minCost, temp + i);
                }
            }
            return(minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }
    
    public int coinChangeDPTD(int[] coins, int amount) {
        
        int[] count = new int[amount];
        if(amount < 1) return 0;
        return coinChangeDPTDHelper(coins, amount, count);
    }

    public int coinChangeDPTDHelper(int[] coins, int remain, int[] count) {
    
        if(remain < 0)  return -1;
        if(remain == 0) return  0;
        if(count[remain - 1] != 0) return count[remain - 1];
        
        int min = Integer.MAX_VALUE;
        
        for(int coin : coins) {
            int temp = coinChangeDPTDHelper(coins, remain - coin, count);
            if(temp >= 0 && temp < min) min = 1 + temp;
        }
        count[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remain - 1];
    }
    
    public int coinChangeDPBU(int[] coins, int amount) {
        
        int max = amount + 1;
        
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, max);
        memo[0] = 0;
        
        for(int i = 1; i < amount + 1; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {
                    memo[i] = Math.min(memo[i], memo[i - coins[j]] + 1);
                }
            }
        }
        return memo[amount] > amount ? -1 : memo[amount];
    }
}