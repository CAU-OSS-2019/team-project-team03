import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    //private final static String userID = "root";
    private final static String userPW = "1234";

    public static void main(String[] args) throws IOException, TasteException {

        System.out.println(
                "                                                                                                .   \r\n" +
                        "                                                                                                .   \r\n" +
                        "                                                                                     ..         .   \r\n" +
                        "                                                                                    ,--.        .   \r\n" +
                        "                                                                                   .-----       .   \r\n" +
                        "                                                                                   ,-----,      .   \r\n" +
                        "                                                                                  .-------      .   \r\n" +
                        "                                                 .                  .,,.          ,------,      .   \r\n" +
                        "                                             .,,,,.                 ----.        .-------       .   \r\n" +
                        "                                            ,------.               -----.        -------        .   \r\n" +
                        "                 .----,                    ,-------.               -----.       ,------.        .   \r\n" +
                        "               ,--------,                 ,--------.              ,----,        ------,         .   \r\n" +
                        "             .,----------,               ,---------.              -----,       :;;;;;;-         .   \r\n" +
                        "           .-------------,              ,----------.             ,-----.      ,------,.             \r\n" +
                        "          .---------------,            .-----------.             -----,      ,~~~~~~~-.             \r\n" +
                        "         .----------------,           .,-----------.            .-----,     .,-------.              \r\n" +
                        "        .-----------------,           ,------.,----.      ..,,. ,-----,     ,--------.              \r\n" +
                        "       .-----------------,           ,------, .----.    .,,---,.,-----.    ,--------,         ,.    \r\n" +
                        "      .------------------.          .------,  ,---,   ,,-----, ,-----,    ,---------,       ,,-.    \r\n" +
                        "     .------------------.           ,------.  ----,,,,-----,   ,-----,   .----------.     .,--,     \r\n" +
                        "     ,---------------,             ,------.  .------------,   .,-----.  .-----------.   .,---,      \r\n" +
                        "    .-----------,,.                ------,   .-----------,    ,-----,  .-----..-----,.,------.      \r\n" +
                        "    ,----------.                  ,------.   ,-----------     ,-----, .-----, .------------,        \r\n" +
                        "   .---------,                   .------.   .----------,.     ,-------------   ,-----------.        \r\n" +
                        "   ,--------,                    ,-----,  .,----------,       ,-----------,.    ----------,         \r\n" +
                        "  .---------.                   .------,.,-----------,        ,----------,.     ,-------,,          \r\n" +
                        "  ,--------.                    ,-------------------,         ,---------,.       ,-----,            \r\n" +
                        "  ,--------              .,.   .------------------,.          .--------.          ,,,..             \r\n" +
                        "  ,-------,             ,-,    ,------------------.            ,------.                             \r\n" +
                        "  ,-------,           ,,-.    .------------------,              ..,,..                              \r\n" +
                        "  ,-------,        .,,--,     ,------------------,                                                  \r\n" +
                        "  ---------      ,-----.     .------------, -----,                                                  \r\n" +
                        "  .--------------------    .------------,   ----,                                                   \r\n" +
                        "   ------------------,    ,----------,.     ,---,                                                   \r\n" +
                        "   ,----------------,   .-----------,       ,---,                                                   \r\n" +
                        "   ,--------------,.   ,----------,.         ,--,                                                   \r\n" +
                        "   .,,----------,,    ------------.         ,---,                                                   \r\n" +
                        "     .-------,..     ,------------          ,---,                                                   \r\n" +
                        "      .......        ,-,,.,------.          ,---.                                                   \r\n" +
                        "                      .   ------,           .---.                                                   \r\n" +
                        "                          -----,            .--,                                                    \r\n" +
                        "                         .-----.             --.                                                    \r\n" +
                        "                         .-----              --.                                                    \r\n" +
                        "                          ----.              ,-.                                                    \r\n" +
                        "                           .,,               ,-                                                     \r\n" +
                        "                                             .,                                                     \r\n" +
                        "                                              .                                                     \r\n" +
                        "                                                                                                    \r\n" +
                        "                                                                                                    \r\n" +
                        "                                                              ,::                                   \r\n" +
                        "               .----------,    ,~:~.   -~,    ..... -~.-~-  ..-:;,.. .~~,   ...........             \r\n" +
                        "               ,:::;;;;:::-   -::~::,  ~;,   -;:::~,~;,~;- .:;;;;;;:,.:;-   ~::::::::;;.            \r\n" +
                        "                  ,:;;;.     ,::. ,;~  ~;,   -;:... ~;,~;-  -::--:;- .:;-   ........,:;.            \r\n" +
                        "                 .~;~~;~.    -;~   ::. ~;~-. -;~    ~;,~;-  -;-  -;~ .:;~,          .:;.            \r\n" +
                        "               ,-::-  -::-,  -;~   ::. ~;:~, -;~    ~;-:;- .~:,  .:: .:;:~.         .:;.            \r\n" +
                        "              .~~-,    ,-~~. ,::. ,;:  ~;-.  -;~    ~;::;-  ~:,  ,;: .:;-.          .:;.            \r\n" +
                        "              .--,......,,-.  -;~-::,  ~;,   -;~    ~;-:;-  ,:~,,:;, .:;-    --, --..:;.            \r\n" +
                        "              ~::::::::::::-   ,~:~,   ~;,   -;~    ~;,~;-   ,~:::,  .:;-    ::-.::,.:;.            \r\n" +
                        "              .....,:;......        ...,~.   -;~    ~;,~;-     ..    .-~,    :;-.::,.:;.            \r\n" +
                        "                  .~:;~.          -:;;:-     -;:...,~;,~;-    ,,,,,,,,,,.    :;-.::,.:;.            \r\n" +
                        "                 -:::::;~        ~;:,,~;~.   -:::::;;;,~;-   .~::::::;;;-    :;-.::,.:;.            \r\n" +
                        "                ,;~.  .:;~      ,;:.  .~;,    .....,~;,~;-     ......,:;-    :;-.::,.:;.            \r\n" +
                        "                -;,    .;~      -;:    -;-          ~;,~;-           .:;-  ..:;~,:;--:;,.           \r\n" +
                        "                -;-    -;~      ,;:.  .~:,          ~;,~;-           .:;- .~~:::::::::::-           \r\n" +
                        "                .::-,,-;:,       ~;~,,~:~.          ~;,~;-           .:;-  ..............           \r\n" +
                        "                 .-~~~~-.        .-~::~-.           ,-.,-,            --.                           \r\n" +
                        "                   ....            ....                                                             ");


        Scanner sc = new Scanner(System.in);

        Menu menu;
        String inputID = null;
        String inputPW = null;

        System.out.println("Recommender in Library");

        System.out.println("�α����� ���ּ���\n\n");


        while(!userPW.equals(inputPW) || !(0 < Integer.parseInt(inputID) && Integer.parseInt(inputID) <= 1000)) {

            System.out.print("���̵� : ");

            inputID = sc.nextLine();

            System.out.print("��й�ȣ : ");

            inputPW = sc.nextLine();

            if(userPW.equals(inputPW) && 0 < Integer.parseInt(inputID) && Integer.parseInt(inputID) <= 1000) {

            }
            else {
                //�α��� ����
                System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
            }

        }

        @SuppressWarnings("unused")
        List<String> userPropensityList;
        HashMap<Long, Book> bookHashMap = new ReadFile("BookList.csv", "euc-kr", "book").getBookHashMap();

        DataModel model = new FileDataModel(new File("resultData_ver5.csv"));

        while(true) {

            menu = new Menu(inputID);

            switch (menu.getMenuInput()) {

                case 1:
                    //����� ���� �м�
                    //new UserPreferenceAnalysis
                    UserPreferenceAnalysis upa = new UserPreferenceAnalysis(Long.parseLong(inputID), bookHashMap, model);
                    upa.printUserData();
                    break;

                case 2:

                    new CheckInOutRecord(inputID, bookHashMap, model);
                    //����� ���� �̷�, �ݳ� �̷� ���
                    //new CheckInOutnRecord
                    break;

                case 3:
                    try {
                        new BookRecommender(model,bookHashMap, Long.parseLong(inputID), 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TasteException e) {
                        e.printStackTrace();
                    }
                    //��õ �˰���
                    //new Algorithm
                    break;

                case 4:
                    //�ý��� ����
                    System.out.println("���ø����̼� ����");

                    try {

                        System.exit(0);

                    } catch(SecurityException e) {

                        e.printStackTrace();

                    }
                    break;

                default:

                    System.out.println("* �ùٸ� ��ȣ�� �Է����ּ���.\n");

                    break;
            }
        }






    }

}
