import java.util.List;
import java.util.Arrays;

public class Sociopath {

    public int findTheSociopath(int groupSize, List<int[]> likeList) {
        if (groupSize <= 0) {
            return -1;
        }

        int[] likesReceived = new int[groupSize + 1]; // tracks how many people like each person

        boolean[] likesSomeone = new boolean[groupSize + 1]; // tracks if person likes someone

        for (int[] pair : likeList) {
            int a = pair[0];
            int b = pair[1];

            if (a < 1 || a > groupSize || b < 1 || b > groupSize) { // person 0 or someone outside of group
                return -1;
            }

            // a likes b
            likesSomeone[a] = true;
            likesReceived[b]++;
        }

        for (int person = 1; person <= groupSize; person++) { // finds person who likes no one but liked by everyone else
            if (!likesSomeone[person] && likesReceived[person] == groupSize - 1) {
                return person;
            }
        }

        return -1; // no sociopath found
    }

    public static void main(String[] args) {
        Sociopath sp = new Sociopath();
        
        // test cases
        List<int[]> test1 = Arrays.asList(new int[]{1, 2});
        System.out.println(sp.findTheSociopath(2, test1));

        List<int[]> test2 = Arrays.asList(new int[]{1, 2});
        System.out.println(sp.findTheSociopath(3, test2));

        List<int[]> test3 = Arrays.asList(new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 3}); sp.findTheSociopath(3, test3);
        System.out.println(sp.findTheSociopath(3, test3));
        
        List<int[]> test4 = Arrays.asList(new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 1});
        System.out.println(sp.findTheSociopath(3, test4));

        List<int[]> test5 = Arrays.asList(new int[]{1, 2});
        System.out.println(sp.findTheSociopath(0, test5));

        List<int[]> test6 = Arrays.asList(new int[]{1, 0});
        System.out.println(sp.findTheSociopath(3, test6));
    }
}
