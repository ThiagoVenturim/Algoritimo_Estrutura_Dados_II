#include <stdio.h>
#include <stdlib.h>
void swap(int i, int j, int * array){
    int tmp= *(array+i);
    *(array+i)= *(array+j);
    *(array+j)= tmp;
}

void quicksort(int *array, int esq, int dir){
    int i = esq;
    int j= dir;
    int pivo = *(array+ (i+j)/2);
    if(i<=j){
        while(*(array+i)>pivo){i++;}
        while(*(array+j)<pivo){j--;}
        if(i<=j){
            swap(i, j, array);
            i++;
            j--;
        }
    }
    if(esq<j){quicksort(array, esq, j);}
    if(dir>i){quicksort(array, i, dir);}
}

void preencher(int *array, int n){
    for(int i=0;i<n;i++){
        scanf("%d", (array+i));
    }
}

int  mostrar(int *array, int n){
    int somar=0;
    for(int i=0;i<n;i++){
        somar = somar+ *(array+i);
    }
return somar;
}

int main(){
    int n;
    while(scanf("%d", &n)!= EOF){

        int k;
        scanf("%d", &k);

        int *notas= (int*)malloc(n*sizeof(int));
        if(notas==NULL){return 1;}

        preencher(notas, n);
        quicksort(notas, 0, n-1);
        printf("%d\n", mostrar(notas, k));


        free(notas);
   }
   

return 0;
}