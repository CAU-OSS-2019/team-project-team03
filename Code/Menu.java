import java.util.Scanner;

public class Menu {

    private int menuInput = 0;

    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Menu(String InputID) {

        System.out.println(InputID + " �� ȯ���մϴ� !\n\n");
        String tempInput = null;

        do {


            System.out.println("�޴� ��ȣ�� �Է��� �ּ���.\n");

            System.out.println("=========================");

            System.out.println("1. ����� ���� �м� ���\n");
            System.out.println("2. ����� ���� �̷�/�ݳ� ���\n");
            System.out.println("3. ��õ �ý��� �̿�\n");
            System.out.println("4. ���ø����̼� ����\n");

            System.out.print(">> ");

            @SuppressWarnings("resource")
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