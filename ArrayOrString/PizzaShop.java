/**
 * Google Online Assessment - Intern 2019
 * 
 * Source: https://leetcode.com/discuss/interview-question/356935/
 * 
 * O(n*m) time O(1) space - n = num of pizzas, m = num of toppings
 */

public class Solution {
    public static int findClosestPriceToBudget(int[] pizzas, int[] toppings, int budget) {

        // Build combinations of 0, 1, 2, .. toppings (0 mean not using any topping)
        List<Integer> toppingCombinations = buildToppingCombinations(toppings);

        int closestPrice = 0;
        int minPriceBudgetDifference = Integer.MAX_VALUE;

        // For each pizza
        for (int pizza : pizzas) {
            // Try with different topping combination
            for (int topping : toppingCombinations) {
                int totalPrice = pizza + topping;
                int priceBudgetDifference = Math.abs(budget - totalPrice);

                if (priceBudgetDifference < minPriceBudgetDifference) {
                    minPriceBudgetDifference = priceBudgetDifference;
                    closestPrice = totalPrice;
                } else if (priceBudgetDifference == minPriceBudgetDifference) {
                    if (totalPrice < closestPrice) {
                        closestPrice = totalPrice;
                    }
                }
            }
        }
        return closestPrice;
    }

    private static List<Integer> buildToppingCombinations(int[] toppings) {
        List<Integer> toppingCombinations = new ArrayList<>();

        // Edge case: no topping used
        toppingCombinations.add(0);

        for (int curToppingIdx = 0; curToppingIdx < toppings.length; curToppingIdx++) {
            // 1 topping at a time
            toppingCombinations.add(toppings[curToppingIdx]);

            // 2 toppings at a time
            for (int nextToppingIdx = curToppingIdx + 1; nextToppingIdx < toppings.length; nextToppingIdx++) {
                toppingCombinations.add(toppings[curToppingIdx] + toppings[nextToppingIdx]);
            }
        }
        return toppingCombinations;
    }

    public static void main(String[] args) {
        int[] pizzas = new int[] { 800, 850, 900 };
        int[] toppings = new int[] { 100, 150 };
        int x = 1000;
        System.out.println("Expected: 1000. Get: ");
        System.out.println(findClosestPriceToBudget(pizzas, toppings, x));
        System.out.println("-------------------------");

        pizzas = new int[] { 850, 900 };
        toppings = new int[] { 200, 250 };
        x = 1000;
        System.out.println("Expected: 1050. Get: ");
        System.out.println(findClosestPriceToBudget(pizzas, toppings, x));
        System.out.println("-------------------------");

        pizzas = new int[] { 1100, 900 };
        toppings = new int[] { 200 };
        x = 1000;
        System.out.println("Expected: 900. Get: ");
        System.out.println(findClosestPriceToBudget(pizzas, toppings, x));
        System.out.println("-------------------------");

        pizzas = new int[] { 800, 800, 800, 800 };
        toppings = new int[] { 100 };
        x = 1000;
        System.out.println("Expected: 900. Get: ");
        System.out.println(findClosestPriceToBudget(pizzas, toppings, x));
        System.out.println("-------------------------");
    }
}
