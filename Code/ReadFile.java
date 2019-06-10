import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReadFile {

    private List<List<String>> checkoutList;
    private HashMap<Long,Book> bookHashMap;

    public ReadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ReadFile(String path, String encoding, String readFileType) {

        if (readFileType.equals("book")) {
            readBookFile(path, encoding);
        } else if (readFileType.equals("checkout")) {
            readCheckOutFile(path, encoding);
        } else {
            System.out.println("* 올바른 타입를 입력해주세요.\n");
        }

    }

    private void readBookFile(String path, String encoding){
        bookHashMap = new HashMap<Long, Book>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

            String line;

            while((line = br.readLine()) != null) {
                Book book = new Book(line);
                bookHashMap.put(book.getBookId(), book);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

    }

    private void readCheckOutFile(String path, String encoding){
        checkoutList = new ArrayList<List<String>>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

            String line;

            while((line = br.readLine()) != null) {

                String[] values = line.split(",");
                checkoutList.add(Arrays.asList(values));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

    }


    public List<List<String>> getChechOutList() {
        return this.checkoutList;
    }

    public HashMap<Long,Book> getBookHashMap(){return this.bookHashMap; }
    public void setRecords(List<List<String>> checkoutList) {
        this.checkoutList = checkoutList;
    }
}
