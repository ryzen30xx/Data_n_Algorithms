import java.util.List;

public class SearchUtil {
    public static Book findBookByTitle(List<Book> books, String title) {
        // Đảm bảo danh sách đã được sắp xếp theo title
        SortUtil.sortBooksByTitle(books);
        return binarySearch(books, title);
    }

    private static Book binarySearch(List<Book> books, String title) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Book midBook = books.get(mid);
            int cmp = title.compareToIgnoreCase(midBook.getTitle());

            if (cmp == 0) {
                return midBook;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return null;
    }
}
