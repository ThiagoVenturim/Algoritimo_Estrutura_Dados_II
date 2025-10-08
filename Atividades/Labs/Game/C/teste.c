#include <stdio.h> 
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX 50
#define TAM 1000
#define CSV 1848

// Struct de String para declarar mais facil
typedef struct {
    char str[1000];
} String;

// Struct de String para Games
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


void swap( Game game [] , int i, int j) {
    Game temp = game[i];
    game[i] = game[j];;
    game[j] = temp;
}

int length(String linha) {
    int i = 0;
    for (; linha.str[i] != '\0' && linha.str[i] != '\n'; i++);
    return i;
}

void separarPalavras(String linha, String conjunto[], bool aux) {
    int i = 0, j = 0, k = 0, cout =0;
    while (linha.str[i] != '\0') {
        // Ignora colchetes
        if ((linha.str[i] == '[' && i== 0) || (linha.str[i] == ']' &&  i== length(linha)-1 )) {
            i++;
            continue;
        }else if(aux && linha.str[i] == '\'' ){
            if(linha.str[i+1] != '\0'){
                i++;
            }
        }


        // Separador por vírgula
        if (linha.str[i] == ',') {
            conjunto[j].str[k] = '\0'; // finaliza a string atual
            
            j++;
            if(linha.str[i+1] == ' ' && linha.str[i+1] != '\0'){
                i++;
            }
            k = 0;                     // reinicia o índice
        } else  {
            conjunto[j].str[k++] = linha.str[i];
        }
        i++;
    }
            // Finaliza a última palavra
        if(conjunto[j].str[k-1]=='\'' || conjunto[j].str[k-1]==']' ){
            conjunto[j].str[k-1] = '\0';
        }else{
            conjunto[j].str[k] = '\0';
        }
     
        //Marca o fim da lista (opcional)
         conjunto[j + 1].str[0] = '\0';


    
}
int tranformarInt(String linha) {
    if( linha.str == NULL){
        return -1;
    }
    int num = 0;
    
    for (int i = 0; i < length(linha); i++) {
        num = num * 10 + (linha.str[i] - '0');
    }
    return num;
}

float tranformarFloat(String linha) {
    if(strcmp(linha.str, "Free to Play") == 0){
        return 0.00;
    }
        
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

String formatarData(String data){
        bool enc= false;
        for( int i=0; i<length(data) && data.str[i] != '\0'; i++){
            if (data.str[i] == ','){
                enc =true;   
            }
        }
        if(!enc){
        String test;
        int j;
        for( int i=0, j=0; i<length(data) && data.str[i] != '\0'; i++, j++){
            test.str[j]= data.str[i];
            if(data.str[i] == ' ' ){
                j++;
                test.str[j] = '0';
                j++;
                test.str[j] = '1';
                j++;
                test.str[j] = ',';
                j++;
                test.str[j] = ' ';
            }
        }       
            test.str[j]= '\0';
            data= test;
        }

    String mes;
    switch (data.str[0])
    {
    case 'J':
        if(data.str[1] == 'a'){ mes.str[0]= '0'; mes.str[1]= '1'; } 
        else if(data.str[2] == 'n'){ mes.str[0]= '0'; mes.str[1]= '6'; } 
        else if(data.str[2] == 'l'){  mes.str[0]= '0'; mes.str[1]= '7';}  
    break;
    case 'F':
        mes.str[0]= '0'; mes.str[1]= '2';
    break;
    case 'M':
        if(data.str[2] == 'r'){ mes.str[0]= '0'; mes.str[1]= '3'; }
        else{mes.str[0]= '0'; mes.str[1]= '5'; }
    break;
    case 'A':
        if(data.str[1] == 'p'){ mes.str[0]= '0'; mes.str[1]= '4'; }
        else{mes.str[0]= '0'; mes.str[1]= '8'; }
    break;
    case 'S':
        mes.str[0]= '0'; mes.str[1]= '9'; 
    break;
      case 'O':
        mes.str[0]= '1'; mes.str[1]= '0';
    break;
     case 'N':
        mes.str[0]= '1'; mes.str[1]= '1';
    break;
     case 'D':
        mes.str[0]= '1'; mes.str[1]= '2'; 
    break;
    default: mes.str[0]= '0'; mes.str[1]= '1';   break;
    }
    mes.str[2] ='\0';
    String nova;

    int i = 4; 
    if(data.str[i+1] == ','){
        data.str[i-1]='0';
        i=3;
    }
    int j;
    for( j=0; data.str[i]!= '\0';i++){
        if(data.str[i]!= ',' && data.str[i]!= ' '  ){
            nova.str[j] = data.str[i];
            j++;
            if(j==2){
                nova.str[j]= '/';
                j++;
                nova.str[j]= mes.str[0];
                j++;
                nova.str[j]= mes.str[1];
                j++;
                nova.str[j]= '/';
                j++;
            }
        }
    }
    nova.str[j]= '\0';
    return nova;
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
            (game+i)->releaseDate = formatarData( linha);
            break;
        case 3:
            (game+i)->estimatedOwners = tranformarInt(linha);
            break;
        case 4:
            (game+i)->price = tranformarFloat(linha);
            break;
        case 5:
            separarPalavras(linha, (game+i)->suppportedLanguages, true);
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
            separarPalavras(linha, (game+i)->publishers, false);                
            break;
        case 10:
            separarPalavras(linha, (game+i)->developers, false);
            break;
        case 11:
            separarPalavras(linha, (game+i)->categories, false); 
            break;
        case 12:
    case 'J': 
            separarPalavras(linha, (game+i)->genres, false); 
            break;
        case 13:
            separarPalavras(linha, (game+i)->tags, false);
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

void imprimirArray(String linhas[]){
    printf("[");
    if (linhas[0].str[0] != '\0')
        printf("%s", linhas[0].str);
    for (int i = 1; i < MAX && linhas[i].str[0] != '\0'; i++){
        printf(", %s", linhas[i].str);
    }
    printf("]");
}





void imprimir(Game game[], int pos){
    for(int i = pos; i <=pos; i++){
        printf("=> %d ## %s ## %s ## %d ## ", game[i].id, game[i].name.str, game[i].releaseDate.str, game[i].estimatedOwners);
        if( game[i].price == game[i].price + 0.00f){

        }
        
        double parte_fracionaria_x100 = fmod(game[i].price * 100.0, 100.0);

        int ultimo_digito = (int)round(parte_fracionaria_x100) % 10;
        
        
        if (ultimo_digito == 0) {
            // Se a segunda casa decimal for zero (ex: 7.5**0**), mostre apenas uma casa.
            printf("%.1f ## ", game[i].price);
        } else {
            // Caso contrário (ex: 6.7**8**), mostre duas casas.
            printf("%.2f ## ", game[i].price);
        }
            
       
        imprimirArray(game[i].suppportedLanguages);
        printf(" ##");
        printf(" %d ## ",   game[i].metacriticScore);
        printf("%.1f ## ", game[i].userScore);
        printf("%d ## ",  game[i].achievements);
        imprimirArray(game[i].publishers);
        printf(" ## ");
        imprimirArray(game[i].developers);
        printf(" ## ");
        imprimirArray(game[i].categories);
        printf(" ## ");
        imprimirArray(game[i].genres);
        printf(" ## ");
        imprimirArray(game[i].tags);
        printf(" ##");
        printf("\n");
    }
}

void quickSort(Game game[], int esq, int dir) {
    int i = esq, j = dir;
    int pivo = game[(esq + dir) / 2].id;

    while (i <= j) {
        while (game[i].id < pivo) i++;
        while (game[j].id > pivo) j--;

        if (i <= j) {
            swap(game, i , j);
            i++;
            j--;
        }
    }

    if (esq < j) quickSort(game, esq, j);
    if (i < dir) quickSort(game, i, dir);
}

int pesquisaBinaria(Game game[], int esq, int dir, int x) {
    if (esq > dir) {
        return -1; 
    }

    int meio = (esq + dir) / 2;

    if (game[meio].id == x) {
        imprimir(game, meio);
        return meio;
    } else if (game[meio].id < x) {
        return pesquisaBinaria(game, meio + 1, dir, x);
    } else {
        return pesquisaBinaria(game, esq, meio - 1, x);
    }
}



int main() {
    FILE *fp = fopen("games.csv", "r");
    //FILE *fp = fopen("/tmp/games.csv", "r");
    if (fp == NULL) {
        printf("Erro ao abrir o arquivo games.csv\n");
        return 1;
    }

    String linha;
    Game *game = (Game*) malloc(CSV * sizeof(Game));
    if (game == NULL) {
        printf("Erro ao alocar memoria\n"); 
        fclose(fp);
        return 1;
    }

    // pula o cabeçalho do CSV
    if (fgets(linha.str, sizeof(linha.str), fp) == NULL) {
        printf("Arquivo vazio ou com erro\n");
        free(game);
        fclose(fp);
        return 1;
    }

    for(int i = 0; i < CSV; i++){
        if (fgets(linha.str, sizeof(linha.str), fp) == NULL) {
            break; // fim do arquivo
        }
        lerLinha(linha, game, i);
    }

    quickSort(game, 0, CSV-1);
    //for(int i=0; i<CSV; i++){ imprimir(game, i);}

    /*String x;
    int ver;
    bool aux = true;
    while (aux){
        fgets(x.str, 50, stdin);
        if(x.str[0] == 'F' || x.str[1] == 'I' ||x.str[3] == 'M' ){ 
            aux = false;
        }
        ver = tranformarInt(x);
        pesquisaBinaria(game, 0, CSV-1, ver);    
    }*/ 

    String entrada;
    while (scanf("%s", entrada.str) != EOF){ 
        if (strcmp(entrada.str, "FIM") == 0) break; 
        int id = atoi(entrada.str); 
        for (int i = 0; i < CSV; i++){ 
            if (game[i].id == id){ 
                imprimir(game, i); 
                break; 
            } 
        } 
    }
    free(game);
    fclose(fp);
    return 0;
}