import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Name: 3Sum
// Level: Medium
// Link: https://leetcode.com/problems/3sum/

public class ThreeSum {
    private static class Solution {
        public static List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            List<List<Integer>> output = new LinkedList();

            for (int i = 0; i < nums.length - 2; i++) {
                if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                    System.out.println(nums[i]);
                    int low = i + 1;
                    int high = nums.length - 1;
                    int sum = 0 - nums[i];
                    while (low < high) {
                        System.out.println("Low " + low);
                        System.out.println("High " + high);
                        System.out.println("Nums low " + nums[low]);
                        System.out.println("Nums high " + nums[high]);

                        if (nums[low] + nums[high] == sum) {
                            output.add(Arrays.asList(nums[i], nums[low], nums[high]));
                            while (low < high && nums[low] == nums[low + 1])
                                low++;
                            while (low < high && nums[high] == nums[high - 1])
                                high--;
                            low++;
                            high--;
                        } else if (nums[low] + nums[high] > sum) {
                            high--;
                        } else {
                            low++;
                        }
                    }
                }
            }
            System.out.println(output);
            return output;
        }

    }

    public static void main(String[] args) {
        Solution.threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
    }
}
