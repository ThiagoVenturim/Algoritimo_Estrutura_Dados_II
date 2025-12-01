#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char str[100];
}String;

void swap(String *array, int i, int j){
    String tmp = array[i];
    array[i] = array[j];
    array[j]= tmp;
}

void quicksort(String *array, int esq, int dir){
    int i=esq;
    int j=dir;
    String pivo = array[(esq + dir)/2];
    if(i<=j){
        while(strcmp(array[i].str, pivo.str)<0){i++;}
        while(strcmp(array[j].str, pivo.str)>0){j--;}
        if(i<=j){
            swap(array, i, j);
            j--;
            i++;
        }
    }
    if(esq<j){quicksort(array, esq, j);}
    if(dir>i){quicksort(array, i, dir);}
}

void mostrar(String *array,  int count){
    for(int i= 0; i<count; i++){
        printf("%s", array[i].str);
    }
}
int main(){

    String entrada;
    String *array =(String *) malloc(100*sizeof(String));
    int cout=0;
    fflush(stdin);

    while(fgets(entrada.str, 100, stdin) != NULL){
        
        if(entrada.str[0]== '0'){
            quicksort(array, 0, cout-1);
            mostrar(array, cout);
            printf("\n");
            cout=0;
        }else if(entrada.str[0]!= '1') {      
            array[cout++] = entrada;
        }
    }
free(array);
return 0;
}