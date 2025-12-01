#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

int comparar = 0;
int movimentacoes = 0;
#define TAM_MAX 1000
#define TAM 50
#define MAX_JOGOS 2000
#define MAX_ITENS TAM

typedef struct {
    char str[TAM_MAX];
} String;

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct Game {
    struct Game *ant;
    struct Game *prox;
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
    strncpy(g.nome.str, "nenhum", TAM_MAX-1); g.nome.str[TAM_MAX-1] = '\0';
    g.data.dia = 1;
    g.data.mes = 1;
    g.data.ano = 2000;
    g.jogadores = 0;
    g.preco = 0.0f;
    strncpy(g.idiomas[0].str, " ", TAM_MAX-1); g.idiomas[0].str[TAM_MAX-1] = '\0';
    g.notaEspecial = -1;
    g.notaUsuario = -1.0f;
    g.conquistas = 0;
    strncpy(g.empresasPublicacao[0].str, " ", TAM_MAX-1); g.empresasPublicacao[0].str[TAM_MAX-1] = '\0';
    strncpy(g.empresasEstudios[0].str, " ", TAM_MAX-1); g.empresasEstudios[0].str[TAM_MAX-1] = '\0';
    strncpy(g.categorias[0].str, " ", TAM_MAX-1); g.categorias[0].str[TAM_MAX-1] = '\0';
    strncpy(g.generos[0].str, " ", TAM_MAX-1); g.generos[0].str[TAM_MAX-1] = '\0';
    strncpy(g.tags[0].str, " ", TAM_MAX-1); g.tags[0].str[TAM_MAX-1] = '\0';
    g.num_idiomas = g.numEmpresasPublicacao = g.numEmpresasEstudios = g.numCategorias = g.numGeneros = g.numTags = 0;
    return g;
}


int formatar(String entrada, String saida[], bool apostrofo) {
    String aux;
    aux.str[0] = '\0';
    int contador = 0;
    int auxPos = 0;
    bool inQuotes = false;

    size_t len = strnlen(entrada.str, TAM_MAX);
    for (size_t i = 0; i < len && contador < MAX_ITENS; i++) {
        char c = entrada.str[i];
        if (c == '"') {
            inQuotes = !inQuotes;
        } else if (c == ',' && !inQuotes) {
            /* finalize aux e copie para saida */
            aux.str[auxPos] = '\0';
            /* remove espaços iniciais */
            int start = 0;
            while (aux.str[start] == ' ' && aux.str[start] != '\0') start++;
            if (aux.str[start] != '\0') {
                strncpy(saida[contador].str, aux.str + start, TAM_MAX - 1);
                saida[contador].str[TAM_MAX - 1] = '\0';
                contador++;
            }
            auxPos = 0;
            aux.str[0] = '\0';
        } else {
            if (!(c == '[' || c == ']' || (apostrofo && c == '\''))) {
                if (auxPos < (int)TAM_MAX - 1) {
                    aux.str[auxPos++] = c;
                }
            }
        }
    }
    /* resto */
    aux.str[auxPos] = '\0';
    int start = 0;
    while (aux.str[start] == ' ' && aux.str[start] != '\0') start++;
    if (aux.str[start] != '\0' && contador < MAX_ITENS) {
        strncpy(saida[contador].str, aux.str + start, TAM_MAX - 1);
        saida[contador].str[TAM_MAX - 1] = '\0';
        contador++;
    }
    return contador;
}


void setDataFormatada(String entrada, Data *saida) {
    /* valores padrão */
    saida->dia = 1;
    saida->mes = 1;
    saida->ano = 0;

    /* protege contra buffers curtos */
    size_t len = strnlen(entrada.str, TAM_MAX);
    if (len < 3) return;

    char mes3[4] = {0};
    
    for (int i = 0; i < 3 && i < (int)len; i++) mes3[i] = entrada.str[i];
    mes3[3] = '\0';

    int firstNum = -1, secondNum = -1;
    const char *p = entrada.str;
    if (len > 3) p = entrada.str + 3;

    
    while (*p && !isdigit((unsigned char)*p)) p++;
    if (*p) {
        if (sscanf(p, "%d", &firstNum) != 1) firstNum = -1;
      
        while (*p && isdigit((unsigned char)*p)) p++;
       
        while (*p && !isdigit((unsigned char)*p)) p++;
        if (*p) {
            if (sscanf(p, "%d", &secondNum) != 1) secondNum = -1;
        }
    }

    if (firstNum == -1 && secondNum == -1) {
        
        return;
    }

    if (firstNum != -1 && secondNum == -1) {
       
        saida->dia = 1;
        saida->ano = firstNum;
    } else if (firstNum != -1 && secondNum != -1) {
        
        if (firstNum > 31) {
            saida->dia = 1;
            saida->ano = firstNum;
        } else {
            saida->dia = firstNum;
            saida->ano = secondNum;
        }
    }

    
    if (strncmp(mes3, "Jan", 3) == 0) saida->mes = 1;
    else if (strncmp(mes3, "Feb", 3) == 0) saida->mes = 2;
    else if (strncmp(mes3, "Mar", 3) == 0) saida->mes = 3;
    else if (strncmp(mes3, "Apr", 3) == 0) saida->mes = 4;
    else if (strncmp(mes3, "May", 3) == 0) saida->mes = 5;
    else if (strncmp(mes3, "Jun", 3) == 0) saida->mes = 6;
    else if (strncmp(mes3, "Jul", 3) == 0) saida->mes = 7;
    else if (strncmp(mes3, "Aug", 3) == 0) saida->mes = 8;
    else if (strncmp(mes3, "Sep", 3) == 0) saida->mes = 9;
    else if (strncmp(mes3, "Oct", 3) == 0) saida->mes = 10;
    else if (strncmp(mes3, "Nov", 3) == 0) saida->mes = 11;
    else if (strncmp(mes3, "Dec", 3) == 0) saida->mes = 12;
    else saida->mes = 1;
}

void setId(Game *game, String valor) { game->id = atoi(valor.str); }
void setNome(Game *game, String valor) { strncpy(game->nome.str, valor.str, TAM_MAX-1); game->nome.str[TAM_MAX-1] = '\0'; }
void setData(Game *game, String valor) { setDataFormatada(valor, &game->data); }
void setJogadores(Game *game, String valor) {
    String aux; aux.str[0] = '\0';
    int pos = 0;
    for (int i = 0; i < (int)strnlen(valor.str, TAM_MAX) && pos < TAM_MAX-1; i++) {
        if (isdigit((unsigned char)valor.str[i])) aux.str[pos++] = valor.str[i];
    }
    aux.str[pos] = '\0';
    game->jogadores = atoi(aux.str);
}
void setPreco(Game *game, String valor) {
    if (strcmp(valor.str, "Free to Play") == 0 || strcmp(valor.str, "0.0") == 0 || strlen(valor.str) == 0) {
        game->preco = 0.0f;
    } else {
        game->preco = atof(valor.str);
    }
}
void setIdiomas(Game *game, String valor) { game->num_idiomas = formatar(valor, game->idiomas, true); }
void setNotaEspecial(Game *game, String valor) { game->notaEspecial = strlen(valor.str) == 0 ? 0 : atoi(valor.str); }
void setNotaUsuario(Game *game, String valor) {
    if (strlen(valor.str) == 0 || strcmp(valor.str, "tbd") == 0) game->notaUsuario = 0.0f;
    else game->notaUsuario = atof(valor.str);
}
void setConquistas(Game *game, String valor) { game->conquistas = strlen(valor.str) == 0 ? 0 : atoi(valor.str); }
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
        while (array[i].str[start] == ' ' && array[i].str[start] != '\0') start++;
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
           game->jogadores
    );
    if (game->preco == 0.0) printf("0.0 ## ");
    else printf("%g ## ", game->preco);
    imprimirArray(game->idiomas, game->num_idiomas);
    printf(" ## %d ## %.1f ## %d ## ", game->notaEspecial, game->notaUsuario, game->conquistas);
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


typedef struct No {
    Game elemento;
    struct No *esq, *dir;
    int nivel;
} No;

No *criarNo(Game game) {
    No *nova = (No*)malloc(sizeof(No));
    if (nova == NULL) { perror("Erro ao alocar memoria para No"); exit(EXIT_FAILURE); }
    nova->elemento = game;
    nova->esq = nova->dir = NULL;
    nova->nivel = 1;
    return nova;
}

typedef struct ArvoreAVL {
    No *raiz;
} ArvoreAVL;

ArvoreAVL *criarArvore() {
    ArvoreAVL *nova = (ArvoreAVL*)malloc(sizeof(ArvoreAVL));
    if (nova == NULL) { perror("Erro ao alocar memoria para ArvoreAVL"); exit(EXIT_FAILURE); }
    nova->raiz = NULL;
    return nova;
}

int getNivel(No *no) { return no ? no->nivel : 0; }
void setNivel(No *no) {
    if (!no) return;
    int ne = getNivel(no->esq);
    int nd = getNivel(no->dir);
    no->nivel = 1 + (ne > nd ? ne : nd);
}
int getFator(No *no) { return no ? getNivel(no->dir) - getNivel(no->esq) : 0; }

No *rotacionarDir(No *no) {
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;
    noEsq->dir = no;
    no->esq = noEsqDir;
    setNivel(no);
    setNivel(noEsq);
    return noEsq;
}
No *rotacionarEsq(No *no) {
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;
    noDir->esq = no;
    no->dir = noDirEsq;
    setNivel(no);
    setNivel(noDir);
    return noDir;
}
No *balancear(No *no) {
    if (no != NULL) {
        setNivel(no);
        int fator = getFator(no);
        if (fator == 2) {
            int fatorDir = getFator(no->dir);
            if (fatorDir == -1) no->dir = rotacionarDir(no->dir);
            return rotacionarEsq(no);
        } else if (fator == -2) {
            int fatorEsq = getFator(no->esq);
            if (fatorEsq == 1) no->esq = rotacionarEsq(no->esq);
            return rotacionarDir(no);
        }
    }
    return no;
}

No *inserirAux(Game x, No *i) {
    if (i == NULL) i = criarNo(x);
    else {
        comparar++;
        if (strcmp(x.nome.str, i->elemento.nome.str) > 0) i->dir = inserirAux(x, i->dir);
        else if (strcmp(x.nome.str, i->elemento.nome.str) < 0) i->esq = inserirAux(x, i->esq);
        else ; // já existe
    }
    return balancear(i);
}
void inserir(Game x, ArvoreAVL *arvore) { arvore->raiz = inserirAux(x, arvore->raiz); }

bool pesquisarNomeAux(String str, No *i) {
    bool resp = false;
    while (i != NULL) {
        comparar++;
        int cmp = strcmp(str.str, i->elemento.nome.str);
        if (cmp > 0) {
            printf(" dir");
            i = i->dir;
        } else if (cmp < 0) {
            printf(" esq");
            i = i->esq;
        } else {
            resp = true;
            i = NULL;
        }
    }
    return resp;
}
void pesquisarNome(String str, ArvoreAVL *arvore) {
    printf("%s: raiz", str.str);
    if (pesquisarNomeAux(str, arvore->raiz)) printf(" SIM\n");
    else printf(" NAO\n");
}

void freeArvoreAux(No *i) {
    if (i != NULL) {
        freeArvoreAux(i->esq);
        freeArvoreAux(i->dir);
        free(i);
    }
}
void freeArvore(ArvoreAVL *arvore) {
    if (!arvore) return;
    freeArvoreAux(arvore->raiz);
    free(arvore);
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


int main() {
    FILE *arq = fopen("tmp/games.csv", "r");
    if (!arq) {
        fprintf(stderr, "Erro ao abrir o arquivo '/tmp/games.csv'\n");
      
    }

    Game *game = (Game*)malloc(MAX_JOGOS * sizeof(Game));
    if (game == NULL) { perror("Erro ao alocar memoria para games"); return 1; }

    int jogos = 0;
    String entrada, cabecalho;
    cabecalho.str[0] = '\0';
    entrada.str[0] = '\0';

    if (arq) {
       
        if (fgets(cabecalho.str, (int)TAM_MAX, arq) == NULL) {
            
        }
        
        while (jogos < MAX_JOGOS && fgets(entrada.str, (int)TAM_MAX, arq) != NULL) {
            
            entrada.str[strcspn(entrada.str, "\r\n")] = '\0';

            String array[14];
            for (int k = 0; k < 14; k++) array[k].str[0] = '\0';

            String aux; aux.str[0] = '\0';
            int contador = 0, auxPos = 0;
            bool aspas = false;
            size_t len = strnlen(entrada.str, TAM_MAX);
            for (size_t i = 0; i < len && contador < 14; i++) {
                char c = entrada.str[i];
                if (c == '"') aspas = !aspas;
                else if (c == ',' && !aspas) {
                    aux.str[auxPos] = '\0';
                    strncpy(array[contador++].str, aux.str, TAM_MAX-1);
                    array[contador-1].str[TAM_MAX-1] = '\0';
                    auxPos = 0;
                    aux.str[0] = '\0';
                } else {
                    if (auxPos < (int)TAM_MAX-1) aux.str[auxPos++] = c;
                }
            }
            aux.str[auxPos] = '\0';
            if (contador < 14) {
                strncpy(array[contador].str, aux.str, TAM_MAX-1);
                array[contador].str[TAM_MAX-1] = '\0';
            }
            /* populamos o jogo */
            settar(&game[jogos], array);
            jogos++;
        }
        fclose(arq);
    }
    quickSort(game, 0, jogos-1);
    ArvoreAVL *arvore = criarArvore();

   
    char line[256];
    while (fgets(line, sizeof(line), stdin) != NULL) {
        line[strcspn(line, "\r\n")] = '\0';
        if (strcmp(line, "FIM") == 0) break;
        int idBusca = atoi(line);
           idBusca=  pesquisaBinaria(game, 0, jogos-1, idBusca);
            
                inserir(game[idBusca], arvore);

    }
        
    

   
    clock_t inicio = clock();

    while (fgets(line, sizeof(line), stdin) != NULL) {
        line[strcspn(line, "\r\n")] = '\0';
        if (strcmp(line, "FIM") == 0) break;
        /* copia para String buscaNome */
        String buscaNome;
        strncpy(buscaNome.str, line, TAM_MAX-1);
        buscaNome.str[TAM_MAX-1] = '\0';
        size_t lenname = strnlen(buscaNome.str, TAM_MAX);
        while (lenname > 0 && (buscaNome.str[lenname-1] == ' ' || buscaNome.str[lenname-1] == '\t' || buscaNome.str[lenname-1] == '\r')) {
            buscaNome.str[--lenname] = '\0';
        }
        if (lenname > 0) pesquisarNome(buscaNome, arvore);
    }

    clock_t fim = clock();

    freeArvore(arvore);
    free(game);

    double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC;
    FILE *log = fopen("878672.txt", "w");
    if (log != NULL) {
        fprintf(log, "878672\t%.3fms\t%dcomparacoes\n", tempoExecucao * 1000, comparar);
        fclose(log);
    } else {
        fprintf(stderr, "Erro ao criar arquivo de log.\n");
    }
    return 0;
}
