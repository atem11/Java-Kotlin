public class BinarySearch {
    static long arr[];

    // pre: arr[i] >= arr[i + 1] && arr[-1] = Inf && arr[size] = -Inf
    static int itBinarySearch(long finder) {
        int l = -1, r = arr.length;
        // r - l >= 1
        // arr[r] && arr[l] is real
        // arr[r] <= arr[l]
        // arr[r] <= finder
        // arr[l] > finder
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (arr[mid] > finder) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }
    // post: arr[r] <= finder && arr[i]` = arr[i] && arr[r - 1] > finder

    // pre: l <= r && arr[i] >= arr[i + 1] ] && arr[-1] = Inf && arr[size] = -Inf
    static int recBinarySearch(int l, int r, long finder) {
        if (r - l > 1) {
            int mid = (r + l) / 2;
            if (arr[mid] > finder) {
                return recBinarySearch(mid, r, finder);
            } else {
                return recBinarySearch(l, mid, finder);
            }
        } else {
            return r;
        }
    }
    // post: arr[r] <= finder && arr[i]` = arr[i] && arr[r - 1] > finder

    public static void main(String[] args) {
        long finder = Integer.parseInt(args[0]);
        arr = new long[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            arr[i - 1] = Long.parseLong(args[i]);
        }
        System.out.println(itBinarySearch(finder));
        //System.out.println(itBinarySearchRight(finder));
        //System.out.println(recBinarySearch(-1, arr.length, finder));
    }
}
