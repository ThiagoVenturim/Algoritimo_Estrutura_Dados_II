#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void simNao(bool teste) {
    if (teste) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }
}

// Testa se só tem vogais
bool testVogais(char *str) {
    if (*str == '\0') return true; // fim da string
    char c = *str;

    bool letraMaius = (c >= 'A' && c <= 'Z');
    bool letraMinus = (c >= 'a' && c <= 'z');

    if (!(letraMaius || letraMinus)) return false; // não é letra
    if (!(c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
          c=='a'||c=='e'||c=='i'||c=='o'||c=='u')) return false;

    return testVogais(str+1);
}

// Testa se só tem consoantes
bool testConsoante(char *str) {
    if (*str == '\0') return true;

    char c = *str;
    bool letraMaius = (c >= 'A' && c <= 'Z');
    bool letraMinus = (c >= 'a' && c <= 'z');
    if (!(letraMaius || letraMinus)) return false; // não é letra

    if (c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||
        c=='a'||c=='e'||c=='i'||c=='o'||c=='u') return false;

    return testConsoante(str+1);
}

// Testa se só tem dígitos inteiros
bool testInteger(char *str) {
    if (*str == '\0') return true;
    char c = *str;
    if (c < '0' || c > '9') return false;
    return testInteger(str+1);
}

// Testa se é número real (dígitos + no máx 1 separador)
bool testRealAux(char *str, bool separador) {
    if (*str == '\0') return true;
    char c = *str;

    if (c >= '0' && c <= '9') {
        return testRealAux(str+1, separador);
    } else if (c == '.' || c == ',') {
        if (separador) return false; // já tinha separador
        return testRealAux(str+1, true);
    } else {
        return false; // caractere inválido
    }
}

bool testReal(char *str) {
    if (strlen(str) == 0) return false;
    return testRealAux(str, false);
}

// Main
int main() {
    char str[1000];
    while (1) {
        scanf(" %[^\n]", str);

        if (strcmp(str, "FIM") == 0) break;

        simNao(testVogais(str));
        simNao(testConsoante(str));
        simNao(testInteger(str));
        simNao(testReal(str));
        printf("\n");
    }
    return 0;
}
