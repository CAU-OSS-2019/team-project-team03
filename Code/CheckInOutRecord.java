
import org.apache.mahout.cf.taste.model.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckInOutRecord {

    private String userId;
    private DataModel model;
    private HashMap<Long, Book> bookHashMap;

    public CheckInOutRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CheckInOutRecord(String userId, HashMap<Long, Book> bookHashMap, DataModel model) {
        this.userId = userId;
        this.model = model;
        this.bookHashMap = bookHashMap;

        System.out.println("책 제목\t대출 날짜\t반납 날짜");

        /*
        for (int i=0; i < checkoutList.size()-1; i++) {

            //System.out.println("책 제목 : " + bookTitle.get(i) + " / " + "대출 날짜 : " + checkOutRecords.get(i) + " / " + " 반납 날짜 : " + checkInRecords.get(i));
            String fixedStr = "";
            fixedStr = bookTitle.get(i) + "\t\t\t\t\t\t\t\t\t\t";

            //System.out.println(fixedStr+" / "+checkOutRecords.get(i) + " / " + checkInRecords.get(i));
            System.out.printf("%1$s / %2$10s / %3$10s", bookTitle.get(i), checkOutRecords.get(i), checkInRecords.get(i)+"\n");
        }

*/
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