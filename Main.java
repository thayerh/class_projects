/*
Takes in an initial input stating the size of the list of sightings, and then takes
in data for date, time, shape, duration, and location. Then the program sorts that data.
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        UFO[] ufoCollection = new UFO[n];
        for (int i = 0; i < n; i++) {
            ufoCollection[i] = new UFO(input.next(), input.next(),
                    input.next(), input.nextInt(), input.nextDouble(), input.nextDouble());
        }
        printLongAndShort(findLongest(ufoCollection), findShortest(ufoCollection), ufoCollection);
        printAvgs(group(ufoCollection));
    }

    public static int findLongest(UFO[] ufos) {
        int longestDur = ufos[0].duration;
        int longestIndex = 0;
        for (int i = 1; i < ufos.length; i++) {
            if (ufos[i].duration > longestDur) {
                longestDur = ufos[i].duration;
                longestIndex = i;
            }
        }
        return longestIndex;
    }

    public static int findShortest(UFO[] ufos) {
        int shortestDur = ufos[0].duration;
        int shortestIndex = 0;
        for (int i = 1; i < ufos.length; i++) {
            if (ufos[i].duration < shortestDur) {
                shortestDur = ufos[i].duration;
                shortestIndex = i;
            }
        }
        return shortestIndex;
    }

    public static void printLongAndShort(int longIndex, int shortIndex, UFO[] ufos) {
        System.out.println("Longest sighting: ");
        System.out.println("When: " + ufos[longIndex].date + " " + ufos[longIndex].time);
        System.out.println("Shape: " + ufos[longIndex].shape);
        System.out.println("Where: (" + ufos[longIndex].latitude + ", " + ufos[longIndex].longitude + ")");
        System.out.println();
        System.out.println("Shortest sighting: ");
        System.out.println("When: " + ufos[shortIndex].date + " " + ufos[shortIndex].time);
        System.out.println("Shape: " + ufos[shortIndex].shape);
        System.out.println("Where: (" + ufos[shortIndex].latitude + ", " + ufos[shortIndex].longitude + ")");
        System.out.println();
    }

    public static ArrayList<ArrayList<UFO>> group(UFO[] ufos) {
        ArrayList<ArrayList<UFO>> uniqueShapes = new ArrayList<>();
        for (UFO ufo : ufos) {
            int index = shapeExists(ufo, uniqueShapes);
            if (index != -1) {
                uniqueShapes.get(index).add(ufo);
            } else {
                uniqueShapes.add(new ArrayList<>());
                uniqueShapes.get(uniqueShapes.size() - 1).add(ufo);
            }
        }
        return uniqueShapes;
    }

    public static int shapeExists(UFO ufo, ArrayList<ArrayList<UFO>> collection) {
        for (int n = 0; n < collection.size(); n++) {
            if (ufo.shape.equals(collection.get(n).get(0).shape)) {
                return n;
            }
        }
        return -1;
    }

    public static void printAvgs(ArrayList<ArrayList<UFO>> ufosGrouped) {
        for (ArrayList<UFO> ufos : ufosGrouped) {
            System.out.println("Averages for " + ufos.get(0).shape + ":");
            System.out.println("Duration: " + avgDuration(ufos));
            System.out.println("Location: " + avgCoordinates(ufos));
            System.out.println();
        }
    }

    public static double avgDuration(ArrayList<UFO> ufos) {
        double count = 0.0;
        for (UFO ufo : ufos) {
            count += ufo.duration;
        }
        return (Math.round((count / ufos.size()) * 100.0) / 100.0);
    }

    public static String avgCoordinates(ArrayList<UFO> ufos) {
        double lonCount = 0.0;
        double latCount = 0.0;
        for (UFO ufo : ufos) {
            lonCount += ufo.longitude;
            latCount += ufo.latitude;
        }
        return ("(" + Math.round((latCount / ufos.size()) * 100.0) / 100.0 + ", " + Math.round((lonCount / ufos.size()) * 100.0) / 100.0 + ")");
    }
}

