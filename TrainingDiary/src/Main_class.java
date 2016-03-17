import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

public class Main_class {

    Scanner scanner = new Scanner(System.in);
    public DBController controller;

    public Main_class(DBController controller) {
        setController(controller);
    }

    public void setController(DBController controller) {
        this.controller = controller;
    }

    public DBController getController() {
        return controller;
    }

    public void addTrainingSession() {
        System.out.println("* betyr obligatorisk");
        // Date is deprecated and we will only allow to add a training session from the current day.
        System.out.println("Setter dagens dato som dato for treningsøkten");
        Date date = new Date(1,1,1);
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

    public void addExercisePerformed() {
        System.out.println("* betyr obligatorisk");
        System.out.print("*Navn på øvelsen: ");
        String name = scanner.nextLine();
        System.out.println("*Legg inn antall sett: ");
        int sets = Integer.parseInt(scanner.nextLine());
        System.out.println("*Legg inn antall reps: ");
        int reps = Integer.parseInt(scanner.nextLine());
        System.out.println("*Legg inn vekt (kg): ");
        int load = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn lengde: ");
        int meters = Integer.parseInt(scanner.nextLine());
        System.out.println("Legg inn varighet: ");
        int seconds = Integer.parseInt(scanner.nextLine());
        System.out.println("Legger inn dagens dato");
        Date date = new Date(1,1,1);

        try {
            controller.addExercisePerformed(sets, reps, load, meters, seconds, name, date);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void collectSessions() {
        System.out.println("Hvilken øvelse vil du ha oversikt over? ");
        String exercise = scanner.next();

        try {
            List<Exercise> exercises = controller.getExercises(exercise);
            System.out.println(exercises.get(0));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateExerciseDescription() {
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

    public static void main(String[] args) {
        Main_class m = new Main_class(new DBController());

        while(true) {
            String welcome = "Velkommen til treningsdagboken\n";
            welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
            welcome += "1 - Legg inn ny treningsøkt\n";
            welcome += "2 - Legg til ny utført øvelse\n";
            welcome += "3 - Hent ut tidligere treningsøkter\n";
            welcome += "4 - Oppdater øvelse med ny beskrivelse\n";

            System.out.print(welcome);
            System.out.print("> ");

            int scase = Integer.parseInt(m.scanner.nextLine());
            switch(scase) {
                case 1:
                    m.addTrainingSession();
                    break;
                case 2:
                    m.addExercisePerformed();
                    break;
                case 3:
                    m.collectSessions();
                    break;
                case 4:
                    m.updateExerciseDescription();
                    break;
                default:
                    break;
            }
        }
    }
}
