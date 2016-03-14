import java.util.Scanner;

public class View {

    private DBController controller;

    public void mainMenu() {

        String welcome = "Velkommen til denne treningsdagboken! \n";
        welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
        welcome += "1 - Legg inn ny treningsøkt\n";
        welcome += "2 - Hent ut tidligere treningsøkter\n";
        welcome += "3 - Legg til ny øvelse\n";
        welcome += "4 - Hent oppsummering for den siste uken\n";
        welcome += "\nFor å avslutte, trykk hvilken som helst annen tast";

        System.out.print(welcome);
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

                break;
            case "2":
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
        String s = scanner.next();

    }

    public static void main(String[] args) {
        View main = new View();
        main.mainMenu();
    }

}
