#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100




int main(){
    char primeira[MAX];
    char segunda[MAX];

    while(scanf(" %s %s", primeira, segunda)==2){

        for(int i =0, j=0 ; primeira[i]!= '\0' || segunda[j]!= '\0';){
            if( primeira[i]!= '\0'){
                printf("%c", primeira[i]);
                i++;
            }
            if(segunda[j]!= '\0'){
                printf("%c", segunda[j]);
                j++;
    
            }
        }
    printf("\n");
    }

return 0;
}