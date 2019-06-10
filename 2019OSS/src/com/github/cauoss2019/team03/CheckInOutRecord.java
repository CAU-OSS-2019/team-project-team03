
import java.util.ArrayList;
import java.util.List;

public class CheckInOutRecord {

    public CheckInOutRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CheckInOutRecord(String inputID) {

        List<String> checkOutRecords = new ArrayList<String>();
        List<String> checkInRecords = new ArrayList<String>();
        List<String> bookTitle = new ArrayList<String>();

       // String path = CheckInOutRecord.class.getResource("").getPath();

        List<List<String>> checkoutList = new ReadFile("BookList_sample_"+inputID+".csv", "euc-kr", "checkout").getChechOutList();

        for(int i=1; i<checkoutList.size(); i++) {

            bookTitle.add(checkoutList.get(i).get(1)); // 책 제목 추가
            checkOutRecords.add(checkoutList.get(i).get(6)); //  대출 기록 추가
            checkInRecords.add(checkoutList.get(i).get(7)); //  반납 기록 추가

        }


        System.out.println("책 제목\t대출 날짜\t반납 날짜");

        for (int i=0; i < checkoutList.size()-1; i++) {

            //System.out.println("책 제목 : " + bookTitle.get(i) + " / " + "대출 날짜 : " + checkOutRecords.get(i) + " / " + " 반납 날짜 : " + checkInRecords.get(i));
            String fixedStr = "";
            fixedStr = bookTitle.get(i) + "\t\t\t\t\t\t\t\t\t\t";

            //System.out.println(fixedStr+" / "+checkOutRecords.get(i) + " / " + checkInRecords.get(i));
            System.out.printf("%1$s / %2$10s / %3$10s", bookTitle.get(i), checkOutRecords.get(i), checkInRecords.get(i)+"\n");
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