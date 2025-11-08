#include <stdio.h>
#include <stdlib.h>



int * troca(int * matriz, int linhas, int colunas){
    int * nova = (int*)malloc(linhas*colunas*sizeof(int));
    for(int i=0 ; i<linhas; i++){
        for(int j=0 ; j<colunas; j++){
            if(*(matriz+i*linhas+j) == 0){
                int cont=0;
                if(i!=0){
                    cont += (*(matriz+(i-1)*linhas+j) == 1 ) ? 1:0;  
                 }
                if(j!=0){
                    cont += (*(matriz+i*linhas+(j-1)) == 1 ) ? 1:0;  
                }
                if(i!=linhas-1){
                    cont += (*(matriz+(i+1)*linhas+j) == 1  ) ? 1:0;  
                }
                if(j!=colunas-1){
                    cont += (*(matriz+i*linhas+(j+1)) == 1) ? 1:0;  
                }   
                *(nova+i*linhas+j) = cont;
            }else if(*(matriz+i*linhas+j) == 1 ){
                *(nova+i*linhas+j) = 9;
            }
        }           
  }
return nova;
}

void imprimir(int * matriz, int linhas, int colunas){
    for(int i=0 ; i<linhas; i++){
        for(int j=0 ; j<colunas; j++){
            printf("%d ", *(matriz+i*linhas+j));
        }
        printf("\n");       
    }
}


int main(){

    int linhas,colunas;
    while(scanf("%d %d", &linhas, &colunas) == 2){
        int *matriz = (int* ) malloc ( linhas*colunas*sizeof(int));
        if(matriz==NULL){return 1;}
        for(int i=0 ; i<linhas; i++){
            for(int j=0 ; j<colunas; j++){
                scanf("%d", (matriz+i*linhas+j));
            }       
        }
        matriz = troca(matriz, linhas, colunas);
        imprimir(matriz, linhas,colunas);
        free(matriz);
    }
    
return 0;
}
