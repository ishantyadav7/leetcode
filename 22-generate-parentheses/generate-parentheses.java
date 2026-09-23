class Solution {
    public List generateParenthesis(int n) {
        List result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List result, StringBuilder current, int open, int close, int max) {
        // Base case: valid combination of length 2 * n complete
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Add '(' if we haven't reached the max limit
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        // Add ')' only if it matches an unmatched '('
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }
}