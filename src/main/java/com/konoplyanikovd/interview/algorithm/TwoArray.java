package com.konoplyanikovd.interview.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoArray {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwoArray.class);




    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15},
                {7, 9, 12, 16}
        };

        int rows = matrix.length;
        int cols = matrix[1].length;

        LOGGER.info(String.valueOf(rows));
        LOGGER.info(String.valueOf(cols));

    }



}
