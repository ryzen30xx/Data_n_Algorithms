import java.util.List;

public class SortUtil {
    public static void sortBooksByTitle(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).getTitle().compareToIgnoreCase(books.get(j + 1).getTitle()) > 0) {
                    // Hoán đổi
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }
}
