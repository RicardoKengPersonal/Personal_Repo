import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Scanner lerTeclado = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length == 0) {
            interativo();
        } else if (args.length == 11) {
            naoInterativo(args);
        } else if (args.length <= 10) {
            System.out.println("Número de argumentos insuficientes.");
        } else {
            System.out.println("Número de argumentos em excesso.");
        }
    }

    public static void gerarGnuplot(File output) {
        try {
            String scriptTxt = "set title 'Evolução das Curvas Epidemiológicas'\n" +
                    "set terminal 'png'\n" +
                    "set output '" + output.getName().replace(".csv", ".png'") +
                    "\nset xlabel 'Dias'\n" +
                    "set ylabel 'População'\n" +
                    "set datafile separator ';'\n" +
                    "set yrange [0:1]\n" +
                    "plot '" + output.getName() + "' using 1:2 title 'Suscetíveis' with lines, \\\n" +
                    "     '" + output.getName() + "' using 1:3 title 'Infectados' with lines, \\\n" +
                    "     '" + output.getName() + "' using 1:4 title 'Recuperados' with lines";
            String nome = output.getName().replace(".csv", ".plt");

            try (PrintWriter writerTxt = new PrintWriter(new FileWriter(nome))) {
                writerTxt.println(scriptTxt);
            }

            ProcessBuilder processBuilder = new ProcessBuilder("gnuplot", nome);
            Process process = processBuilder.start();

            process.waitFor();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //método não interativo

    private static void naoInterativo(String[] argumentos) throws FileNotFoundException {

        File nomeFicheiroParams = new File(argumentos[1]);

        double[] parametros = lerParametros(nomeFicheiroParams);

        File nomeFicheiroVI = new File(argumentos[3]);

        double[] valoresIniciais = lerValoresIniciais(nomeFicheiroVI);

        double S0 = valoresIniciais[0], I0 = valoresIniciais[1], R0 = valoresIniciais[2];

        double lambda = parametros[1], mu = parametros[2], kapa = parametros[3], beta = parametros[4], b = parametros[5], delta1 = parametros[6], delta2 = parametros[7];

        int metodo = Integer.parseInt(argumentos[5]);

        double h = Double.parseDouble(argumentos[7]);

        int dias = Integer.parseInt(argumentos[9]);

        File output = new File(argumentos[10] + ".csv");

        if (dias < 0 || (h < 0 || h > 1) || (metodo != 1 && metodo != 2) || !testarParametros(parametros) || !testarValoresIniciais(valoresIniciais)) {
            System.out.println("Valores introduzidos nos argumentos inválidos ou nos ficheiros inválidos.");
        } else if (metodo == 1) {
            Euler(b, lambda, mu, kapa, h, beta, delta1, delta2, S0, R0, I0, dias, output);
            gerarGnuplot(output);
        } else {
            RK4(S0, I0, R0, b, h, lambda, mu, kapa, beta, delta1, delta2, dias, output);
            gerarGnuplot(output);
        }


    }



    //método interativo (com leitura de ficheiro)

    public static void interativo() throws FileNotFoundException {

        System.out.println("Indique o nome do ficheiro que contém os parâmetros:");

        File nomeFicheiroParams = new File(lerTeclado.next());

        System.out.println("Indique o nome do ficheiro que contém os valores iniciais:");

        File nomeFicheiroVI = new File(lerTeclado.next());

        double[] parametros = lerParametros(nomeFicheiroParams);

        double[] valoresIniciais = lerValoresIniciais(nomeFicheiroVI);

        double lambda = parametros[1], mu = parametros[2], kapa = parametros[3], beta = parametros[4], b = parametros[5], delta1 = parametros[6], delta2 = parametros[7];

        double S0 = valoresIniciais[0], I0 = valoresIniciais[1], R0 = valoresIniciais[2];

        int metodo = escolherMetodo();

        double h = escolherPassoDeIntegracao();

        int dias = lerDias();

        System.out.println("Selecione o nome para o ficheiro de output:");

        File output = new File(lerTeclado.next() + ".csv"); // CSV = Comma-separated Values

        if (!testarParametros(parametros)||!testarValoresIniciais(valoresIniciais)){
            System.out.println("Valores introduzidos nos ficheiros inválidos");
        } else if (metodo == 1) {
            Euler(b, lambda, mu, kapa, h, beta, delta1, delta2, S0, R0, I0, dias, output);
            gerarGnuplot(output);
        } else {
            RK4(S0, I0, R0, b, h, lambda, mu, kapa, beta, delta1, delta2, dias, output);
            gerarGnuplot(output);
        }


    }

    public static int escolherMetodo() {

        //Seleção de método

        System.out.println("Selecione 1 para utilizar o método de Euler");

        System.out.println("Selecione 2 para utilizar o método de Runge-Kutta de 4ª ordem");

        int metodo = lerTeclado.nextInt();

        while (metodo != 2 && metodo != 1) {
            System.out.println("Valor inválido.");
            System.out.println("Selecione 1 para utilizar o método de Euler");
            System.out.println("Selecione 2 para utilizar o método de Runge-Kutta de 4ª ordem");
            metodo = lerTeclado.nextInt();
        }
        return metodo;
    }

    public static void Euler(double b, double lambda, double mu, double kapa, double h, double beta, double delta1, double delta2, double S0, double R0, double I0,
                             int dias, File output) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(output);

        double S, I, R;

        colunas(pw);

        // Loop para método Euler

        for (int cont = 0; cont <= dias; cont++) {

            printResultados(S0, I0, R0, cont, pw);

            int n = 0;

            //Equações matemáticas de Euler
            //h (passo de integração) determina número de iterações controlado pela variável local (n)

            do {
                S = S0 + ((h) * eqS(lambda, b, S0, I0, mu));
                I = I0 + ((h) * eqI(b, S0, I0, kapa, beta, R0, mu, delta1));
                R = R0 + ((h) * eqR(kapa, I0, beta, R0, mu, delta2));
                S0 = S;
                I0 = I;
                R0 = R;
                n = n + 1;

            } while (n < (1 / h));
        }
        pw.close();
    }

    public static void printResultados(double S0, double I0, double R0, int cont, PrintWriter pw) {
        pw.println((cont) + ";" + S0 + ";" + I0 + ";" + R0 + ";" + (int) Math.round(S0 + I0 + R0));
    }

    public static void colunas(PrintWriter pw) {
        pw.println("Dia;S;I;R;T");
    }

    //Equações diferenciais não lineares

    public static double eqS(double lambda, double b, double S0, double I0, double mu) {
        return (lambda - (b * S0 * I0) - (mu * S0));
    }

    public static double eqI(double b, double S0, double I0, double kapa, double beta, double R0, double mu, double delta1) {
        return ((b * S0 * I0) - (kapa * I0) + (beta * I0 * R0) - ((mu + delta1) * I0));
    }

    public static double eqR(double kapa, double I0, double beta, double R0, double mu, double delta2) {
        return ((kapa * I0) - (beta * I0 * R0) - ((mu + delta2) * R0));
    }


    public static void RK4(double S0, double I0, double R0, double b, double h, double lambda, double mu, double kapa, double beta, double delta1, double delta2,
                           int dias, File output) throws FileNotFoundException {

        double[][] kappas = new double[3][5];  // Matriz para armazenar valores necessários ao método

        PrintWriter pw = new PrintWriter(output);

        colunas(pw);

        // Loop para método Runge-Kutta de ordem 4

        for (int cont = 0; cont <= dias; cont++) {

            printResultados(S0, I0, R0, cont, pw);

            int n = 0;

            do {

                // Equações matemáticas para o método Runge-Kutta de ordem 4

                kappas[0][0] = h * eqS(lambda, b, S0, I0, mu);
                kappas[1][0] = h * eqI(b, S0, I0, kapa, beta, R0, mu, delta1);
                kappas[2][0] = h * eqR(kapa, I0, beta, R0, mu, delta2);

                kappas[0][1] = h * eqS(lambda, b, (S0 + (kappas[0][0] * 0.5)), (I0 + (kappas[1][0] * 0.5)), mu);
                kappas[1][1] = h * eqI(b, (S0 + (kappas[0][0] * 0.5)), (I0 + (kappas[1][0] * 0.5)), kapa, beta, (R0 + (kappas[2][0] * 0.5)), mu, delta1);
                kappas[2][1] = h * eqR(kapa, (I0 + (kappas[1][0] * 0.5)), beta, (R0 + (kappas[2][0] * 0.5)), mu, delta1);

                kappas[0][2] = h * eqS(lambda, b, (S0 + (kappas[0][1] * 0.5)), (I0 + (kappas[1][1] * 0.5)), mu);
                kappas[1][2] = h * eqI(b, (S0 + (kappas[0][1] * 0.5)), (I0 + (kappas[1][1] * 0.5)), kapa, beta, (R0 + (kappas[2][1] * 0.5)), mu, delta1);
                kappas[2][2] = h * eqR(kapa, (I0 + (kappas[1][1] * 0.5)), beta, (R0 + (kappas[2][1] * 0.5)), mu, delta2);

                kappas[0][3] = h * eqS(lambda, b, (S0 + kappas[0][2]), (I0 + kappas[1][2]), mu);
                kappas[1][3] = h * eqI(b, (S0 + kappas[0][2]), (I0 + kappas[1][2]), kapa, beta, (R0 + kappas[2][2]), mu, delta1);
                kappas[2][3] = h * eqR(kapa, (I0 + kappas[1][2]), beta, (R0 + kappas[2][2]), mu, delta2);

                S0 = S0 + (kappas[0][0] + (2 * kappas[0][1]) + (2 * kappas[0][2]) + kappas[0][3]) / 6;
                I0 = I0 + (kappas[1][0] + (2 * kappas[1][1]) + (2 * kappas[1][2]) + kappas[1][3]) / 6;
                R0 = R0 + (kappas[2][0] + (2 * kappas[2][1]) + (2 * kappas[2][2]) + kappas[2][3]) / 6;
                n = n + 1;

            } while (n < (1 / h));
        }
        pw.close();
    }

    public static double[] lerParametros(File nomeFicheiroParams) throws FileNotFoundException {

        String[] valores = lerFicheiros(nomeFicheiroParams);

        double[] parametros = new double[8];

        for (int i = 0; i < parametros.length; i++) {
            parametros[i] = Double.parseDouble(valores[i]);
        }
        return parametros;
    }

    public static double[] lerValoresIniciais(File nomeFicheiroVI) throws FileNotFoundException {

        String[] valores = lerFicheiros(nomeFicheiroVI);

        double[] valoresIniciais = new double[3];

        for (int i = 0; i < valoresIniciais.length; i++) {
            valoresIniciais[i] = Double.parseDouble(valores[i]);
        }
        return valoresIniciais;
    }

    // Extração dos valores do ficheiro

    public static String[] lerFicheiros(File nomeFicheiro) throws FileNotFoundException {

        Scanner lerFicheiro = new Scanner(nomeFicheiro);

        lerFicheiro.nextLine();

        String linhaValores = lerFicheiro.nextLine();

        lerFicheiro.close();

        linhaValores = linhaValores.replace(",", ".");

        return linhaValores.split(";");
    }

    // Escolha passo de integração (h)

    public static double escolherPassoDeIntegracao() {

        System.out.println("Que passo de integração deseja utilizar (entre 0 e 1):");

        double h = lerTeclado.nextDouble();

        while (h > 1 || h < 0) {
            System.out.println("Passo de integração inválido. Selecione um valor entre 0 e 1:");

            h = lerTeclado.nextDouble();
        }
        return h;
    }

    // Leitura de número de dias em análise

    public static int lerDias() {

        System.out.println("Digite o número de dias em análise:");

        int dias = lerTeclado.nextInt();

        while (dias < 0) {

            System.out.println("Número de dias inválido. Selecione um valor maior que 0:");

            dias = lerTeclado.nextInt();
        }
        return dias;
    }

    public static boolean testarParametros(double[] parametros) {
        final int numeroParamestrosEsperados = 8;
        return numeroParamestrosEsperados == parametros.length;
    }

    private static boolean testarValoresIniciais(double[] valoresIniciais) {
        final int numeroVIEsperados = 3;
        return numeroVIEsperados == valoresIniciais.length;
    }
}
