package com.aolifu.rocketmq.mybatis;


/**
 * 通配符匹配
 * @date 2022/7/14
 */
class Solution {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String confStr = "cloud-legionzone-zone,game-ms-*,cloud-pc-browser";
        final String[] split = confStr.split(",");
        String target = "cloud-legionzone-zone";
        System.out.println(solution.isMatch(target, split[0]));
    }
    
    
    public boolean isMatch(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }
 
        if (pRight == 0) {
            return sRight == 0;
        }
 
        // 我们用 sIndex 和 pIndex 表示当前遍历到 s 和 p 的位置
        // 此时我们正在 s 中寻找某个 u_i
        // 其在 s 和 p 中的起始位置为 sRecord 和 pRecord
        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;
        
        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                // 如果遇到星号，说明找到了 u_i，开始寻找 u_i+1
                ++pIndex;
                // 记录下起始位置
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                // 如果两个字符可以匹配，就继续寻找 u_i 的下一个字符
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                 // 如果两个字符不匹配，那么需要重新寻找 u_i
		        // 枚举下一个 s 中的起始位置
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                // 如果不匹配并且下一个起始位置不存在，那么匹配失败
                return false;
            }
        }
 
        return allStars(p, pIndex, pRight);
    }
 
    public boolean allStars(String str, int left, int right) {
        // 由于 p 的最后一个字符是星号，那么 s 未匹配完，那么没有关系
		// 但如果 p 没有匹配完，那么 p 剩余的字符必须都是星号
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
 
    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }
}