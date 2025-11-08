#include <stdio.h>
int main(){
    int idadeMonica;
    int idade;
    int idade2;

    scanf("%d", &idadeMonica);
    scanf("%d %d", &idade, &idade2 );
    idadeMonica = idadeMonica-idade-idade2;
    if(idade>idadeMonica){
        idadeMonica=  idade;
    }else if(idade2>idadeMonica){
        idadeMonica=  idade2;
    }
    printf("%d\n",idadeMonica ); 

}