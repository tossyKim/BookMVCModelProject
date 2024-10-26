package bookproject.bookservice;
import bookproject.bookdto.BookDTO;
import bookproject.bookview.bookView;
import java.util.Scanner;
import java.util.ArrayList;

public class BookService {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> publishers = new ArrayList<>();
    ArrayList<Integer> prices = new ArrayList<>();
    ArrayList<BookDTO> books = new ArrayList<>();
    ArrayList<Integer> isbns = new ArrayList<>();

    bookView bv = new bookView();
    Scanner sc = new Scanner(System.in);

    String[] titleL = {
            "변신", "오뒷세이아", "노인과 바다", "죄와 벌", "오만과 편견",
            "신곡", "동물농장", "코스모스", "스타시커", "천사의 부름"
    };
    String[] authorL = {
            "카프카", "호머", "헤밍웨이", "옙스키", "오스틴",
            "단테", "조지 오웰", "칼 세이건", "팀 보울러", "기욤 뮈소"
    };
    String[] publisherL = {
            "문예", "호메로스", "민음사1", "민음사2", "더스토리",
            "민음사3", "올리버", "사이언스북스", "다산책방", "밝은세상"
    };
    int[] priceL = {9000, 29700, 7200, 9900, 17820, 26100, 9000, 17910, 7200, 12150};


//  book 10권을 생성
    public void getBooks(){
        for(int i = 0; i < titleL.length; i++){
            BookDTO book = new BookDTO(i, titleL[i], authorL[i], publisherL[i], priceL[i]);
            titles.add(titleL[i]);
            authors.add(authorL[i]);
            publishers.add(publisherL[i]);
            prices.add(priceL[i]);
            isbns.add(i);

            books.add(book);
        }
    }
//  book 정보 모두 출력하기
    public void lookBooks(){
        bv.printvar();
        System.out.println("모든 책 정보를 출력합니다");
        bv.printvar();
        for(int i = 0; i < books.size(); i++) {
            BookDTO book = books.get(i);
            bv.printBook(book);//
        }
    }
//  새로운 도서 추가하기
    public void addBooks(){
        System.out.print("추가할 도서의 제목을 입력하시오. : ");
        String ntitle = sc.nextLine();
        titles.add(ntitle);
        System.out.print("추가할 도서의 작가를 입력하시오. : ");
        String nauthor = sc.nextLine();
        authors.add(nauthor);
        System.out.print("추가할 도서의 출판사를 입력하시오. : ");
        String npublisher = sc.nextLine();
        publishers.add(npublisher);
        System.out.print("추가할 도서의 가격을 입력하시오. : ");
        int nprice = sc.nextInt();
        prices.add(nprice);

        BookDTO lastbook = books.get(books.size()-1);
        int newIsbn = lastbook.getIsbn()+1;

        System.out.printf("%d",newIsbn);
        BookDTO book = new BookDTO(newIsbn,ntitle,nauthor,npublisher,nprice);
        isbns.add(newIsbn);
        books.add(book);
    }
//  선택한 책 정보 조회하기
    public void chbook(){
        System.out.print("조회할 책 번호를 선택하시오 : ");
        int ch = sc.nextInt();//입력받는 것은 isbn
        sc.nextLine();

        if(ch<0||ch>=books.size()){
            bv.printError();
            return;
        }

        BookDTO bookc = books.get(ch);

        if(ch!=bookc.getIsbn()||!isbns.contains(ch)||ch<0||ch>books.size()-1){
            bv.printError();
            return;
        }

        BookDTO book = books.get(ch);
        int Isbna = book.getIsbn();

        for (int i=0 ; i< books.size();i++){
            BookDTO booka = books.get(i);
            int isbnb = booka.getIsbn();
            if(Isbna == isbnb){
                bv.printvar();
                bv.printBook(booka);
                break;
            }
        }
    }
//  선택한 책의 정보 수정하기
    public void setbooks(){

        System.out.print("수정할 책 번호를 선택하시오 : ");
        int ch1 = sc.nextInt();
        sc.nextLine();
        if(ch1<0 || ch1>books.size()){
            bv.printError();
            return;
        }
        BookDTO book = books.get(ch1);
        System.out.println("제목 : 1, 작가 : 2, 출판사 : 3, 가격 : 4, 취소 : 5");
        System.out.print("수정할 항목을 선택하시오 : ");
        int ch2 = sc.nextInt();
        sc.nextLine();

        switch(ch2){
            case 1:
                System.out.print("수정할 제목 명을 입력 : ");
                String mtitle = sc.nextLine();
                titles.set(ch1,mtitle);
                book.setTitle(mtitle);
                break;

            case 2:
                System.out.print("수정할 작가 명을 입력 : ");
                String mauthor = sc.nextLine();
                authors.set(ch1,mauthor);
                book.setAuthor(mauthor);
                break;

            case 3:
                System.out.print("수정할 출판사 명을 입력 : ");
                String mpublisher = sc.nextLine();
                publishers.set(ch1,mpublisher);
                book.setPublisher(mpublisher);
                break;

            case 4:
                System.out.print("수정할 가격 명을 입력 : ");
                int mprice = sc.nextInt();
                prices.set(ch1,mprice);
                book.setPrice(mprice);
                break;

            case 5:
                bv.printvar();
                System.out.print("나가기");
                bv.printvar();
                break;

            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
//    선택한 책을 삭제하기
    public void delBook(){
        System.out.print("삭제할 책 번호를 선택하시오 : ");
        int ch1 = sc.nextInt();
        sc.nextLine();

        if(!isbns.contains(ch1)){
            bv.printError();
        }

        for(int i=0; i<books.size();i++){//Isbn과 ch가 같은 값을 삭제

            BookDTO book = books.get(i);

            if(book.getIsbn()==ch1){
                titles.remove(i);
                authors.remove(i);
                publishers.remove(i);
                prices.remove(i);
                books.remove(i);
                isbns.remove(i);
                bv.printvar();
                System.out.println("책이 삭제되었습니다.");
                break;
            }
        }
    }

//  메소드 통합하기
    int status=0;
    public void totalBook(){

        getBooks();// 책 생성
        while(status == 0){
            bv.printvar();
            System.out.println("1. 전체 도서정보 출력, 2. 선택도서 조회, 3. 도서정보 추가, 4. 도서정보 수정, 5. 선택도서 삭제, 6. Array확인하기 7. 프로그램 종료");
            bv.printvar();
            System.out.print("수행할 작업을 선택해주세요 : ");
            int ch = sc.nextInt();

            sc.nextLine();
            switch(ch){
                case 1://도서정보 출력
                    lookBooks(); break;
                case 2://선택도서 조회
                    chbook(); break;
                case 3://도서정보추가
                    addBooks(); break;
                case 4://도서정보수정
                    setbooks(); break;
                case 5://도서 삭제
                    delBook(); break;
                case 6: //ArrayList출력해서 상태 확인하기
                    printArray(); break;
                case 7://끝내기
                    status = 1;
                    System.out.println("프로그램을 종료합니다.");break;
                default :
                    bv.printError();
            }
        }
    }
    public void printArray(){
        System.out.println(isbns);
        System.out.println(titles);
        System.out.println(authors);
        System.out.println(publishers);
        System.out.println(prices);
    }
}
