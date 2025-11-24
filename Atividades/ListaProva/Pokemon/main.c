#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct{
    char str [100];
}String;

bool inserir(String nome, String *vetor, int atual , int index){
    int resp= true;
    if(index != atual){
        if(strcmp(vetor[atual].str, nome.str)!=0){
           resp=  inserir(nome,vetor, atual+1, index);
        }else{
            resp=false;
        }
    }
return resp;
}

int main(){
    int num;
    scanf(" %d", &num);
    String *vetor = (String *)malloc (num*sizeof(String));
    if(vetor==NULL){return 1;}
    int count=0;

    for(int i=0; i<num; i++){
        String nome;
        scanf(" %[^\n]", nome.str);
        if(inserir(nome, vetor, 0 , count)){
            vetor[count++] = nome;
        }
    }
    printf("%d\n", 151-count-1);
return 0;
}