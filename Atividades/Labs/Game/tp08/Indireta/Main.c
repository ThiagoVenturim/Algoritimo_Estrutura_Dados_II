#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int comparar = 0;
int movimentacoes = 0;
// Auxilio.
#define TAM_MAX 1000
#define TAM 50
#define TAM_TAB 21

// Struct String para auxilio.
typedef struct {
    char str[TAM_MAX];
} String;

//Struct Data
typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

// Struct Game.
typedef struct Game {
    int id;
    String nome;
    Data data;
    int jogadores;
    float preco;
    String idiomas[TAM];
    int num_idiomas;
    int notaEspecial;
    float notaUsuario;
    int conquistas;
    String empresasPublicacao[TAM];
    int numEmpresasPublicacao;
    String empresasEstudios[TAM];
    int numEmpresasEstudios;
    String categorias[TAM];
    int numCategorias;
    String generos[TAM];
    int numGeneros;
    String tags[TAM];
    int numTags;
} Game;

Game gamevazio() {
    Game g;
    g.id = -1;
    strcpy(g.nome.str, "nenhum");
    g.data.dia = 1;
    g.data.mes = 1;
    g.data.ano = 2000;
    g.jogadores = 0;
    g.preco = 0.0f;
    for (int i = 0; i < TAM; i++) {
        g.idiomas[i].str[0] = '\0';
        g.empresasPublicacao[i].str[0] = '\0';
        g.empresasEstudios[i].str[0] = '\0';
        g.categorias[i].str[0] = '\0';
        g.generos[i].str[0] = '\0';
        g.tags[i].str[0] = '\0';
    }
    g.num_idiomas = g.numEmpresasPublicacao = g.numEmpresasEstudios = 0;
    g.numCategorias = g.numGeneros = g.numTags = 0;
    g.notaEspecial = -1;
    g.notaUsuario = -1.0f;
    g.conquistas = 0;
    return g;
}


int formatar(String entrada, String saida[], bool apostrofo) {
    String aux;
    aux.str[0] = '\0';
    int contador = 0, auxPos = 0;
    for (int i = 0; i < (int)strlen(entrada.str); i++) {
        char c = entrada.str[i];
        if (c == ',') {
            aux.str[auxPos] = '\0';
            int start = 0;
            while (aux.str[start] == ' ') start++;
            if (strlen(aux.str + start) > 0) {
                strcpy(saida[contador].str, aux.str + start);
                contador++;
            }
            aux.str[0] = '\0';
            auxPos = 0;
        } else {
            if (!(c == '[' || c == ']' || (apostrofo && c == '\''))) {
                aux.str[auxPos++] = c;
            }
        }
    }
    aux.str[auxPos] = '\0';
    if (strlen(aux.str) > 0) {
        int start = 0;
        while (aux.str[start] == ' ') start++;
        strcpy(saida[contador].str, aux.str + start);
        contador++;
    }
    return contador;
}

void setDataFormatada(String entrada, Data *saida) {
    if (strlen(entrada.str) < 8) {
        saida->dia = 1;
        saida->mes = 1;
        saida->ano = 0;
        return;
    }

    char mes[4], dia[3], ano[5], mesNum[3];
    strncpy(mes, entrada.str, 3);
    mes[3] = '\0';

    if (strlen(entrada.str) == 8 && entrada.str[3] == ' ') {
        dia[0] = '0';
        dia[1] = '1';
        dia[2] = '\0';
        strcpy(ano, entrada.str + 4);
    } else if (entrada.str[5] == ',') {
        dia[0] = '0';
        dia[1] = entrada.str[4];
        dia[2] = '\0';
        strcpy(ano, entrada.str + 7);
    } else {
        dia[0] = entrada.str[4];
        dia[1] = entrada.str[5];
        dia[2] = '\0';
        strcpy(ano, entrada.str + 8);
    }

    if (strcmp(mes, "Jan") == 0) strcpy(mesNum, "01");
    else if (strcmp(mes, "Feb") == 0) strcpy(mesNum, "02");
    else if (strcmp(mes, "Mar") == 0) strcpy(mesNum, "03");
    else if (strcmp(mes, "Apr") == 0) strcpy(mesNum, "04");
    else if (strcmp(mes, "May") == 0) strcpy(mesNum, "05");
    else if (strcmp(mes, "Jun") == 0) strcpy(mesNum, "06");
    else if (strcmp(mes, "Jul") == 0) strcpy(mesNum, "07");
    else if (strcmp(mes, "Aug") == 0) strcpy(mesNum, "08");
    else if (strcmp(mes, "Sep") == 0) strcpy(mesNum, "09");
    else if (strcmp(mes, "Oct") == 0) strcpy(mesNum, "10");
    else if (strcmp(mes, "Nov") == 0) strcpy(mesNum, "11");
    else if (strcmp(mes, "Dec") == 0) strcpy(mesNum, "12");
    else strcpy(mesNum, "01");

    saida->dia = atoi(dia);
    saida->ano = atoi(ano);
    saida->mes = atoi(mesNum);
}

void setId(Game *game, String valor) { game->id = atoi(valor.str); }
void setNome(Game *game, String valor) { strcpy(game->nome.str, valor.str); }
void setData(Game *game, String valor) { setDataFormatada(valor, &game->data); }
void setJogadores(Game *game, String valor) {
    String aux;
    aux.str[0] = '\0';
    int pos = 0;
    for (int i = 0; i < (int)strlen(valor.str); i++) {
        if (valor.str[i] >= '0' && valor.str[i] <= '9')
            aux.str[pos++] = valor.str[i];
    }
    aux.str[pos] = '\0';
    game->jogadores = atoi(aux.str);
}
void setPreco(Game *game, String valor) {
    if (strcmp(valor.str, "Free to Play") == 0 || strcmp(valor.str, "0.0") == 0) game->preco = 0.0f;
    else game->preco = atof(valor.str);
}
void setIdiomas(Game *game, String valor) { game->num_idiomas = formatar(valor, game->idiomas, true); }
void setNotaEspecial(Game *game, String valor) {
    if (strlen(valor.str) == 0) game->notaEspecial = 0;
    else game->notaEspecial = atoi(valor.str);
}
void setNotaUsuario(Game *game, String valor) {
    if (strlen(valor.str) == 0 || strcmp(valor.str, "tbd") == 0) game->notaUsuario = 0.0f;
    else game->notaUsuario = atof(valor.str);
}
void setConquistas(Game *game, String valor) {
    if (strlen(valor.str) == 0) game->conquistas = 0;
    else game->conquistas = atoi(valor.str);
}
void setEmpresasPublicacao(Game *game, String valor) { game->numEmpresasPublicacao = formatar(valor, game->empresasPublicacao, false); }
void setEmpresasEstudios(Game *game, String valor) { game->numEmpresasEstudios = formatar(valor, game->empresasEstudios, false); }
void setCategorias(Game *game, String valor) { game->numCategorias = formatar(valor, game->categorias, false); }
void setGeneros(Game *game, String valor) { game->numGeneros = formatar(valor, game->generos, false); }
void setTags(Game *game, String valor) { game->numTags = formatar(valor, game->tags, false); }

void settar(Game *game, String array[]) {
    setId(game, array[0]);
    setNome(game, array[1]);
    setData(game, array[2]);
    setJogadores(game, array[3]);
    setPreco(game, array[4]);
    setIdiomas(game, array[5]);
    setNotaEspecial(game, array[6]);
    setNotaUsuario(game, array[7]);
    setConquistas(game, array[8]);
    setEmpresasPublicacao(game, array[9]);
    setEmpresasEstudios(game, array[10]);
    setCategorias(game, array[11]);
    setGeneros(game, array[12]);
    setTags(game, array[13]);
}

void imprimirArray(String array[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        int start = 0;
        while (array[i].str[start] == ' ') start++;
        printf("%s", array[i].str + start);
        if (i < n - 1) printf(", ");
    }
    printf("]");
}
void imprimir(Game *game) {
    printf("=> %d ## %s ## %02d/%02d/%04d ## %d ## ",
           game->id,
           game->nome.str,
           game->data.dia,
           game->data.mes,
           game->data.ano,
           game->jogadores);
    if (game->preco == 0.0) printf("0.0 ## ");
    else printf("%g ## ", game->preco);
    imprimirArray(game->idiomas, game->num_idiomas);
    printf(" ## %d ## %.1f ## %d ## ",
           game->notaEspecial,
           game->notaUsuario,
           game->conquistas);
    imprimirArray(game->empresasPublicacao, game->numEmpresasPublicacao);
    printf(" ## ");
    imprimirArray(game->empresasEstudios, game->numEmpresasEstudios);
    printf(" ## ");
    imprimirArray(game->categorias, game->numCategorias);
    printf(" ## ");
    imprimirArray(game->generos, game->numGeneros);
    printf(" ## ");
    imprimirArray(game->tags, game->numTags);
    printf(" ##\n");
}


void swap(Game game[], int i, int j) {
    Game temp = game[i];
    game[i] = game[j];
    game[j] = temp;
}

void quickSort(Game game[], int esq, int dir) {
    int i = esq, j = dir;
    int pivo = game[(esq + dir) / 2].id;
    while (i <= j) {
        while (game[i].id < pivo) i++;
        while (game[j].id > pivo) j--;
        if (i <= j) {
            swap(game, i, j);
            i++; j--;
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

typedef struct Celula {
    Game elemento;
    struct Celula *prox;
} Celula;

typedef struct {
    Celula *primeiro; // sentinela
    Celula *ultimo;
    int tamanho;
} Lista;

Lista *criarLista() {
    Lista *l = (Lista *)malloc(sizeof(Lista));
    Celula *sent = (Celula *)malloc(sizeof(Celula));
    sent->prox = NULL;
    l->primeiro = sent;
    l->ultimo = sent;
    l->tamanho = 0;
    return l;
}

void inserirInicio(Lista *l, Game x) {
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = x;
    nova->prox = l->primeiro->prox;
    l->primeiro->prox = nova;
    if (l->primeiro == l->ultimo) l->ultimo = nova;
    l->tamanho++;
    movimentacoes++;
}

void inserirFim(Lista *l, Game x) {
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = x;
    nova->prox = NULL;
    l->ultimo->prox = nova;
    l->ultimo = nova;
    l->tamanho++;
    movimentacoes++;
}

void inserirPos(Lista *l, Game x, int pos) {
    if (pos < 0 || pos > l->tamanho) return;
    if (pos == 0) { inserirInicio(l, x); return; }
    if (pos == l->tamanho) { inserirFim(l, x); return; }
    Celula *i = l->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox);
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = x;
    nova->prox = i->prox;
    i->prox = nova;
    l->tamanho++;
    movimentacoes++;
}

Game removerInicio(Lista *l) {
    if (l->primeiro == l->ultimo) return gamevazio();
    Celula *tmp = l->primeiro->prox;
    l->primeiro->prox = tmp->prox;
    if (tmp == l->ultimo) l->ultimo = l->primeiro;
    Game resp = tmp->elemento;
    free(tmp);
    l->tamanho--;
    movimentacoes++;
    return resp;
}

Game removerFim(Lista *l) {
    if (l->primeiro == l->ultimo) return gamevazio();
    Celula *i;
    for (i = l->primeiro; i->prox != l->ultimo; i = i->prox);
    Celula *tmp = l->ultimo;
    Game resp = tmp->elemento;
    i->prox = NULL;
    l->ultimo = i;
    free(tmp);
    l->tamanho--;
    movimentacoes++;
    return resp;
}

Game removerPos(Lista *l, int pos) {
    if (pos < 0 || pos >= l->tamanho) return gamevazio();
    if (pos == 0) return removerInicio(l);
    if (pos == l->tamanho - 1) return removerFim(l);
    Celula *i = l->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox);
    Celula *tmp = i->prox;
    i->prox = tmp->prox;
    Game resp = tmp->elemento;
    free(tmp);
    l->tamanho--;
    movimentacoes++;
    return resp;
}

void freeLista(Lista *l) {
    Celula *p = l->primeiro;
    while (p != NULL) {
        Celula *t = p->prox;
        free(p);
        p = t;
    }
    free(l);
}

bool removerPorId(Lista *l, int id) {
    Celula *ant = l->primeiro;
    Celula *at = ant->prox;
    while (at != NULL) {
        if (at->elemento.id == id) {
            ant->prox = at->prox;
            if (at == l->ultimo) l->ultimo = ant;
            free(at);
            l->tamanho--;
            movimentacoes++;
            return true;
        }
        ant = at;
        at = at->prox;
    }
    return false;
}


int hashNome(const char *nome) {
    int soma = 0;
    for (int i = 0; nome[i] != '\0'; i++) soma += (unsigned char)nome[i];
    return soma % TAM_TAB;
}

void inserirNaTabela(Lista *tabela[], Game x) {
    int h = hashNome(x.nome.str);
    inserirFim(tabela[h], x);
}

void removerDaTabelaPorId(Lista *tabela[], int id, const char *nome) {
    int h = hashNome(nome);
    removerPorId(tabela[h], id);
}


bool buscarNaTabela(Lista *tabela[], const char *nome, int *bucket) {
    int h = hashNome(nome);
    *bucket = h;
    for (Celula *c = tabela[h]->primeiro->prox; c != NULL; c = c->prox) {
        comparar++;
        if (strcmp(c->elemento.nome.str, nome) == 0) {
            return true;
        }
    }
    return false;
}

int main() {
    FILE *arq = fopen("tmp/games.csv", "r");
    if (!arq) { printf("Erro ao abrir o arquivo /tmp/games.csv\n"); return 1; }

    Game *game = (Game *)malloc(4000 * sizeof(Game));
    if (game == NULL) return 1;

    String entrada, cabecalho;
    int jogos = 0;
    if (fscanf(arq, " %[^\n]", cabecalho.str) == EOF) { fclose(arq); return 1; }

    while (fscanf(arq, " %[^\n]", entrada.str) != EOF) {
        entrada.str[strcspn(entrada.str, "\r\n")] = '\0';
        String array[14];
        String aux;
        aux.str[0] = '\0';
        int contador = 0, auxPos = 0;
        bool aspas = false;

        for (int i = 0; i < (int)strlen(entrada.str); i++) {
            char c = entrada.str[i];
            if (c == '"') aspas = !aspas;
            else if (c == ',' && !aspas) {
                aux.str[auxPos] = '\0';
                strcpy(array[contador++].str, aux.str);
                auxPos = 0;
                aux.str[0] = '\0';
            } else {
                aux.str[auxPos++] = c;
            }
        }
        aux.str[auxPos] = '\0';
        strcpy(array[contador].str, aux.str);
        settar(&game[jogos], array);
        jogos++;
    }
    fclose(arq);

    if (jogos > 0) quickSort(game, 0, jogos - 1);
    game = (Game *)realloc(game, jogos * sizeof(Game));


    Lista *tabela[TAM_TAB];
    for (int i = 0; i < TAM_TAB; i++) tabela[i] = criarLista();
    Lista *lista = criarLista();

    char token[TAM_MAX];
    while (scanf(" %s", token) == 1) {
        if (strcmp(token, "FIM") == 0) break;
        int idBusca = atoi(token);
        int x = pesquisaBinaria(game, 0, jogos - 1, idBusca);
        if (x != -1) {
            inserirFim(lista, game[x]);
            inserirNaTabela(tabela, game[x]);
        }
    }

   
    int c = getchar();
    if (c != '\n' && c != EOF) {
        ungetc(c, stdin);
    }

    char nomeLinha[TAM_MAX];
    while (fgets(nomeLinha, sizeof(nomeLinha), stdin) != NULL) {
        nomeLinha[strcspn(nomeLinha, "\r\n")] = '\0';
        if (strcmp(nomeLinha, "FIM") == 0) break;
        if (strlen(nomeLinha) == 0) continue; 
        int bucket = -1;
        bool achou = buscarNaTabela(tabela, nomeLinha, &bucket);
        if (achou) {
            printf("%s:  (Posicao: %d) SIM\n", nomeLinha, bucket);
        } else {
            printf("%s:  (Posicao: %d) NAO\n", nomeLinha, bucket);
        }
    }

    FILE *logf = fopen("878672_hashIndireta.txt", "w");
    if (logf) {
        time_t t = time(NULL);
        struct tm tm = *localtime(&t);
        fprintf(logf, "878672\t %d\t %d\n", comparar, movimentacoes);
        fclose(logf);
    }

    // liberar memória
    for (int b = 0; b < TAM_TAB; b++) freeLista(tabela[b]);
    freeLista(lista);
    free(game);

    return 0;
}
