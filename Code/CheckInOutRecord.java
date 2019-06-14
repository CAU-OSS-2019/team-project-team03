
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

        System.out.println("å ����\t�۰�\t���ǻ�\t�帣");


        for(Long bookId : model.getItemIDsFromUser(userId) ) {
            System.out.println(bookHashMap.get(bookId).getBookTitle() + "\t" + bookHashMap.get(bookId).getBookAuthor() + "\t" +
                    bookHashMap.get(bookId).getBookPublisher() + "\t" +bookHashMap.get(bookId).getBookCategory());
        }

        System.out.println("======================\n\n");

        System.out.println("�޴��� ���ư����� �ƹ�Ű�� ��������.");

        try {

            System.in.read();

            System.out.println("\n\n======================");

        } catch(Exception e) {

            e.printStackTrace();

        }
    }

}