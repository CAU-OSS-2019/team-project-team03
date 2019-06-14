import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferenceAnalysis {
    private List<String> genre;
    private List<Integer> userCheckOutList;
    private HashMap<Integer, Book> bookHashMap;
    private HashMap<String, Integer> genreCounts;

    public UserPreferenceAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserPreferenceAnalysis(String userId, HashMap<Integer, Book> bookHashMap) {

        this.bookHashMap = bookHashMap;
        userCheckOutList = new ArrayList<Integer>();
        genreCounts = new HashMap<String, Integer>();


        // String path = UserPreferenceAnalysis.class.getResource("").getPath();

        List<List<String>> checkoutList = new ReadFile("resultData_ver4.csv", "euc-kr","checkout").getChechOutList();

        for(int i=0; i<checkoutList.size(); i++) {

            if(checkoutList.get(i).get(0).equals(userId)) {

                int bookId = Integer.parseInt(checkoutList.get(i).get(1));

                userCheckOutList.add(bookId);

                if(genreCounts.containsKey(bookHashMap.get(bookId).getBookCategory())) {
                    genreCounts.put(bookHashMap.get(bookId).getBookCategory(), genreCounts.get(bookHashMap.get(bookId).getBookCategory())+1);

                }
                else {
                    genreCounts.put(bookHashMap.get(bookId).getBookCategory(), 1);
                }
            }
        }
        createUserPersonalDataFile(Integer.parseInt(userId)); // file 생성

        System.out.println("\n==========================");
        System.out.println("* "+userId+" 님은 총 "+userCheckOutList.size()+" 권을 빌리셨습니다.");
        System.out.println("==========================\n");


        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue() + " 권 \t("
                    + String.format("%.2f", (entry.getValue() / (double)userCheckOutList.size()) * 100.00) + " %)");

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

    private void createUserPersonalDataFile(int userId) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("PersonalUserCheckOutData_ID_" + userId + ".csv");

            BufferedWriter bw = new BufferedWriter(fw);

            for (int i : userCheckOutList) {

                bw.write(bookHashMap.get(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}