package com.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * #54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix sMatrix = new SpiralMatrix();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(sMatrix.spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if(matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        //define direction for row
        int[] dr = {0, 1, 0, -1};
        //define direction for col
        int[] dc = {1, 0, -1, 0};
        int nextR, nextC, r = 0, c = 0, di = 0;
        for(int i = 0 ; i < R*C ; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            nextR = r + dr[di];
            nextC = c + dc[di];
            //check nextR and nextC are within range and next element in matrix is not already seen
            if(nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && !seen[nextR][nextC]) {
                //if yes, make a move
                r = nextR;
                c = nextC;
            } else {
                //if no, means we need to change direction
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}
