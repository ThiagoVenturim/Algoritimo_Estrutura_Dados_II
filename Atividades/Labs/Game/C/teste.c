#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX 50
#define TAM 1000
#define CSV 1848

// Struct de String
typedef struct {
    char str[TAM];
} String;

// Struct Game
typedef struct {
    int id;
    int estimatedOwners;
    int metacriticScore;
    int achievements;
    float price;
    float userScore;
    String name;
    String releaseDate;
    String priceStr;
    String linha;
    String suppportedLanguages[MAX];
    String publishers[MAX];
    String developers[MAX];
    String categories[MAX];
    String genres[MAX];
    String tags[MAX];
} Game;

void swap(Game game[], int i, int j) {
    Game temp = game[i];
    game[i] = game[j];
    game[j] = temp;
}

int length(String linha) {
    int i = 0;
    for (; linha.str[i] != '\0' && linha.str[i] != '\n'; i++);
    return i;
}

void separarPalavras(String linha, String conjunto[], bool aux) {
    int i = 0, j = 0, k = 0;

    while (linha.str[i] != '\0') {
        // Ignora colchetes e aspas
        if ((linha.str[i] == '[' && i == 0) || (linha.str[i] == ']' && i == length(linha) - 1)) {
            i++;
            continue;
        } else if (aux && linha.str[i] == '\'') {
            i++;
            continue;
        }

        if (linha.str[i] == ',') {
            conjunto[j].str[k] = '\0';
            j++;
            k = 0;
            if (linha.str[i + 1] == ' ') i++;
        } else {
            conjunto[j].str[k++] = linha.str[i];
        }
        i++;
    }

    if (k > 0 && (conjunto[j].str[k - 1] == '\'' || conjunto[j].str[k - 1] == ']')) {
        conjunto[j].str[k - 1] = '\0';
    } else {
        conjunto[j].str[k] = '\0';
    }

    conjunto[j + 1].str[0] = '\0';
}

int transformarInt(String linha) {
    if (linha.str[0] == '\0') return -1;
    int num = 0;
    for (int i = 0; i < length(linha); i++) {
        if (linha.str[i] >= '0' && linha.str[i] <= '9')
            num = num * 10 + (linha.str[i] - '0');
    }
    return num;
}

float transformarFloat(String linha) {
    if (strcmp(linha.str, "Free to Play") == 0)
        return 0.00f;

    String num, decimal;
    int vir = 0, j = 0, k = 0;
    for (int i = 0; i < length(linha); i++) {
        if (linha.str[i] == ',' || linha.str[i] == '.') {
            vir = 1;
            continue;
        }
        if (!vir)
            num.str[k++] = linha.str[i];
        else
            decimal.str[j++] = linha.str[i];
    }
    num.str[k] = '\0';
    decimal.str[j] = '\0';

    int inteiro = transformarInt(num);
    int dec = (j > 0) ? transformarInt(decimal) : 0;
    float frac = (j > 0) ? dec / pow(10, j) : 0.0f;

    return inteiro + frac;
}

String formatarData(String data) {
    String nova;
    char mesStr[16];
    int d = 1, y = 0, mes = 1;
    // Try to parse formats like "Jul 6, 2006" or "Jul 2006" or "d/m/Y"
    if (sscanf(data.str, "%3s %d, %d", mesStr, &d, &y) == 3) {
        // ok
    } else if (sscanf(data.str, "%3s %d", mesStr, &y) == 2) {
        d = 1;
    } else if (sscanf(data.str, "%d/%d/%d", &d, &mes, &y) == 3) {
        sprintf(nova.str, "%02d/%02d/%04d", d, mes, y);
        return nova;
    } else {
        // fallback: copy original
        strncpy(nova.str, data.str, TAM-1);
        nova.str[TAM-1] = '\0';
        return nova;
    }

    if (!strncmp(mesStr, "Jan", 3)) mes = 1;
    else if (!strncmp(mesStr, "Feb", 3)) mes = 2;
    else if (!strncmp(mesStr, "Mar", 3)) mes = 3;
    else if (!strncmp(mesStr, "Apr", 3)) mes = 4;
    else if (!strncmp(mesStr, "May", 3)) mes = 5;
    else if (!strncmp(mesStr, "Jun", 3)) mes = 6;
    else if (!strncmp(mesStr, "Jul", 3)) mes = 7;
    else if (!strncmp(mesStr, "Aug", 3)) mes = 8;
    else if (!strncmp(mesStr, "Sep", 3)) mes = 9;
    else if (!strncmp(mesStr, "Oct", 3)) mes = 10;
    else if (!strncmp(mesStr, "Nov", 3)) mes = 11;
    else if (!strncmp(mesStr, "Dec", 3)) mes = 12;

    sprintf(nova.str, "%02d/%02d/%04d", d, mes, y);
    return nova;
}

void classificarLinha(int opcao, String linha, Game *game, int i) {
    switch (opcao) {
        case 0: (game + i)->id = transformarInt(linha); break;
        case 1: (game + i)->name = linha; break;
        case 2: (game + i)->releaseDate = formatarData(linha); break;
        case 3: (game + i)->estimatedOwners = transformarInt(linha); break;
        case 4:
            (game + i)->price = transformarFloat(linha);
            (game + i)->priceStr = linha;
            break;
        case 5: separarPalavras(linha, (game + i)->suppportedLanguages, true); break;
        case 6: (game + i)->metacriticScore = transformarInt(linha); break;
        case 7: (game + i)->userScore = transformarFloat(linha); break;
        case 8: (game + i)->achievements = transformarInt(linha); break;
        case 9: separarPalavras(linha, (game + i)->publishers, false); break;
        case 10: separarPalavras(linha, (game + i)->developers, false); break;
        case 11: separarPalavras(linha, (game + i)->categories, false); break;
        case 12: separarPalavras(linha, (game + i)->genres, false); break;
        case 13: separarPalavras(linha, (game + i)->tags, false); break;
        default: break;
    }
}

void lerLinha(String linha, Game *game, int index) {
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
        nova.str[k] = '\0';
        classificarLinha(opcao, nova, game, index);
    }
}

void quickSort(Game game[], int esq, int dir) {
    int i = esq, j = dir;
    int pivo = game[(esq + dir) / 2].id;

    while (i <= j) {
        while (game[i].id < pivo) i++;
        while (game[j].id > pivo) j--;

        if (i <= j) {
            swap(game, i, j);
            i++;
            j--;
        }
    }

    if (esq < j) quickSort(game, esq, j);
    if (i < dir) quickSort(game, i, dir);
}

int pesquisaBinaria(Game game[], int esq, int dir, int x) {
    if (esq > dir) return -1;

    int meio = (esq + dir) / 2;
    if (game[meio].id == x) return meio;
    else if (game[meio].id < x) return pesquisaBinaria(game, meio + 1, dir, x);
    else return pesquisaBinaria(game, esq, meio - 1, x);
}

void imprimirArray(String linhas[]) {
    printf("[");
    if (linhas[0].str[0] != '\0')
        printf("%s", linhas[0].str);
    for (int i = 1; i < MAX && linhas[i].str[0] != '\0'; i++) {
        printf(", %s", linhas[i].str);
    }
    printf("]");
}

int main() {
    FILE *fp = fopen("/tmp/games.csv", "r");
    if (fp == NULL) {
        printf("Erro ao abrir o arquivo games.csv\n");
        return 1;
    }

    String linha;
    Game *game = (Game *)malloc(CSV * sizeof(Game));
    if (game == NULL) {
        printf("Erro ao alocar memoria\n");
        fclose(fp);
        return 1;
    }

    // pula o cabeçalho
    fgets(linha.str, sizeof(linha.str), fp);

    for (int i = 0; i < CSV; i++) {
        if (fgets(linha.str, sizeof(linha.str), fp) == NULL) break;
        lerLinha(linha, game, i);
    }

    quickSort(game, 0, CSV - 1);

    String x;
    while (true) {
        if (scanf("%s", x.str) != 1) break;
        if (strcmp(x.str, "FIM") == 0) break;

        int idBusca = transformarInt(x);
        int pos = pesquisaBinaria(game, 0, CSV - 1, idBusca);

        if (pos == -1) {
            printf("NAO ENCONTRADO\n");
            continue;
        }

        if (strcmp(game[pos].priceStr.str, "Free to Play") == 0 || fabs(game[pos].price) < 0.0001)
            printf("=> %d ## %s ## %s ## %d ## 0.0 ## ",
                   game[pos].id, game[pos].name.str, game[pos].releaseDate.str,
                   game[pos].estimatedOwners);
        else
            printf("=> %d ## %s ## %s ## %d ## %s ## ",
                   game[pos].id, game[pos].name.str, game[pos].releaseDate.str,
                   game[pos].estimatedOwners, game[pos].priceStr.str);

        imprimirArray(game[pos].suppportedLanguages);
        printf(" ## %d ## %.1f ## %d ## ",
               game[pos].metacriticScore, game[pos].userScore, game[pos].achievements);
        imprimirArray(game[pos].publishers);
        printf(" ## ");
        imprimirArray(game[pos].developers);
        printf(" ## ");
        imprimirArray(game[pos].categories);
        printf(" ## ");
        imprimirArray(game[pos].genres);
        printf(" ## ");
        imprimirArray(game[pos].tags);
        printf(" ##\n");
    }

    free(game);
    fclose(fp);
    return 0;
}
