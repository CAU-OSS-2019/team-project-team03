import java.util.Scanner;

public class Menu {

    private int menuInput = 0;

    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Menu(String InputID) {

        System.out.println(InputID + " 님 환영합니다 !\n\n");
        String tempInput = null;

        do {


            System.out.println("메뉴 번호를 입력해 주세요.\n");

            System.out.println("=========================");

            System.out.println("1. 사용자 성향 분석 목록\n");
            System.out.println("2. 사용자 대출 이력/반납 목록\n");
            System.out.println("3. 추천 시스템 이용\n");
            System.out.println("4. 어플리케이션 종료\n");

            System.out.println(">> ");

            Scanner sc = new Scanner(System.in);

            tempInput = sc.next();

            menuInput = Integer.parseInt(tempInput);



        } while(0 >= menuInput || menuInput > 4);


    }

    public int getMenuInput() {
        return menuInput;
    }

    public void setMenuInput(int menuInput) {
        this.menuInput = menuInput;
    }


}