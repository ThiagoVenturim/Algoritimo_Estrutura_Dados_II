#include <stdio.h>
#include <stdlib.h>

void preencherMatriz(char *str, char **matriz, int linhaAtual, int coluna){
    for(int j=0; j<coluna; j++){
        matriz[linhaAtual][j]= str[j];
    }
}

void mostrarMatriz( char **matriz, int linha, int coluna){
    for(int i=0; i<linha; i++){
        for(int j=0; j<coluna; j++){
            printf("%c", matriz[i][j]);
        }
        printf("\n");
    }
}


void converterMatriz(char **matriz, int linha, int coluna){
    int flag = 1;
    while (flag) {
        flag = 0;
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if (matriz[i][j] == '.') {
                    // Regra 1: água vem de cima
                    if (i > 0 && matriz[i - 1][j] == 'o') {
                        matriz[i][j] = 'o';
                        flag = 1;
                    }
                    // Regra 2: escorre da esquerda
                    else if (j > 0 && i < linha - 1 &&
                             matriz[i][j - 1] == 'o' &&
                             matriz[i + 1][j - 1] == '#') {
                        matriz[i][j] = 'o';
                        flag = 1;
                    }
                    // Regra 3: escorre da direita
                    else if (j < coluna - 1 && i < linha - 1 &&
                             matriz[i][j + 1] == 'o' &&
                             matriz[i + 1][j + 1] == '#') {
                        matriz[i][j] = 'o';
                        flag = 1;
                    }
                }
            }
        }
    }
}

int main(){
    int linha, coluna;
    scanf("%d %d", &linha, &coluna);

    char **matriz = (char **)calloc(linha, sizeof(char *));
    if(matriz==NULL){return 1;}
    for(int i=0; i<linha; i++){
        *(matriz+i) = (char *) calloc(coluna, sizeof(char));
        if(*(matriz+i) == NULL){return 1;}
    }
    

    for(int i=0 ; i<linha ; i++){
        char str[100];
        scanf(" %[^\n]", str);
        preencherMatriz(str, matriz, i, coluna);
    }
    mostrarMatriz(matriz, linha, coluna);
    printf("\n");
    converterMatriz(matriz,linha, coluna);
     mostrarMatriz(matriz, linha, coluna);
    for(int i=0; i<linha; i++){
        free(*(matriz+i));
    }
    free(matriz);

    return 0;
}