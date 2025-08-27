#include <stdio.h>
#include <string.h>
#include <stdbool.h>

// Função auxiliar: escreve "SIM" ou "NAO"
void simNao(bool teste) {
    if (teste) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }
}

// Retorna true se a string contiver somente vogais (A, E, I, O, U / a, e, i, o, u)
// Rejeita caracteres especiais (não ASCII entre 'A'-'Z' e 'a'-'z')

bool testVogais(char str[]) {
    if (strlen(str) == 0) return false;
    for (int i = 0; i < strlen(str); i++) {
        char c = str[i];
        // verifica se é letra
        bool letraMaius = (c >= 'A' && c <= 'Z');
        bool letraMinus = (c >= 'a' && c <= 'z');
        if (!(letraMaius || letraMinus)) {
            return false; // não é letra
        }
        // precisa ser vogal
        if (!(c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
              c=='a'||c=='e'||c=='i'||c=='o'||c=='u')) {
            return false;
        }
    }
    return true;
}


// Retorna true se a string contiver somente consoantes
// (letras entre A-Z ou a-z, mas não vogais)
bool testConsoante(char str[]) {
    if (strlen(str) == 0) return false;
    for (int i = 0; i < strlen(str); i++) {
        char c = str[i];
        // verifica se é letra
        bool letraMaius = (c >= 'A' && c <= 'Z');
        bool letraMinus = (c >= 'a' && c <= 'z');
        if (!(letraMaius || letraMinus)) {
            return false; // não é letra
        }
        // rejeita se for vogal
        if (c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
            c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {
            return false;
        }
    }
    return true;
}


bool testInteger(char str[]) {
    if (strlen(str) == 0) return false;
    for (int i = 0; i < strlen(str); i++) {
        char c = str[i];
        if (c < '0' || c > '9') {
            return false;
        }
    }
    return true;
}


// Retorna true se a string representar um número real
// (composta por dígitos e no máximo um separador '.' ou ',')

bool testReal(char str[]) {
    if (strlen(str) == 0) return false;
    bool temSeparador = false;
    for (int i = 0; i < strlen(str); i++) {
        char c = str[i];
        if (c >= '0' && c <= '9') {
            continue;
        } else if (c == '.' || c == ',') {
            if (temSeparador) return false; // já tinha um separador
            temSeparador = true;
        } else {
            return false; // caractere inválido
        }
    }
    return true;
}


// Função principal
// Lê strings até encontrar "FIM"
// Para cada entrada imprime os 4 testes: vogal, consoante, inteiro, real

int main() {
    char str[1000];

    while (1) {
        scanf(" %[^\n]", str); // lê linha inteira (até \n)

        if (strcmp(str, "FIM") == 0) {
            break; // condição de parada
        }

        simNao(testVogais(str));
        simNao(testConsoante(str));
        simNao(testInteger(str));
        simNao(testReal(str));
        printf("\n");
    }

    return 0;
}
