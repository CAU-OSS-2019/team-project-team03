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
            System.err.println(" !! <JDBC ����> Driver load ����: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.����
        try {
            String[] category = {"�ڿ�����", "IT", "������", "����", "����", "�ι�", "�ڱ���"};

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?serverTimezone = UTC", "root", "rhkdejr12#");
            PreparedStatement pStmt = conn.prepareStatement("select * from book where category = ? AND ranking <= 50");


            for(int i = 701; i<1001; i++) { // ������ ���� 300��
                int nbooks = (int) (Math.random() * (18-10+1)) + 10; // �� ī�װ��� ������ �Ǽ�
                int categoryIndex = 0;

                pStmt.setString(1, category[categoryIndex]);
                ResultSet rset = pStmt.executeQuery();

                ArrayList<Book> bookList = new ArrayList<Book>();
                while (rset.next()) {
                    bookList.add(new Book(Integer.parseInt(rset.getString("id")), rset.getString("title"),
                            rset.getString("author"),rset.getString("publisher"),
                            rset.getString("category"),rset.getString("date"),Integer.parseInt(rset.getString("ranking"))));
                }

                ArrayList<Integer> randomBookIndexList = new ArrayList<Integer>();

                /*  Create Data    */
                for(int j = 0; j <100; j++) {   // ������ 100���� ���� ������ ����

                    if(nbooks == 0){
                        nbooks = (int) (Math.random() * (18-10+1)) + 10; // �� ī�װ��� ������ �Ǽ�
                        if(categoryIndex<6) {
                            categoryIndex++;

                            randomBookIndexList = new ArrayList<Integer>();

                            pStmt.setString(1, category[categoryIndex]);
                            rset = pStmt.executeQuery();

                            bookList = new ArrayList<Book>();
                            while (rset.next()) {
                                bookList.add(new Book(Integer.parseInt(rset.getString("id")), rset.getString("title"),
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
