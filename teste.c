#include <stdio.h>
int func(int num){
     if(num/10==0){
        return num;
    }else{
        if(num% 2 == 0){
            return num%10 + func(num/10);
        }else{
            return num%10 * func(num/10);
        }
    }
}

int calcular(int num){
    int impar=0; int par=0;
    int j=1, k=1;
    for(int i=num; i>0; i/=10 ){
        if(i%2==0){
            impar += (i%10)*j;
            j*=10;
        }
        else{
             par += (i%10)*k;
            k*=10;
        }
    }
    par= func(par);
    impar = func(impar);
    if(par==0){
        par =1;
    }else if(impar==0){
        impar = 1;
    }
    
    return  impar * par;
} 


int main(){
    printf(" %d", calcular(77));
}