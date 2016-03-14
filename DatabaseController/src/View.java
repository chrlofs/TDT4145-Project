import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private DBController controller;

    public View(DBController controller) {
        setController(controller);
    }

    public void mainMenu() {

        String welcome = "Velkommen til denne treningsdagboken! \n";
        welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
        welcome += "1 - Legg inn ny treningsøkt\n";
        welcome += "2 - Hent ut tidligere treningsøkter\n";
        welcome += "3 - Legg til ny øvelse\n";
        welcome += "4 - Hent oppsummering for den siste uken\n";
        welcome += "\nFor å avslutte, trykk hvilken som helst annen tast \n";

        System.out.print(welcome);
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        processInput(scanner.next());
    }

    private void setController(DBController controller) {
        this.controller = controller;
    }

    private DBController getController() {
        return controller;
    }

    private void processInput(String input) {
        switch(input) {
            case "1":
                addExercise();
                System.out.println();
                mainMenu();
                break;
            case "2":
                collectSessions();
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                break;
        }
    }

    private void addExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Navn på øvelsen: ");
        String exercise = scanner.next();
        System.out.print("Beskrivelse på øvelsen: ");
        String description = scanner.next();

        try {
            controller.addExercise(exercise, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void collectSessions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hvilken øvelse vil du ha oversikt over? ");
        String exercise = scanner.next();

        try {
            List<Excercise> exercises = controller.getExercises(exercise);
            System.out.println(exercises.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        View main = new View(new DBController());
        main.mainMenu();
    }

}
