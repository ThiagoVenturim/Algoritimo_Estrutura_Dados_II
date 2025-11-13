#include <stdio.h>

int main() {
    int M, A, B, C, maisVelho;

    scanf("%d", &M);
    scanf("%d", &A);
    scanf("%d", &B);

    // calcula a idade do terceiro filho
    C = M - (A + B);

    // encontra o mais velho
    maisVelho = A;
    if (B > maisVelho) maisVelho = B;
    if (C > maisVelho) maisVelho = C;

    printf("%d\n", maisVelho);

    return 0;
}
