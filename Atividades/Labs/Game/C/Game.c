#include <stdio.h> 
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

#define MAX 50
#define TAM 1000
#define CSV 1848

typedef struct {
    char str[1000];
} String;

typedef struct {
    int id;
    int estimatedOwners;
    int metacriticScore;
    int achievements;
    float price;
    float userScore;
    String name;
    String releaseDate;
    String linha;
    String suppportedLanguages[MAX];
    String publishers[MAX];
    String developers[MAX];
    String categories[MAX];
    String genres[MAX];
    String tags[MAX];
} Game;

int length(String linha) {
    int i = 0;
    for (; linha.str[i] != '\0' && linha.str[i] != '\n'; i++);
    return i;
}

// separa palavras de uma string e retorna quantas encontrou
int separarPalavras(String linha, String conjunto[]) {
    int cout = 0, j = 0;
    for (int i = 0; i < length(linha); i++) {
        char c = linha.str[i];
        if (c == ',' || c == '\n' || c == '\0') {
            conjunto[cout].str[j] = '\0'; 
            cout++;
            j = 0;
        } else if (c != ' ' && c != '\"' && c != '\'') {
            conjunto[cout].str[j++] = c;
        }
    }
    conjunto[cout].str[j] = '\0'; 
    return cout + 1;
}

int tranformarInt(String linha) {
    int num = 0;
    for (int i = 0; i < length(linha); i++) {
        num = num * 10 + (linha.str[i] - '0');
    }
    return num;
}

float tranformarFloat(String linha) {
    String num, decimal;
    int vir = 0;
    int j = 0, k = 0;
    for (int i = 0; i < length(linha); i++) {
        if (linha.str[i] == ',' || linha.str[i] == '.') {
            vir = 1;
            continue;
        }
        if (!vir) { 
            num.str[k++] = linha.str[i];
        } else { 
            decimal.str[j++] = linha.str[i];
        }
    }
    num.str[k] = '\0';
    decimal.str[j] = '\0';

    int inteiro = tranformarInt(num);
    int dec = (j > 0) ? tranformarInt(decimal) : 0;
    float frac = (j > 0) ? dec / pow(10, j) : 0.0;

    return inteiro + frac;
}

void classificarLinha(int opcao, String linha, Game *game, int i) {
    switch (opcao) {
        case 0:
            (game+i)->id = tranformarInt(linha);
            break;
        case 1:
            (game+i)->name = linha;
            break;
        case 2:
            (game+i)->releaseDate = linha;
            break;
        case 3:
            (game+i)->estimatedOwners = tranformarInt(linha);
            break;
        case 4:
            (game+i)->price = tranformarFloat(linha);
            break;
        case 5:
            separarPalavras(linha, game->suppportedLanguages);
            break;
        case 6:
            (game+i)->metacriticScore = tranformarInt(linha);
            break;
        case 7:
            (game+i)->userScore = tranformarFloat(linha);
            break;
        case 8:
            (game+i)->achievements = tranformarInt(linha);
            break;
        case 9:
            separarPalavras(linha, (game+i)->publishers);                
            break;
        case 10:
            separarPalavras(linha, (game+i)->developers);
            break;
        case 11:
            separarPalavras(linha, (game+i)->categories); 
            break;
        case 12:
            separarPalavras(linha, (game+i)->genres); 
            break;
        case 13:
            separarPalavras(linha, (game+i)->tags);
            break;
        default:
            // ignora colunas extras
            break;
    }
}

void lerLinha(String linha, Game *game , int index) {
    int opcao = 0;
    for (int j = 0; j < length(linha); opcao++) {  
        String nova;
        int k = 0;
        bool parar = true;
        int aspas = 0;

        for (; j < length(linha) && parar; j++) {
            if (linha.str[j] == ',' && (aspas == 0 || (opcao != 2 && opcao != 1 && opcao != 5 && opcao < 9))) {
                parar = false;
            } else if (linha.str[j] == '\"') {
                aspas++;
                if (aspas == 2) {
                    parar = false;
                    j++;
                }
            } else {
                nova.str[k++] = linha.str[j];
            }
        }
        nova.str[k] = '\0'; // fecha a string corretamente
        classificarLinha(opcao, nova, game, index);
    }
}

int main() {
    String linha;
    Game *game = (Game*) malloc(CSV * sizeof(Game));
    
    fgets(linha.str, sizeof(linha.str), stdin);
    if (game == NULL) {
        printf("Erro ao alocar memoria\n"); 
        return 1;
    }

    for(int i =0 ; i<CSV ; i++){

        fgets(linha.str, sizeof(linha.str), stdin);
        lerLinha(linha, game, i);
        printf("---- Jogo: %d ----\n", i+1);
        printf("id: %d\n", (game+i)->id);
        printf("name: %s\n", (game+i)->name.str);
        printf("releaseDate: %s\n", (game+i)->releaseDate.str);
        printf("estimatedOwners: %d\n", (game+i)->estimatedOwners);
        printf("price: %.2f\n", (game+i)->price);
        printf("metacriticScore: %d\n", (game+i)->metacriticScore);
        printf("userScore: %.2f\n", (game+i)->userScore);
        printf("achievements: %d\n", (game+i)->achievements);
    }
    free(game);
    return 0;
}
