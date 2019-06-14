
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckInOutRecord {

    private Long userId;
    private DataModel model;
    private HashMap<Long, Book> bookHashMap;

    public CheckInOutRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CheckInOutRecord(Long userId, HashMap<Long, Book> bookHashMap, DataModel model) throws TasteException {
        this.userId = userId;
        this.model = model;
        this.bookHashMap = bookHashMap;

        System.out.println("책 제목\t작가\t출판사\t장르");


        for(Long bookId : model.getItemIDsFromUser(userId) ) {
            System.out.println(bookHashMap.get(bookId).getBookTitle() + "\t" + bookHashMap.get(bookId).getBookAuthor() + "\t" +
                    bookHashMap.get(bookId).getBookPublisher() + "\t" +bookHashMap.get(bookId).getBookCategory());
        }

        System.out.println("======================\n\n");

        System.out.println("메뉴로 돌아가려면 아무키나 누르세요.");

        try {

            System.in.read();

            System.out.println("\n\n======================");

        } catch(Exception e) {

            e.printStackTrace();

        }
    }

}