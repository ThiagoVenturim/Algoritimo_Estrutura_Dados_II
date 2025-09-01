import java.util.Scanner;

class Espelho {
    private int num1;
    private int num2;

    public Espelho() {
        num1 = 0;
        num2 = 0;
    }

    public Espelho(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getNum1() { return num1; }
    public int getNum2() { return num2; }
    public void setNum1(int num1) { this.num1 = num1; }
    public void setNum2(int num2) { this.num2 = num2; }

    public void chamarMetodo() {
        metodoEspelho(num1, num2);
    }

    private void metodoEspelho(int a, int b) {
        StringBuilder str = new StringBuilder();

        for (int i = a; i <= b; i++) {
            str.append(i);
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            str.append(str.charAt(i));
        }

        System.out.println(str.toString());
    }
}

public class EspelhoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Espelho espelho = new Espelho();

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            espelho.setNum1(a);
            espelho.setNum2(b);
            espelho.chamarMetodo();
        }

        scanner.close();
    }
}
