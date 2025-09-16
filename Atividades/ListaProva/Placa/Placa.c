#include <stdio.h>
#include <stdio.h>
#include <string.h>

void verificarPlaca(int tamanho, char placa[]){
    int reposta=1 ;
        if(tamanho>8 || tamanho<7 ){ reposta = 0; 
        }else if(tamanho==8){    
            reposta =1;
            for(int i =0; i<tamanho; i++){
                if(i<3){
                    if(placa[i]  < 'A' || placa[i]  >'Z' ){
                        reposta=0;
                        
                    }
                }else if(i==3){
                    if(placa[i]  != '-'){
                        reposta=0;
                       
                    }
                }else if( i>=4){
                    if(placa[i]  < '0' || placa[i]  >'9' ){
                        reposta=0;
                      
                    }
                }
            }
        }else if(tamanho==7){
             reposta = 2;
            for(int i =0; i<tamanho; i++){
                if(i<3){
                    if(placa[i]  < 'A' || placa[i]  >'Z' ){
                        reposta=0;
                    }
                }else if(i==3 || i>4){
                    if(placa[i]  < '0' || placa[i]  >'9' ){
                        reposta=0;
                    }
                }else if( i==4){
                    if(placa[i] < 'A' || placa[i] >'Z' ){
                        reposta=0;
                    }
                }
            }
        } 
        printf("%d", reposta);
        return;
    }

int main(){
        char placa[50];
        int tamanho=0;
        fgets(placa, 50 , stdin);
        for(int i =0; placa[i] != '\0'; i++){tamanho++;}
        verificarPlaca( tamanho-1, placa);
    return 0;
}