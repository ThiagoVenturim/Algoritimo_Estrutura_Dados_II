#include <stdio.h>

int  Sequencial(int a){
    a--;
    a-=3;
    a=a-2;
return a;
}

int Condicional(int a, int b){
    a = b = 6;
    if(a-5 <b-3){
        a--;
        --b;
        a-=3;
    }else{
        a--;
    }
return a;
}

int Repeticao(int a, int b, int n){
    a = b = n= 5;
    for(int i =0; i<n; i++){
        a--;
        b--;
    }
return a;
}

int main(){
int a =0, b=0, n=0;
    a = Sequencial( a);
    a = Condicional(a, b);
    a= Repeticao(a, b, n);
    
return 0;
}