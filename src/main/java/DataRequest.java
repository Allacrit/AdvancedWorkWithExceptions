import java.io.FileWriter;
import java.util.Scanner;

public class DataRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String yearOfBirth;
    private long numberPhone;
    private char gender;
    private final Scanner scan = new Scanner(System.in);

    private void EnterFirstName() {
        do {
            System.out.print("Введите имя: ");
            firstName = scan.nextLine();
        } while (correctInput(firstName));
    }

    private void EnterLastName() {
        do {
            System.out.print("Введите фамилию: ");
            lastName = scan.nextLine();
        } while (correctInput(lastName));
    }

    private void EnterMiddleName() {
        do {
            System.out.print("Введите отчество: ");
            middleName = scan.nextLine();
        } while (correctInput(middleName));
    }

    private void EnterYearOfBirth() {
        do {
            System.out.print("Введите год рождения: ");
            yearOfBirth = scan.nextLine();
        } while (correctInput(yearOfBirth));
    }

    private void EnterNumberPhone() {
        String number;
        do {
            do {
                System.out.print("Введите номер телефона: ");
                number = scan.nextLine();
            } while (correctInput(number));
        } while (correctNumbericData(number));
    }

    private void EnterGender() {
        String tempGen;
        do {
            System.out.print("Введите пол (М или Ж):");
            tempGen = scan.nextLine();
        } while (checkGenderData(tempGen));
    }

    private boolean checkGenderData(String genderData) {
        String female = "ж";
        String male = "м";
        if (correctInput(genderData)) {
            return true;
        } else if (genderData.length() > 1) {
            System.out.println("Вы ввели слишком много символов \n" +
                    "попробуйте снова!");
            return true;
        } else if (genderData.toLowerCase().equals(female) || genderData.toLowerCase().equals(male)) {
            gender = genderData.charAt(0);
            return false;
        } else {
            System.out.println("Вы ввели не тот символ! Вводите кириллицей!!");
            return true;
        }
    }

    private boolean correctInput(String inputData) {
        if (inputData.equals("")) {
            System.out.println("Вводимое значение не может быть пустым,\n" +
                    "Попробуйте снова!");
            return true;
        } else {
            return false;
        }
    }

    private boolean correctNumbericData(String inputData) {
        try {
            numberPhone = Long.parseLong(inputData);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Не верный формат, попробуйте снова!");
            return true;
        }
        return false;
    }

    private void OutPutData() {
        System.out.println("\nИмя:  \t- \t" + firstName + "\n" + "Фамилия:  \t- \t" + lastName + "\n" +
                "Отчество:  \t- \t" + middleName + "\n" + "Год рождения:  \t- \t" + yearOfBirth + "\n" +
                "Номер телефона:  \t- \t" + numberPhone + "\n" + "Пол:    \t- \t" + gender);
    }

    private void DataEnter() {
        EnterFirstName();
        EnterLastName();
        EnterMiddleName();
        EnterYearOfBirth();
        EnterNumberPhone();
        EnterGender();
        OutPutData();
    }

    public void SaveDataEnter() {

        DataEnter();
        String file = "src/main/java/Data/" + lastName + ".txt";
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write("<" + firstName + "> " + "<" + lastName + "> " + "<" + middleName + "> " + "<" +
                    yearOfBirth + "> " + "<" + numberPhone + "> " + "<" + gender + ">\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}