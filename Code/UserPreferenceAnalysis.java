import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferenceAnalysis {
    private Long userId;
    private DataModel model;
    private HashMap<Long, Book> bookHashMap;
    private HashMap<String, Integer> genreCounts;
    private double standDev; // ǥ������

    public UserPreferenceAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }


    public UserPreferenceAnalysis(Long userId, HashMap<Long, Book> bookHashMap, DataModel model) throws TasteException {
        this.userId = userId;
        this.bookHashMap = bookHashMap;
        this.model = model;
        this.genreCounts = new HashMap<String, Integer>();
        this.analysis();
    }

    private void analysis() throws TasteException {

        for(Long bookId : model.getItemIDsFromUser(userId) ) {
            if (genreCounts.containsKey(bookHashMap.get(bookId).getBookCategory())) {
                genreCounts.put(bookHashMap.get(bookId).getBookCategory(), genreCounts.get(bookHashMap.get(bookId).getBookCategory()) + 1);

            } else {
                genreCounts.put(bookHashMap.get(bookId).getBookCategory(), 1);
            }
        }

        int sum = model.getItemIDsFromUser(userId).size();          //��
        int avg = sum / 7;                          //���
        double[] sqrtDev = new double[7];           //����^2
        double sumOfDev = 0.0;                      //����^2�� ��
        double var;                                 //�л�

        int i= 0;
        for(Map.Entry<String, Integer> entry : genreCounts.entrySet()){
            sqrtDev[i]=Math.pow(entry.getValue()-avg, 2);
            sumOfDev+=sqrtDev[i];
            i++;
        }
        var = sumOfDev / 7;

        standDev = Math.sqrt(var);
    }


    public void printUserData() throws TasteException {
        System.out.println("\n==========================");
        System.out.println("* "+userId+" ���� �� "+model.getItemIDsFromUser(userId).size()+" ���� �����̽��ϴ�.");
        System.out.println("==========================\n");


        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue() + " �� \t("
                    + String.format("%.2f", (entry.getValue() / (double)model.getItemIDsFromUser(userId).size()) * 100.00) + " %)");

        }
        System.out.println("\n==========================");
        System.out.println("ǥ������: " + standDev);

        System.out.println("==========================\n\n");

        System.out.println("�޴��� ���ư����� �ƹ�Ű�� ��������.");

        try {

            System.in.read();

            System.out.println("\n\n======================");

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    public double getStandDev() {
        return this.standDev;
    }
}