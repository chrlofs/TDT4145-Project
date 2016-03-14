
public class View {

    public void mainMenu() {

        String welcome = "Velkommen til denne treningsdagboken! \n";
        welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
        welcome += "1 - Legg inn ny treningsøkt\n";
        welcome += "2 - Hent ut tidligere treningsøkter\n";
        welcome += "\nFor å avslutte, trykk hvilken som helst annen tast";

        System.out.print(welcome);
    }

    public static void main(String[] args) {
        View main = new View();
        main.mainMenu();
    }

}
