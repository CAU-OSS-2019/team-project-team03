import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CreateCheckingOutDataset {

    CreateCheckingOutDataset() throws IOException {
        processing();
    }
    private void processing() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("MyList_2.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            String[] category = {"자연과학", "IT", "에세이", "역사", "예술", "인문", "자기계발"};

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?serverTimezone = UTC", "root", "rhkdejr12#");
            PreparedStatement pStmt = conn.prepareStatement("select * from book where category = ? AND ranking <= 50");


            for(int i = 701; i<1001; i++) { // 편향적 유저 300명
                int nbooks = (int) (Math.random() * (18-10+1)) + 10; // 한 카테고리당 빌리는 권수
                int categoryIndex = 0;

                pStmt.setString(1, category[categoryIndex]);
                ResultSet rset = pStmt.executeQuery();

                ArrayList<BookData> bookList = new ArrayList<BookData>();
                while (rset.next()) {
                    bookList.add(new BookData(Integer.parseInt(rset.getString("id")), rset.getString("title"),
                            rset.getString("author"),rset.getString("publisher"),
                            rset.getString("category"),rset.getString("date"),Integer.parseInt(rset.getString("ranking"))));
                }

                ArrayList<Integer> randomBookIndexList = new ArrayList<>();

                /*  Create Data    */
                for(int j = 0; j <100; j++) {   // 유저당 100권의 대출 데이터 생성

                    if(nbooks == 0){
                        nbooks = (int) (Math.random() * (18-10+1)) + 10; // 한 카테고리당 빌리는 권수
                        if(categoryIndex<6) {
                            categoryIndex++;

                            randomBookIndexList = new ArrayList<>();

                            pStmt.setString(1, category[categoryIndex]);
                            rset = pStmt.executeQuery();

                            bookList = new ArrayList<BookData>();
                            while (rset.next()) {
                                bookList.add(new BookData(Integer.parseInt(rset.getString("id")), rset.getString("title"),
                                        rset.getString("author"), rset.getString("publisher"),
                                        rset.getString("category"), rset.getString("date"), Integer.parseInt(rset.getString("ranking"))));
                            }
                        }
                    }

                    /*  remove duplication */

                    int randomBookIndex = (int) (Math.random() * bookList.size());

                    if(randomBookIndexList.contains(randomBookIndex)){
                        j--;
                        continue;
                    } else{
                        randomBookIndexList.add(randomBookIndex);
                        bw.write(i + ","+bookList.get(randomBookIndexList.get(randomBookIndexList.size()-1)).getBookId()+","+1);
                        bw.newLine();
                        System.out.println(i + "\t"+j +"\t"+ bookList.get(randomBookIndexList.get(randomBookIndexList.size()-1)).getBookId()+ "\t"
                                +bookList.get(randomBookIndexList.get(randomBookIndexList.size()-1)).getBookTitle());
                        nbooks--;
                    }
                }
            }
            bw.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException : " + sqle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
