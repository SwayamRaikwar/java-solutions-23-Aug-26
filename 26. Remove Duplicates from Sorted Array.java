class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Pointer for the position of the last unique element found
        int k = 1;

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // When a new unique element is encountered
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // Place it at the next unique position
                k++;
            }
        }

        return k;
    }
}
