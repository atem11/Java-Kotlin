public class BinarySearchSpan {
    static int arr[];

    // pre: i >= -1 && i <= arr.size arr[i] >= arr[i + 1] && arr[-1] = Inf && arr[size] = -Inf
    // && type arr[i], finder = int
    static int itBinarySearch(int finder) {
        int l = -1, r = arr.length;
        // r - l >= 1
        // arr[r] && arr[l] is real
        // arr[r] < arr[l]
        // arr[r] <= finder
        // r` - l` < r - l
        while (r - l > 1) {
            int mid = (r + l) / 2;
            // l < mid < r
            if (arr[mid] > finder) {
                // arr[r] && arr[l] is real
                // arr[r] < arr[l]
                // arr[r] <= finder
                l = mid;
                // l = mid
                // l` > l && l` < r` = r =>
                //r` - l` < r - l

            } else {
                // arr[r] && arr[l] is real
                // arr[r] < arr[l]
                // arr[r] <= finder
                r = mid;
                // r = mid
                // r` < r && l = l` < r` =>
                //r` - l` < r - l
            }
        }
        return r;
    }
    // post: arr[r] <= finder && arr[i]` = arr[i] && arr[r - 1] > finder
    // &&

    // pre: l > -1 && r <= arr.size && l <= r && arr[i] >= arr[i + 1] ]
    // && arr[-1] = Inf && arr[size] = -Inf
    // && type arr[i] = int
    // && type l, r, finder = int
    static int recBinarySearch(int l, int r, int finder) {
        if (r - l > 1) {
            int mid = (r + l) / 2;
            // l < mid < r
            if (arr[mid] >= finder) {
                // arr[r] && arr[l] is real
                // arr[r] < arr[l]
                // arr[r] <= finder
                return recBinarySearch(mid, r, finder);
            } else {
                // arr[r] && arr[l] is real
                // arr[r] < arr[l]
                // arr[r] <= finder
                return recBinarySearch(l, mid, finder);
            }
        } else {
            // arr[l] >= finder && arr[l + 1] < finder
            return l;
        }
    }
    // post: arr[l] >= finder && arr[i]` = arr[i] && arr[l + 1] < finder
    // && (l` > l || r` < r)

    public static void main(String[] args) {
        int finder = Integer.parseInt(args[0]);
        arr = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            arr[i - 1] = Integer.parseInt(args[i]);
        }
        int left, right;
        left = itBinarySearch(finder);
        // l >= -1 && r <= arr.size
        right = recBinarySearch(-1, arr.length, finder);
        if (right >= left) {
            System.out.println(left + " " + (right - left + 1));
        } else {
            System.out.println(left + " " + 0);
        }
        //System.out.println(recBinarySearch(-1, arr.length, finder));
    }
}
