#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int myAtoiRec(char *s, int i, int acc){
    int result;
    if(s[i] == '\0'){                 // fim da string
        result = acc;
    } else {
        if(s[i] >= '0' && s[i] <= '9'){   // só soma se for dígito
            acc = acc * 10 + (s[i] - '0');
        }
        result = myAtoiRec(s, i+1, acc);  // avança para o próximo caractere
    }
    return result;
}

int SomarDigitos(int num){
    if(num < 0) num = -num;
    if(num == 0) return 0;
    return (num % 10) + SomarDigitos(num / 10);
}

int main(){
    char entrada[50];

    while(scanf("%s", entrada) == 1){
        // verifica se o usuário digitou "FIM" (pela tabela ASCII)
        if (entrada[0] == 70 && entrada[1] == 73 && entrada[2] == 77 && entrada[3] == '\0'){
            break; 
        }

        int num = myAtoiRec(entrada, 0, 0); // converte string pra inteiro
        printf("%d\n", SomarDigitos(num));
    }
    return 0;
}
