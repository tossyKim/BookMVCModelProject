package bookproject.bookcontroller;
import bookproject.bookservice.BookService;

public class BookController {
    public static void main(String[] args) {

        BookService bs = new BookService();

        bs.totalBook();
    }
}
