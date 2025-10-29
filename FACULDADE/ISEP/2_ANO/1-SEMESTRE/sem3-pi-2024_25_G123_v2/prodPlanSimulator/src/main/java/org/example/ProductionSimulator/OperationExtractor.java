package org.example.ProductionSimulator;

import java.io.*;
import java.util.*;

public class OperationExtractor {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\USER\\IdeaProjects\\sem3-pi-2024_25_G123_v2\\operations(2).csv";  // Caminho do arquivo CSV
        String outputFilePath = "C:\\Users\\USER\\IdeaProjects\\sem3-pi-2024_25_G123_v2\\operation_ids.txt";  // Caminho do arquivo de saída

        List<Integer> operationIds = extractOperationIds(inputFilePath);

        // Exportar os IDs de operação para um arquivo de texto
        exportOperationIds(outputFilePath, operationIds);

        // Exibir os IDs de operação extraídos
        System.out.println("Operation IDs exportados para o arquivo: " + outputFilePath);
    }

    /**
     * Método para extrair os IDs de operação do arquivo CSV.
     * @param filePath Caminho do arquivo CSV a ser lido.
     * @return Lista com os IDs de operação extraídos do arquivo.
     */
    public static List<Integer> extractOperationIds(String filePath) {
        List<Integer> operationIds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Ignorar cabeçalho (caso exista)

            // Ler cada linha do arquivo CSV
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");

                // Verificar se a linha contém dados suficientes (op_id e op_name)
                if (columns.length >= 2) {
                    try {
                        // Extrair o ID da operação da primeira coluna
                        int opId = Integer.parseInt(columns[0].trim());
                        operationIds.add(opId);
                    } catch (NumberFormatException e) {
                        // Ignorar linhas com dados inválidos
                        System.err.println("Erro ao ler o ID da operação: " + columns[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return operationIds;
    }

    /**
     * Método para exportar os IDs de operação para um arquivo de texto.
     * @param filePath Caminho do arquivo de saída.
     * @param operationIds Lista de IDs de operação a serem exportados.
     */
    public static void exportOperationIds(String filePath, List<Integer> operationIds) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Operation IDs");
            writer.newLine();
            writer.write("----------------");
            writer.newLine();

            // Iterar e escrever os IDs de operação no arquivo
            for (int id : operationIds) {
                writer.write(String.valueOf(id));
                writer.newLine();
            }

            System.out.println("Operation IDs exportados para: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao exportar os IDs de operação: " + e.getMessage());
        }
    }
}
