package bookproject.bookview;
import bookproject.bookdto.BookDTO;

import java.util.ArrayList;

public class bookView {
    ArrayList<String> titles = new ArrayList<>();

    public void printvar(){
        System.out.println("==================================");
    }

    public void printlongvar(){
        System.out.println("====================================================================");
    }

    public void printError(){
        printlongvar();
        System.out.println("================== 잘못된 선택입니다. 다시 선택해주세요 ==================");
        printlongvar();
    }
    public void printBook(BookDTO book){
        System.out.printf("-번호: %2d\t\t\t-제목: %s\t\t\t-작가: %s\t\t\t-출판사: %s\t\t\t-가격: %d\n",book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getPrice());
    }
}
