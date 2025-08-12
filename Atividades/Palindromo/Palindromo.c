#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


typedef struct{
    char frase[100];
    int tamanho;
}String;

/*void PalindromoIterativo(String *s){
    int i, j;
    for(i = 0, j = s->tamanho - 1; i < s->tamanho / 2; i++, j--){
        if(tolower(s->frase[i]) == tolower(s->frase[j])){
            printf("NAO\n");
            return;
        }
    }
    printf("SIM\n");
}*/

int PalindromoRecursivo(char *s, int tamanho, int index){
    if(index >= tamanho / 2){
        printf("SIM\n");
        return 1;
    }else if(tolower(s[index]) != tolower(s[tamanho - index - 1])){
        printf("NAO\n");
        return 0;
    }
    else{
        return palindromo(s, tamanho, index +    1);
    }
}
int main(){
    String s;
    
    do{
        fgets(s.frase, 100, stdin);
        s.tamanho = strlen(s.frase);
        if(strcmp(s.frase, "FIM") == 0){break;}
        //PalindromoInteretivo(&s);
        PalindromoRecursivo(s.frase, s.tamanho, 0);
    }while(1);


return 0;
}