#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void combinar(char *str1, char *str2) {

    int tamanho = strlen(str1) + strlen(str2);
    char new[tamanho + 1]; // +1 para o '\0'
    int j = 0;

    // percorre enquanto ainda houver caracteres em uma das duas
    for (int i = 0; str1[i] != '\0' || str2[i] != '\0'; i++) {
        if (str1[i] != '\0') {
            new[j++] = str1[i];
        }
        if (str2[i] != '\0') {
            new[j++] = str2[i];
        }
    }

    new[j] = '\0'; // encerra string
    printf("Nova String: %s\n", new);
}

int main() {
    char str1[30], str2[30];

    scanf("%s %s", str1, str2);
    combinar(str1, str2);

    return 0;
}
