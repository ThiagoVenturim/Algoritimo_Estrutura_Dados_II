#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 400

// Função principal que inicializa a recursão
int ehPalindromo(char *str) {
    return ehPalindromoRec(str, 0, strlen(str) - 1);
}

// Função recursiva
int ehPalindromoRec(char *str, int i, int j) {
    int resp;
    if (i >= j) {
        resp = 1; // SIM
    } else if (str[i] != str[j]) {
        resp = 0; // NAO
    } else {
        resp = ehPalindromoRec(str, i + 1, j - 1);
    }
    return resp;
}

int main() {
    char *str = (char *)malloc(MAX * sizeof(char));// alocar dinamicamente a memoria pra string
    if (str == NULL) return 1; // se for null retornar zero 

    while (fgets(str, MAX, stdin) != NULL && strncmp(str, "FIM", 3) != 0) { //loop com leitrua no incio se for fim sai do codigo
        str[strcspn(str, "\n")] = '\0'; // remove '\n'
        if (ehPalindromo(str)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    free(str); // liberar memoria
    return 0;
}
