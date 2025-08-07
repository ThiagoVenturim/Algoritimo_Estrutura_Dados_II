#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int ContMaisRecursivo(char *str){
    if(*str == '\0'){ 
        return 0;
    }else if(*str >= 'A' && *str <= 'Z'){
        return 1 + ContMaisRecursivo(str + 1);
    }
    return ContMaisRecursivo(str + 1);
}

int ContMaisIterativo(char *str){
int cont = 0;
    for( ; *str!= '\0'; str++){
        if(*str >= 'A' && *str <= 'Z'){
            cont++;
        }
    }
return cont;
}

int main(){
char str[100];
    while(1){
        fgets(str, 100, stdin);
        if(strcmp(str, "FIM") == 0){ break;}
        printf("Recursivo: %d\n", ContMaisRecursivo(str));
        printf("Iterativo: %d\n", ContMaisIterativo(str));
    }

return 0;
}