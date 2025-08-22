#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct{
    char frase[100];
    int tamanho;
} String;

int PalindromoRecursivo(char *s, int tamanho, int index){
    if(index >= tamanho / 2){
        printf("SIM\n");
        return 1;
    } else if((s[index]) != (s[tamanho - index - 1])){
        printf("NAO\n");
        return 0;
    } else {
        return PalindromoRecursivo(s, tamanho, index + 1);
    }
}

int main(){
    String s;
    
    while(1){
        fgets(s.frase, 100, stdin);

        // remover \n
        s.frase[strcspn(s.frase, "\n")] = '\0';
        s.tamanho = strlen(s.frase);

        if(strcmp(s.frase, "FIM") == 0) break;

        PalindromoRecursivo(s.frase, s.tamanho, 0);
    }

    return 0;
}
