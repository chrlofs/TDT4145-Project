import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

public class Main {

    Scanner scanner = new Scanner(System.in);
    private DBController controller;

    public Main(DBController controller) {
        setController(controller);
    }

    private void setController(DBController controller) {
        this.controller = controller;
    }

    private DBController getController() {
        return controller;
    }

    private void addTrainingSession() {
        System.out.println("* betyr obligatorisk");
        // Date is deprecated and we will only allow to add a training session from the current day.
        System.out.println("Setter dagens dato som dato for treningsøkten");
        Date date = new Date();
        System.out.println("Legg inn varighet: ");
        int seconds = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn form: ");
        int shape = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn rating: ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn notat: ");
        String note = scanner.nextLine();
        System.out.println("Legg inn navnet på idretten: ");
        String sportname = scanner.nextLine();
        System.out.println("Legg inn type: ");
        String type = scanner.nextLine();
        System.out.println("Legg inn værtype: ");
        String weather_type = scanner.nextLine();
        System.out.println("Legg inn temperatur: ");
        int temperature = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn luftfuktighet: ");
        double humidity = Double.parseDouble(scanner.nextLine());
        System.out.println("Legg til tilskuere: ");
        int spectators = Integer.parseInt(scanner.nextLine());

        try {
            controller.addTrainingSession(date, seconds, shape, rating, note, sportname, type, weather_type, temperature, humidity, spectators);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addExercise() {
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
        System.out.println("Hvilken øvelse vil du ha oversikt over? ");
        String exercise = scanner.next();

        try {
            List<Exercise> exercises = controller.getExercises(exercise);
            System.out.println(exercises.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateExerciseDescription() {
        System.out.println("Hvilken øvelse vil du endre? ");
        String exercise = scanner.nextLine();
        System.out.println("Ny beskrivelse: ");
        String description = scanner.nextLine();
        try {
            controller.changeDescription(exercise, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main(new DBController());

        while(true) {
            String welcome = "Velkommen til treningsdagboken\n";
            welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
            welcome += "1 - Legg inn ny treningsøkt\n";
            welcome += "2 - Legg til ny øvelse\n";
            welcome += "3 - Hent ut tidligere treningsøkter\n";
            welcome += "4 - Oppdater øvelse med ny beskrivelse\n";

            System.out.print(welcome);
            System.out.print("> ");

            int scase = Integer.parseInt(main.scanner.nextLine());
            switch(scase) {
                case 1:
                    main.addTrainingSession();
                    break;
                case 2:
                    main.addExercise();
                    break;
                case 3:
                    main.collectSessions();
                    break;
                case 4:
                    main.updateExerciseDescription();
                    break;
                default:
                    break;
            }
        }
    }
}
