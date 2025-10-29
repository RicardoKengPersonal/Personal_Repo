package MDISC;

import MDISC.US13.InputReader;
import MDISC.US13.Station;

import java.util.List;
import java.util.Scanner;


public final class Printer {
    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}