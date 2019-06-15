import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


// input ���ϸ� : CheckingOutData.csv
// output ���ϸ�: CheckingOutDataWithZero.csv (70����)
// ���� ��ο� ��ġ�Ұ�!

public class CreateZero {
    
    private final static String path = CreateZero.class.getResource("").getPath(); // ������ �о����
    private final static String dataFileName = "CheckingOutData.csv"; // ���������� �̸�
    private final static String dataEncoding = "euc-kr"; // ������ ���� ���ڵ�
    
    public CreateZero(){
        
        super();
        
        List<List<String>> records = new ArrayList<>();
        

        records = readCSV(dataFileName, dataEncoding);

        System.out.println("phase1> ���� ������ �б�");

        System.out.println("���� �д���..");
        
        

           int i = 1;
           boolean[] check = new boolean[701];
           for(int j = 0; j < 701; j++){
                check[j] = false;
            }
            
            List<String> write = new ArrayList<>();
            for (List<String> list : records) {

                System.out.println(list.toString());

                int userId = Integer.parseInt(list.get(0));
                int bookId = Integer.parseInt(list.get(1));
                int isRead = Integer.parseInt(list.get(2));
                
                
                if(userId == i){
                    write.add(userId+","+bookId+",1");
                    //System.out.println(userId+","+bookId+",1");
                    check[bookId] = true;
                }else{
                    for(int j = 1; j <= 700; j++){
                        if(check[j] == false){
                            String s = i + "," + j + ",0";
                            write.add(s);
                            //System.out.println(s);
                        }
                        
                    }
                    i++;
                    
                    check = new boolean[701];
                    for(int j = 0; j < 701; j++){
                        check[j] = false;
                    }
                    write.add(userId+","+bookId+",1");
                    //System.out.println(userId+","+bookId+",1");
                    check[bookId] = true;
                }
            }
            
            for(int j = 1; j <= 700; j++){
                if(check[j] == false){
                    String s = i + "," + j + ",0";
                    write.add(s);
                    System.out.println(s);
                }
                
            }
             
            createCSV(write);
            System.out.println("��������");
         

               

    }
            
        
        

       

        
    

    // data.csv �� �о�� List<List<String>>�� return�մϴ�.
    public List<List<String>> readCSV(String path, String encoding) {

        List<List<String>> records = new ArrayList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

            String line;

            while ((line = br.readLine()) != null) {

                // String[] values = line.split(",");
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // ���뿡 �޸��� ����� ���Խ����� ó��

                records.add(Arrays.asList(values));

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;

    }
    

   
    
    
    public void createCSV(List<String> wt) { //�⺻ filePath : C:\\, file�̸� ; resultData.csv
        
        final char UTF_8_WITHOUT_BOM = '\uFEFF';
        
        FileWriter fw = null;
        try {
            fw = new FileWriter("CheckingOutDataWithZero.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        
        
        for(int i = 0; i < wt.size(); i++){
            try {
                bw.write(wt.get(i));
                bw.newLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            System.out.println(wt.get(i));
        }
        try {
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
                
        
    }
    
    public static void main(String[] args) {
        CreateZero cz = new CreateZero();
    }
    
    
}

