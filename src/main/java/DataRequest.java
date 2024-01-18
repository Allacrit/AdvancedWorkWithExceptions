import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DataRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String yearOfBirth;
    private long numberPhone;
    private char gender;
    private final Scanner scan = new Scanner(System.in);

    private void enterFirstLastMiddleName() {
        String inputData;
        do {
            do {
                System.out.print("""
                        Введите Ф.И.О. через пробел. Пример:\s
                        Иванов Иван Иванович
                        """);

                inputData = scan.nextLine().trim();
            } while (correctInput(inputData));
        } while (dataTransferFIO(inputData));
    }

    private void enterYearOfBirth() {
        String tempData;
        do {
            do {
                System.out.print("Введите дату рождения формата dd.mm.yyyy: ");
                tempData = scan.nextLine().trim();
            } while (correctInput(tempData));
        } while (correctDataBirth(tempData));
    }

    private boolean correctDataBirth(String tempData) {
        if (tempData.contains(".")) {
            return creatingAndVerifyDate(creatingBasedOnDelimiters(tempData, "."));
        } else if (tempData.contains(",")) {
            return creatingAndVerifyDate(creatingBasedOnDelimiters(tempData, ","));
        } else if (tempData.contains("/")) {
            return creatingAndVerifyDate(creatingBasedOnDelimiters(tempData, "/"));
        } else if (tempData.contains(" ")) {
            return creatingAndVerifyDate(creatingBasedOnDelimiters(tempData, " "));
        } else {
            System.out.println("Не корректные данные отсутствуют разрешённые разделители . , / 'Пробел'");
            return true;
        }
    }

    private String[] creatingBasedOnDelimiters(String tempData, String s) {
        return tempData.split(s);
    }

    private boolean creatingAndVerifyDate(String[] date) {
        if (date.length == 3) {
            if (date[0].length() <= 2 && date[1].length() <= 2 && date[2].length() <= 4) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
                Calendar calendar = new GregorianCalendar();
                calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
                yearOfBirth = dateFormat.format(calendar.getTime());
                return false;
            } else {
                System.out.println("Не корректное количество символов в поле. Проверьте правильность ввода");
                return true;
            }
        } else {
            System.out.println("Не корректные данные. Проверьте правильность ввода");
            return true;
        }
    }

    private void enterNumberPhone() {
        String number;
        do {
            do {
                System.out.print("Введите номер телефона: ");
                number = scan.nextLine();
            } while (correctInput(number));
        } while (correctNumberData(number));
    }

    private void enterGender() {
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
            System.out.println("Вы ввели слишком много символов \n" + "попробуйте снова!");
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
            System.out.println("Вводимое значение не может быть пустым,\n" + "Попробуйте снова!");
            return true;
        } else {
            return false;
        }
    }

    private boolean dataTransferFIO(String inputData) {
        String[] dataFIO = inputData.split(" ");
        if (dataFIO.length == 3) {
            firstName = dataFIO[1];
            lastName = dataFIO[0];
            middleName = dataFIO[2];
            return false;
        } else if (dataFIO.length < 3) {
            System.out.println("Не правильный ввод.Не хватает полей.");
            return true;
        } else {
            System.out.println("Не правильный ввод. Полей более чем 3.");
            return true;
        }
    }

    private boolean correctNumberData(String inputData) {
        try {
            numberPhone = Long.parseLong(inputData);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Не верный формат, попробуйте снова!");
            return true;
        }
        return false;
    }

    private void outPutData() {
        System.out.printf("""
                Имя:             %s
                Фамилия:         %s
                Отчество:        %s
                Дата рождения:   %s
                Номер телефона:  %d
                Пол:             %s
                """, firstName, lastName, middleName, yearOfBirth, numberPhone, gender);
    }

    private void dataEnter() {
        enterFirstLastMiddleName();
        enterYearOfBirth();
        enterNumberPhone();
        enterGender();
        outPutData();
    }

    public void saveDataEnter() {

        dataEnter();
        String file = "src/main/java/Data/" + lastName + ".txt";
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write("<" + firstName + "> " + "<" + lastName + "> " + "<" + middleName + "> "
                    + "<" + yearOfBirth + "> " + "<" + numberPhone + "> " + "<" + gender + ">\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}