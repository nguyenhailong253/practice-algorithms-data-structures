/**
 * Unbounded Knapsack problem - classic DP question
 * 
 * Source: https://www.interviewcake.com/question/java/cake-thief
 * 
 * O(N*K) time and O(K) space, with N is the length of the cake array, and K is
 * the capacity of the duffle bag
 */

public class CakeType {

    final int weight;
    final int value;

    public CakeType(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class InfinityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InfinityException() {
        super("Max value is infinity!");
    }
}

public class Solution {
    public long maxDuffleBagValue(CakeType[] cakeTypes, int weightCapacity) {

        // Can use array or hashmap
        // Array to hold the max possible value at every capacity from 0 to
        // weightCapacity - representing by index in array
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

            // max capacity from cake combination for the current capacity level
            long currentMaxMonetaryValue = 0;

            // We need to check any cakes that weigh <= currentCapacity
            for (CakeType cakeType : cakeTypes) {
                if (cakeType.weight <= currentCapacity) {
                    // find maxValue that could be obtained at this capacity

                    // if cake weights 0 and has positive value, meaning we can put infinite number
                    // of this cake
                    if (cakeType.weight == 0 && cakeType.value > 0)
                        throw new InfinityException();

                    // How much space we have left in the bag
                    int remainingCapacityAfterTakingCake = currentCapacity - cakeType.weight;

                    long maxValueUsingCurrentCake = maxValuesAtCapacities[remainingCapacityAfterTakingCake]
                            + cakeType.value;

                    currentMaxMonetaryValue = Math.max(currentMaxMonetaryValue, maxValueUsingCurrentCake);
                }
            }
            // Save the max value for later use
            maxValuesAtCapacities[currentCapacity] = currentMaxMonetaryValue;
        }
        return maxValuesAtCapacities[weightCapacity];
    }
}
