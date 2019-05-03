package com.arrays;

import java.util.Arrays;

/**
 * #498 : Digonal traversal
 * https://leetcode.com/problems/diagonal-traverse/
 */
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int R = matrix.length;
        int C = matrix[0].length;
        int[] dr = {-1, 0, 1, 1,-1, 1, 1, 0};
        int[] dc = { 1, 1,-1, 0, 1, 0,-1, 1};
        int[] output = new int[R*C];
        int cnt = 1, r = 0, c = 0, move = 0;
        output[0] = matrix[0][0];
        while(cnt < R*C) {
            int tempR = r + dr[move];
            int tempC = c + dc[move];
            if(isValid(tempR,tempC,R,C)) {
                output[cnt] = matrix[tempR][tempC];
                r = tempR;
                c = tempC;
                cnt++;
            } else {
                move = (move + 1) % dr.length;
            }
        }
        return output;
    }

    private boolean isValid(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) {
        DiagonalTraverse traverse = new DiagonalTraverse();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.toString(traverse.findDiagonalOrder(matrix)));
    }
}
