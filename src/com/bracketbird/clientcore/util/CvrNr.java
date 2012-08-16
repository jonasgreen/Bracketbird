package com.bracketbird.clientcore.util;

/**
 *
 */
public class CvrNr {

    private static int[] weights = new int[]{2, 7, 6, 5, 4, 3, 2};

    public static boolean validate(String cvrNr) {
        int[] cvrCiffers = getCvrCiffers(cvrNr);
        int sum = 0;
        int index = 0;
        int controlCiffer = 0;
        for (int weight : weights) {
            sum += (cvrCiffers[index] * weights[index]);
            index++;
        }
        controlCiffer = sum % 11;
        if(controlCiffer != 0){
            controlCiffer = 11 - controlCiffer;
        }

        return controlCiffer == cvrCiffers[7];
    }

    private static int[] getCvrCiffers(String cvrNr) {
        int[] ciffers = new int[8];
        int i = 0;
        while (i < 8) {
            ciffers[i] = Integer.valueOf(cvrNr.substring(i, i + 1));
            i++;
        }
        return ciffers;
    }
}
