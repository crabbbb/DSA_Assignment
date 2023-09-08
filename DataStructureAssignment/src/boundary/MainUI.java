package boundary;

/**
 * @author LOH XIN JIE
 */
import control.CourseControl;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\n");
            System.out.println("1. Programme Management Subsystem \t\tGUI BASE");
            System.out.println("2. Tutorial Group Management Subsystem \t\tCONSOLE BASE");
            System.out.println("3. Course Management Subsystem \t\t\tCONSOLE BASE");
            System.out.println("0. Exit");

            System.out.print("Please Enter Your Choice ( 0 - 3 ) > ");
            choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    new ProgrammeMainMenu().setVisible(true);
                    break;
                case 2:
                    TutorialGroupUI.main(args);
                    break;
                case 3:
                    CourseControl courseControl = new CourseControl();
                    courseControl.runCourseManagement();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice, Please reenter again !!\n\n");
            }
        } while (choice != 0);
    }
}
