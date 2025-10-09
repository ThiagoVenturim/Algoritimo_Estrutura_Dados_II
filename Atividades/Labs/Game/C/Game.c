#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <math.h>

typedef struct {
    int id;
    char* name;
    char* releaseDate;
    int estimatedOwners;
    float price;
    char** supportedLanguages;
    int supportedLanguages_count;
    int metacriticScore;
    float userScore;
    int achievements;
    char** publishers;
    int publishers_count;
    char** developers;
    int developers_count;
    char** categories;
    int categories_count;
    char** genres;
    int genres_count;
    char** tags;
    int tags_count;
} Game;

// Funções auxiliares 
// Trim: remove espaços no início e fim
char* trim(char* str) {
    if (!str) return NULL;
    while (isspace((unsigned char)*str)) str++;
    if (*str == 0) return str;
    char* end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
    end[1] = '\0';
    return str;
}

// Faz split de string em array de strings dinâmicas
char** separarStringInterna(const char* texto, char separador, int* count) {
    *count = 0;
    if (!texto || texto[0] == '\0') return NULL;

    int temp_count = 1;
    for (int i = 0; texto[i]; i++)
        if (texto[i] == separador) temp_count++;

    char** resultado = malloc(temp_count * sizeof(char*));
    char buffer[1024];
    int idx = 0, buf_len = 0;

    for (int i = 0; texto[i]; i++) {
        if (texto[i] == separador) {
            buffer[buf_len] = '\0';
            resultado[idx++] = strdup(trim(buffer));
            buf_len = 0;
        } else {
            if (buf_len < 1023) buffer[buf_len++] = texto[i];
        }
    }
    buffer[buf_len] = '\0';
    resultado[idx++] = strdup(trim(buffer));
    *count = idx;
    return resultado;
}

// Converte string para float (tratando vírgula e ponto)
float transformarFloat(const char* str) {
    char temp[64];
    int j = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] == ',') temp[j++] = '.';
        else temp[j++] = str[i];
    }
    temp[j] = '\0';
    return atof(temp);
}

// Formata data do CSV para dd/mm/yyyy
char* formatarData(const char* data) {
    char mesNum[3] = "01";
    if (strstr(data, "Jan")) strcpy(mesNum, "01");
    else if (strstr(data, "Feb")) strcpy(mesNum, "02");
    else if (strstr(data, "Mar")) strcpy(mesNum, "03");
    else if (strstr(data, "Apr")) strcpy(mesNum, "04");
    else if (strstr(data, "May")) strcpy(mesNum, "05");
    else if (strstr(data, "Jun")) strcpy(mesNum, "06");
    else if (strstr(data, "Jul")) strcpy(mesNum, "07");
    else if (strstr(data, "Aug")) strcpy(mesNum, "08");
    else if (strstr(data, "Sep")) strcpy(mesNum, "09");
    else if (strstr(data, "Oct")) strcpy(mesNum, "10");
    else if (strstr(data, "Nov")) strcpy(mesNum, "11");
    else if (strstr(data, "Dec")) strcpy(mesNum, "12");

    int dia = 1, ano = 1970;
    char* token;
    char dataCopy[128];
    strncpy(dataCopy, data, 128);
    token = strtok(dataCopy, " ,");
    if (token) { /* mes */ token = strtok(NULL, " ,"); }
    if (token) dia = atoi(token);
    token = strtok(NULL, " ,");
    if (token) ano = atoi(token);

    char* novaData = malloc(11);
    sprintf(novaData, "%02d/%s/%d", dia, mesNum, ano);
    return novaData;
}

// Libera memória de um Game
void free_game_memory(Game* game) {
    free(game->name);
    free(game->releaseDate);
    for (int i = 0; i < game->supportedLanguages_count; i++) free(game->supportedLanguages[i]);
    free(game->supportedLanguages);
    for (int i = 0; i < game->publishers_count; i++) free(game->publishers[i]);
    free(game->publishers);
    for (int i = 0; i < game->developers_count; i++) free(game->developers[i]);
    free(game->developers);
    for (int i = 0; i < game->categories_count; i++) free(game->categories[i]);
    free(game->categories);
    for (int i = 0; i < game->genres_count; i++) free(game->genres[i]);
    free(game->genres);
    for (int i = 0; i < game->tags_count; i++) free(game->tags[i]);
    free(game->tags);
}

// Imprime um Game
void imprimir(const Game* game) {
    printf("=> %d ## %s ## %s ## %d ## ", game->id, game->name, game->releaseDate, game->estimatedOwners);
    if (game->price == 0.0f) {
    printf("%.1f ## [", game->price); 
    } else {
    printf("%g ## [", game->price); 
    }
    for (int i = 0; i < game->supportedLanguages_count; i++) {
        printf("%s%s", game->supportedLanguages[i], (i < game->supportedLanguages_count-1) ? ", " : "");
    }
    printf("] ## %d ## %.1f ## %d ## [", game->metacriticScore, game->userScore, game->achievements);
    for (int i = 0; i < game->publishers_count; i++) {
        printf("%s%s", game->publishers[i], (i < game->publishers_count-1) ? ", " : "");
    }
    printf("] ## [");
    for (int i = 0; i < game->developers_count; i++) {
        printf("%s%s", game->developers[i], (i < game->developers_count-1) ? ", " : "");
    }
    printf("] ## [");
    for (int i = 0; i < game->categories_count; i++) {
        printf("%s%s", game->categories[i], (i < game->categories_count-1) ? ", " : "");
    }
    printf("] ## [");
    for (int i = 0; i < game->genres_count; i++) {
        printf("%s%s", game->genres[i], (i < game->genres_count-1) ? ", " : "");
    }
    printf("] ## [");
    for (int i = 0; i < game->tags_count; i++) {
        printf("%s%s", game->tags[i], (i < game->tags_count-1) ? ", " : "");
    }
    printf("] ##\n");
}

//  Parsing do CSV 
void formatar(Game* game, const char* linha) {
    char* campos[20];
    int campos_count = 0;

    char buffer[8192];
    strcpy(buffer, linha);

    char* token;
    bool dentroAspas = false;
    char campoTemp[8192];
    int idx = 0;
    for (int i = 0; buffer[i]; i++) {
        if (buffer[i] == '"') dentroAspas = !dentroAspas;
        else if (buffer[i] == ',' && !dentroAspas) {
            campoTemp[idx] = '\0';
            campos[campos_count++] = strdup(trim(campoTemp));
            idx = 0;
        } else {
            campoTemp[idx++] = buffer[i];
        }
    }
    campoTemp[idx] = '\0';
    campos[campos_count++] = strdup(trim(campoTemp));

    // Preenche Game
    game->id = atoi(campos[0]);
    game->name = strdup(campos[1]);
    game->releaseDate = formatarData(campos[2]);
    game->estimatedOwners = atoi(campos[3]);
    game->price = transformarFloat(campos[4]);

    // supportedLanguages
    char linguagensLimpas[1024];
    int j = 0;
    for (int i = 0; campos[5][i]; i++) {
        if (campos[5][i] != '[' && campos[5][i] != ']' && campos[5][i] != '\'')
            linguagensLimpas[j++] = campos[5][i];
    }
    linguagensLimpas[j] = '\0';
    game->supportedLanguages = separarStringInterna(linguagensLimpas, ',', &game->supportedLanguages_count);

    game->metacriticScore = atoi(campos[6]);
    game->userScore = atof(campos[7]);
    game->achievements = atoi(campos[8]);
    game->publishers = separarStringInterna(campos[9], ',', &game->publishers_count);
    game->developers = separarStringInterna(campos[10], ',', &game->developers_count);
    game->categories = separarStringInterna(campos[11], ',', &game->categories_count);
    game->genres = separarStringInterna(campos[12], ',', &game->genres_count);
    game->tags = separarStringInterna(campos[13], ',', &game->tags_count);

    for (int i = 0; i < campos_count; i++) free(campos[i]);
}

void swap(Game* a, Game* b) {
    Game temp = *a;
    *a = *b;
    *b = temp;
}

// Partição baseada no campo 'id'
int partition(Game* arr, int low, int high) {
    int pivot = arr[high].id;
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (arr[j].id < pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }

    swap(&arr[i + 1], &arr[high]);
    return i + 1;
}

// Função recursiva do QuickSort
void quickSort(Game* arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

//  Função main 
int main() {
    FILE* arquivo = fopen("/tmp/games.csv", "r");
    if (!arquivo) { 
        printf("ERRO ao abrir games.csv\n"); 
        return 1; 
    }

    int capacity = 4000, size = 0;
    Game* gamesList = malloc(capacity * sizeof(Game));
    char linha[8192];

    fscanf(arquivo, "%[^\n]%*c", linha); // pular cabeçalho

    while (fscanf(arquivo, " %[^\n]%*c", linha) != EOF) {
        if (size >= capacity) {
            capacity *= 2;
            gamesList = realloc(gamesList, capacity * sizeof(Game));
        }
        formatar(&gamesList[size], linha);
        size++;
    }
    fclose(arquivo);

    // Ordena os jogos por nome
    quickSort(gamesList, 0, size - 1);

    //for (int i = 0; i < size; i++) { imprimir(&gamesList[i]);}

   char entrada[128]; 
    while (scanf("%s", entrada) != EOF){ 
        if (strcasecmp(entrada, "FIM") == 0) break; 
        int id = atoi(entrada); 
        for (int i = 0; i < size; i++){ 
            if (gamesList[i].id == id){ 
                imprimir(&gamesList[i]); 
                break; 
            } 
        } 
    }

    // Libera memória
    for (int i = 0; i < size; i++) free_game_memory(&gamesList[i]);
    free(gamesList);

    return 0;
}
