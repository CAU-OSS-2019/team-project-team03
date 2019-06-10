import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferenceAnalysis {
    private String userId;
    private DataModel model;
    private HashMap<Long, Book> bookHashMap;
    private HashMap<String, Integer> genreCounts;

    public UserPreferenceAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserPreferenceAnalysis(String userId, HashMap<Long, Book> bookHashMap, DataModel model) throws TasteException {
        this.userId = userId;
        this.bookHashMap = bookHashMap;
        this.model = model;
        genreCounts = new HashMap<String, Integer>();


        // String path = UserPreferenceAnalysis.class.getResource("").getPath();

        /*
        List<List<String>> checkoutList = new ReadFile("resultData_ver4.csv", "euc-kr","checkout").getChechOutList();

        for(int i=0; i<checkoutList.size(); i++) {

            if(checkoutList.get(i).get(0).equals(userId)) {

                int bookId = Integer.parseInt(checkoutList.get(i).get(1));

                userCheckOutList.add(bookId);

            }
        }*/

        this.buildGenreCounts(); // Genre별 카운트
        this.print(); // 출력

        System.out.println("표준편차: " + analysis());

        System.out.println("======================\n\n");

        System.out.println("메뉴로 돌아가려면 아무키나 누르세요.");

        try {

            System.in.read();

            System.out.println("\n\n======================");

        } catch(Exception e) {

            e.printStackTrace();

        }
    }
/*
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
    }*/

    private void buildGenreCounts() throws TasteException {
        for(Long bookId : model.getItemIDsFromUser(Integer.parseInt(userId)) ) {
            if (genreCounts.containsKey(bookHashMap.get(bookId).getBookCategory())) {
                genreCounts.put(bookHashMap.get(bookId).getBookCategory(), genreCounts.get(bookHashMap.get(bookId).getBookCategory()) + 1);

            } else {
                genreCounts.put(bookHashMap.get(bookId).getBookCategory(), 1);
            }
        }
    }

    private double analysis() throws TasteException {

        int sum = model.getItemIDsFromUser(Integer.parseInt(userId)).size();          //합
        int avg = sum / 7;                          //평균
        double[] sqrtDev = new double[7];           //편차^2
        double sumOfDev = 0.0;                      //편차^2의 합
        double var;                                 //분산
        double standVar;                            //표준편차

        int i= 0;
        for(Map.Entry<String, Integer> entry : genreCounts.entrySet()){
            sqrtDev[i]=Math.pow(entry.getValue()-avg, 2);
            sumOfDev+=sqrtDev[i];
            i++;
        }
        var = sumOfDev / 7;

        standVar = Math.sqrt(var);

        return standVar;
    }

    private void print() throws TasteException {
        System.out.println("\n==========================");
        System.out.println("* "+userId+" 님은 총 "+model.getItemIDsFromUser(Integer.parseInt(userId)).size()+" 권을 빌리셨습니다.");
        System.out.println("==========================\n");


        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue() + " 권 \t("
                    + String.format("%.2f", (entry.getValue() / (double)model.getItemIDsFromUser(Integer.parseInt(userId)).size()) * 100.00) + " %)");

        }

    }
}