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

        System.out.println("로그인을 해주세요\n\n");


        while(!userPW.equals(inputPW) || !(0 < Integer.parseInt(inputID) && Integer.parseInt(inputID) <= 1000)) {

            System.out.print("아이디 : ");

            inputID = sc.nextLine();

            System.out.print("비밀번호 : ");

            inputPW = sc.nextLine();

            if(userPW.equals(inputPW) && 0 < Integer.parseInt(inputID) && Integer.parseInt(inputID) <= 1000) {

            }
            else {
                //로그인 실패
                System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
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
                    //사용자 성향 분석
                    //new UserPreferenceAnalysis
                    UserPreferenceAnalysis upa = new UserPreferenceAnalysis(Long.parseLong(inputID), bookHashMap, model);
                    upa.printUserData();
                    break;

                case 2:

                    new CheckInOutRecord(inputID, bookHashMap, model);
                    //사용자 대출 이력, 반납 이력 출력
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
                    //추천 알고리즘
                    //new Algorithm
                    break;

                case 4:
                    //시스템 종료
                    System.out.println("어플리케이션 종료");

                    try {

                        System.exit(0);

                    } catch(SecurityException e) {

                        e.printStackTrace();

                    }
                    break;

                default:

                    System.out.println("* 올바른 번호를 입력해주세요.\n");

                    break;
            }
        }






    }

}
