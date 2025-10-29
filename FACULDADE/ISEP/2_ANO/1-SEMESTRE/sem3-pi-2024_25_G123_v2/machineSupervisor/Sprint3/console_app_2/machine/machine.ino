#include <DHT.h>

#define PINO_DHT 22       // Pino onde o DHT11 está conectado
#define TIPO_DHT DHT11    // Tipo do sensor DHT

DHT dht(PINO_DHT, TIPO_DHT);

bool piscar = false; // Estado de piscar do LED embutido
const int pinosLeds[] = {21, 20, 19, 18, 17}; // Pinos dos LEDs
const int qtdLeds = sizeof(pinosLeds) / sizeof(pinosLeds[0]);
unsigned long ultimoTempoPiscar = 0; // Controle do tempo para piscar
const unsigned long intervaloPiscar = 500; // Intervalo de piscar
bool estadoLed = false; // Estado do LED embutido

// Variável para armazenar o estado da máquina
String estadoMaquina = "";

unsigned long tempoExibirEstado = 0; // Tempo de exibição do estado
const unsigned long duracaoExibicao = 2000; // 2 segundos

void setup() {
    pinMode(LED_BUILTIN, OUTPUT);
    for (int i = 0; i < qtdLeds; i++) {
        pinMode(pinosLeds[i], OUTPUT);
    }
    Serial.begin(9600);
    dht.begin(); // Inicializa o sensor DHT
}

void loop() {
  // wait_for_command_from_mach_manager()
    if (Serial.available() > 0) {
        String comando = Serial.readStringUntil('\n');
        comando.trim();

        if (comando.startsWith("OP,")) {
            piscar = true;
            String binarioStr = comando.substring(3); // Recebe a parte após "OP,"
            int indiceLed = 0;
            int tamanhoStr = binarioStr.length();

            // Processa os bits em ordem inversa
            for (int i = tamanhoStr - 1; i >= 0; i--) {
                char c = binarioStr[i];
                if (c == '1' || c == '0') {
                    if (indiceLed < qtdLeds) {
                        digitalWrite(pinosLeds[indiceLed], (c == '1') ? HIGH : LOW);
                        indiceLed++;
                    }
                }
            }

            // Apaga LEDs extras
            for (; indiceLed < qtdLeds; indiceLed++) {
                digitalWrite(pinosLeds[indiceLed], LOW);
            }

            estadoMaquina = "OP"; // Atualiza o estado da máquina para "OP"
            tempoExibirEstado = millis(); // Atualiza o tempo de exibição do estado
        } else if (comando.startsWith("ON,")) {
            piscar = false;
            digitalWrite(LED_BUILTIN, HIGH);
            String binarioStr = comando.substring(3); // Recebe a parte após "ON,"
            int indiceLed = 0;
            int tamanhoStr = binarioStr.length();

            // Processa os bits em ordem inversa
            for (int i = tamanhoStr - 1; i >= 0; i--) {
                char c = binarioStr[i];
                if (c == '1' || c == '0') {
                    if (indiceLed < qtdLeds) {
                        digitalWrite(pinosLeds[indiceLed], (c == '1') ? HIGH : LOW);
                        indiceLed++;
                    }
                }
            }

            // Apaga LEDs extras
            for (; indiceLed < qtdLeds; indiceLed++) {
                digitalWrite(pinosLeds[indiceLed], LOW);
            }

            estadoMaquina = "LIGADO"; // Atualiza o estado da máquina para "LIGADO"
            tempoExibirEstado = millis(); // Atualiza o tempo de exibição do estado
        } else if (comando == "OFF") {
            piscar = false;
            digitalWrite(LED_BUILTIN, LOW);

            // Apaga todos os LEDs
            for (int i = 0; i < qtdLeds; i++) {
                digitalWrite(pinosLeds[i], LOW);
            }

            estadoMaquina = "DESLIGADO"; // Atualiza o estado da máquina para "DESLIGADO"
            tempoExibirEstado = millis(); // Atualiza o tempo de exibição do estado
        }

        // send_data()
        enviarDadosSensor(); // Envia dados de temperatura e umidade após cada comando
    }

    // turn (on / off) leds
    exibirEstadoMaquina(); // Exibe o estado da máquina

    // Fazer o LED piscar a cada intervalo definido em intervaloPiscar
    if (piscar) {
        unsigned long millisAtual = millis();
        if (millisAtual - ultimoTempoPiscar >= intervaloPiscar) {
            ultimoTempoPiscar = millisAtual;
            estadoLed = !estadoLed;
            digitalWrite(LED_BUILTIN, estadoLed ? HIGH : LOW);
        }
    }
}

void enviarDadosSensor() {
  // read_temp / hum_from_sensor() 
    float temperatura = dht.readTemperature(); // Ler temperatura
    float umidade = dht.readHumidity();        // Ler umidade

    if (isnan(temperatura) || isnan(umidade)) {
        Serial.println("ERRO,TEMP,UMID");
    } else {
        int tempInteiro = (int)round(temperatura); // Converter temperatura para inteiro
        int umidInteiro = (int)round(umidade);    // Converter umidade para inteiro

        Serial.print("TEMP&unidade:graus&valor:");
        Serial.print(tempInteiro);
        Serial.print("#UMID&unidade:percentual&valor:");
        Serial.println(umidInteiro);
    }
}

void exibirEstadoMaquina() {
    // Mostra o estado da máquina usando o LED embutido por 2 segundos
    // Sleep 2 seconds
    if (millis() - tempoExibirEstado < duracaoExibicao) {
        if (estadoMaquina == "LIGADO") {
            digitalWrite(LED_BUILTIN, HIGH);  // Acende o LED para mostrar "ON"
        } else if (estadoMaquina == "DESLIGADO") {
            digitalWrite(LED_BUILTIN, LOW);   // Apaga o LED para mostrar "OFF"
        } else if (estadoMaquina.startsWith("OP")) {
            digitalWrite(LED_BUILTIN, HIGH);  // Acende o LED para mostrar "OP"
        }
    } else {
        digitalWrite(LED_BUILTIN, LOW);  // Apaga o LED após 2 segundos
    }
}
