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


// input 파일명 : CheckingOutData.csv
// output 파일명: CheckingOutDataWithZero.csv (70만행)
// 같은 경로에 위치할것!

public class CreateZero {
    
    private final static String path = CreateZero.class.getResource("").getPath(); // 절대경로 읽어오기
    private final static String dataFileName = "CheckingOutData.csv"; // 데이터파일 이름
    private final static String dataEncoding = "euc-kr"; // 데이터 파일 인코딩
    
    public CreateZero(){
        
        super();
        
        List<List<String>> records = new ArrayList<>();
        

        records = readCSV(dataFileName, dataEncoding);

        System.out.println("phase1> 원본 데이터 읽기");

        System.out.println("파일 읽는중..");
        
        

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
            System.out.println("생성성공");
         

               

    }
            
        
        

       

        
    

    // data.csv 를 읽어와 List<List<String>>을 return합니다.
    public List<List<String>> readCSV(String path, String encoding) {

        List<List<String>> records = new ArrayList<>();

        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

            String line;

            while ((line = br.readLine()) != null) {

                // String[] values = line.split(",");
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // 내용에 콤마가 들어간경우 정규식으로 처리

                records.add(Arrays.asList(values));

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;

    }
    

   
    
    
    public void createCSV(List<String> wt) { //기본 filePath : C:\\, file이름 ; resultData.csv
        
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

